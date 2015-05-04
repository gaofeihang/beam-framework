package net.beamlight.remoting;

import net.beamlight.commons.frame.BeamRequest;
import net.beamlight.commons.frame.BeamResponse;
import net.beamlight.commons.frame.Constants;
import net.beamlight.remoting.handler.PacketHandlerAdapter;
import net.beamlight.remoting.netty.NettyBeamServer;
import net.beamlight.remoting.stat.RemotingStats;
import net.beamlight.remoting.util.PacketUtils;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class RemotingServerBootstrap {

    public static void main(String[] args) {
        
        System.setProperty(Constants.KEY_APP_NAME, "Remoting Server");
        
        BeamServer server = new NettyBeamServer(8080);
        server.setHandler(new PacketHandlerAdapter() {
            @Override
            protected Object handleRequest(BeamPacket packet) {
                BeamRequest request = PacketUtils.decode(packet, BeamRequest.class);
                return new BeamResponse("resp to: " + request.getService());
            }
        });
        server.start();
        RemotingStats.start();
    }
    
}
