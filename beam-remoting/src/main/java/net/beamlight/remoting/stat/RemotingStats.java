package net.beamlight.remoting.stat;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import net.beamlight.commons.util.Counter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on Feb 25, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class RemotingStats {
    
    private static final Logger logger = LoggerFactory.getLogger(RemotingStats.class);
    
    private static Counter readCounter = new Counter();
    private static Counter writeCounter = new Counter();
    private static ConcurrentHashMap<String, Counter> ioWriteCounters = new ConcurrentHashMap<String, Counter>();
    
    private static int round;
    private static long maxAverage;
    private static long prevCount;
    private static int interval = 10;
    
    private static ScheduledExecutorService scheduledExecutorService;
    private static AtomicBoolean inited = new AtomicBoolean(false);
    
    private static String appName = System.getProperty("beam.appName", "");
    
    private static boolean logTimeStat = false;
    
    public static void recordRead() {
        readCounter.inc();
    }
    
    public static void recordWrite() {
        writeCounter.inc();
    }
    
    public static void recordWriiteTime(long time) {
        String threadName = Thread.currentThread().getName();
        Counter counter = ioWriteCounters.get(threadName);
        if (counter == null) {
            ioWriteCounters.putIfAbsent(threadName, new Counter());
        } else {
            counter.inc(time);
        }
    }
    
    public static void start() {
        
        if (inited.compareAndSet(false, true)) {
            scheduledExecutorService = Executors.newScheduledThreadPool(2);
            scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                
                @Override
                public void run() {
                    logger.warn(appName + " Stats - read: {}, write: {}",
                            readCounter.getCountChange(),
                            writeCounter.getCountChange());
                    
                    if (logTimeStat) {
                        for (Entry<String, Counter> entry : ioWriteCounters.entrySet()) {
                            logger.warn(appName + " IoStats - thread: {}, time: {}",
                                    entry.getKey(), entry.getValue().getCountChange());
                        }
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
            
            scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                
                @Override
                public void run() {
                    long writeCount = writeCounter.getCount();
                    long average = (writeCount - prevCount) / interval;
                    maxAverage = average > maxAverage ? average : maxAverage;
                    prevCount = writeCount;
                    if (average > 0) {
                        round++;
                    }
                    logger.warn(appName + " Average - Round: {}, QPS: {}, Max: {}", round, average, maxAverage);
                            
                }
            }, 0, interval, TimeUnit.SECONDS);
        }
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                close();
            }
        });
    }
    
    public static void close() {
        scheduledExecutorService.shutdownNow();
    }
    
    public static void setLogTimeStat(boolean logTimeStat) {
        RemotingStats.logTimeStat = logTimeStat;
    }

}
