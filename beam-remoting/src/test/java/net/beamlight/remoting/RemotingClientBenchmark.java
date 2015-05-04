package net.beamlight.remoting;

import net.beamlight.remoting.benchmark.BeamClientBenchmark;
import net.beamlight.remoting.netty.NettyBeamClient;

/**
 * Created on Jan 5, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class RemotingClientBenchmark {
    
    public static void main(String[] args) {
        int clientNum = 2;
        BeamClient[] clients = new BeamClient[clientNum];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new NettyBeamClient("127.0.0.1", 8080);
        }
        new BeamClientBenchmark(clients, 10).start();
    }

}
