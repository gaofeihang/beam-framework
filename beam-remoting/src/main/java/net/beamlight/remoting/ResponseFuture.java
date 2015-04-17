package net.beamlight.remoting;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on Feb 12, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class ResponseFuture implements Future<RemotingResponse> {
    
    private static final Logger logger = LoggerFactory.getLogger(ResponseFuture.class); 
    
    private static final int DEFAULT_TIMEOUT = 1000;
    
    private static final ConcurrentHashMap<Long, ResponseFuture> futureMap = new ConcurrentHashMap<Long, ResponseFuture>();
    
    private long start = System.currentTimeMillis();
    private long id;
    private volatile RemotingResponse response;
    private int timeout;
    
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    public ResponseFuture(long id) {
        this(id, DEFAULT_TIMEOUT);
    }
    
    public ResponseFuture(long id, int timeout) {
        this.id = id;
        this.timeout = timeout;
        
        if (futureMap.putIfAbsent(id, this) != null) {
            logger.warn("requestId conflict: {}, thread: {}", id, Thread.currentThread().getName());
        }
    }
    
    public static void receiveResponse(RemotingResponse response) {
        long id = response.getId();
        ResponseFuture future = futureMap.remove(id);
        if (future != null) {
            future.setResponse(response);
        }
    }
    
    public void setResponse(RemotingResponse response) {
        this.response = response;
        
        try {
            lock.lock();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return response == null;
    }

    @Override
    public RemotingResponse get() throws InterruptedException, ExecutionException {
        
        try {
            lock.lock();
            condition.await();
            return response;
        } finally {
            futureMap.remove(id);
            lock.unlock();
        }
    }

    @Override
    public RemotingResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        
        try {
            lock.lock();
            condition.await(timeout, unit);
            return response;
        } finally {
            futureMap.remove(id);
            lock.unlock();
        }
    }
    
    private static class TimeoutScan implements Runnable {

        public void run() {
            while (true) {
                try {
                    for (ResponseFuture future : futureMap.values()) {
                        if (future == null || future.isDone()) {
                            continue;
                        }
                        if (System.currentTimeMillis() - future.start > future.timeout) {
                            RemotingResponse response = new RemotingResponse(future.id, null);
                            ResponseFuture.receiveResponse(response);
                        }
                    }
                    Thread.sleep(30);
                } catch (Throwable e) {
                    logger.error("Exception when scan the timeout invocation of remoting.", e);
                }
            }
        }
    }

    static {
        Thread t = new Thread(new TimeoutScan(), "BeamRemotingTimeoutScanner");
        t.setDaemon(true);
        t.start();
    }

}
