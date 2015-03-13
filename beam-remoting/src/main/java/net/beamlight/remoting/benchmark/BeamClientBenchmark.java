package net.beamlight.remoting.benchmark;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.beamlight.commons.frame.BeamRequest;
import net.beamlight.commons.util.ThreadUtils;
import net.beamlight.remoting.BeamClient;
import net.beamlight.remoting.Protocol;
import net.beamlight.remoting.exception.RemotingException;
import net.beamlight.remoting.stat.RemotingStats;
import net.beamlight.remoting.util.PacketUtils;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public class BeamClientBenchmark {
    
    private int threadNum = 1;
    private int loopNum = 10000 * 10000;
    
    private BeamClient[] clients;
    
    private ExecutorService executorService;
    private CountDownLatch latch = new CountDownLatch(threadNum);
    
    public BeamClientBenchmark(BeamClient client, int threadNum) {
        this(new BeamClient[] { client }, threadNum);
    }
    
    public BeamClientBenchmark(BeamClient[] clients, int threadNum) {
        this.clients = clients;
        this.threadNum = threadNum;
        
        executorService = Executors.newCachedThreadPool();
    }
    
    public void start() {
        try {
            for (BeamClient client : clients) {
                client.open();
            }
        } catch (RemotingException e) {
            e.printStackTrace();
            System.exit(0);
        }
        
        for (final BeamClient client : clients) {
            for (int i = 0; i < threadNum; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < loopNum; j++) {
                            try {
                                client.sendAndGet(
                                        PacketUtils.encode(new BeamRequest("test"), Protocol.CMD_REQUEST, Protocol.CODEC_MSGPACK));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        ThreadUtils.sleep(1000);
                        latch.countDown();
                    }
                });
            }
        }
        
        RemotingStats.start();
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (BeamClient client : clients) {
            client.close();
        }
        executorService.shutdown();
    }
    
}
