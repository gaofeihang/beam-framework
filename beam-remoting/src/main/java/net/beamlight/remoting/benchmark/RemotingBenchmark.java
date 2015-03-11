package net.beamlight.remoting.benchmark;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.beamlight.commons.exception.RemotingException;
import net.beamlight.commons.frame.BeamRequest;
import net.beamlight.commons.stat.RemotingStats;
import net.beamlight.commons.util.ThreadUtils;
import net.beamlight.remoting.BeamClient;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public class RemotingBenchmark {
    
    private int threadNum = 1;
    private int loopNum = 10000 * 10000;
    
    private BeamClient client;
    
    private ExecutorService executorService;
    private CountDownLatch latch = new CountDownLatch(threadNum);
    
    public RemotingBenchmark(BeamClient client, int threadNum) {
        this.client = client;
        this.threadNum = threadNum;
        
        executorService = Executors.newCachedThreadPool();
    }
    
    public void start() {
        try {
            client.open();
        } catch (RemotingException e) {
            e.printStackTrace();
            System.exit(0);
        }
        
        for (int i = 0; i < threadNum; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < loopNum; j++) {
                        try {
                            client.sendAndGet(buildRequest());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    ThreadUtils.sleep(1000);
                    latch.countDown();
                }
            });
        }
        
        RemotingStats.start();
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        client.close();
        executorService.shutdown();
    }
    
    private BeamRequest buildRequest() {
        BeamRequest request = new BeamRequest();
        return request;
    }

}
