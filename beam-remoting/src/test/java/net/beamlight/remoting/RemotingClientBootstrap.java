package net.beamlight.remoting;

import net.beamlight.commons.frame.Constants;
import net.beamlight.remoting.benchmark.BeamClientBootstrap;
import net.beamlight.remoting.netty.NettyBeamClient;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class RemotingClientBootstrap {
    
    public static void main(String[] args) {
        
        System.setProperty(Constants.KEY_APP_NAME, "Remoting Client");
        
        BeamClient client = new NettyBeamClient("127.0.0.1", 8080);
        new BeamClientBootstrap(client, 10).start();
    }

}
