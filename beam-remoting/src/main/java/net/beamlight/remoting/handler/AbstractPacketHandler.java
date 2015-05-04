package net.beamlight.remoting.handler;

import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.Protocol;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public abstract class AbstractPacketHandler implements PacketHandler {

    @Override
    public BeamPacket handle(BeamPacket packet) {
        
        switch (packet.getType()) {
        case Protocol.PACKET_REQUEST:
            return RemotingHandler.buildResponsePacket(packet, handleRequest(packet));
            
        case Protocol.PACKET_RESPONSE:
            RemotingHandler.handleResponse(packet);
            break;
            
        case Protocol.PACKET_MESSAGE:
            RemotingHandler.handleResponse(packet);
            break;
        
        case Protocol.PACKET_HEARTBEAT:
            RemotingHandler.handleResponse(packet);
            break;

        default:
            break;
        }
        
        return null;
    }
    
    protected abstract Object handleRequest(BeamPacket packet);
    
    protected abstract void handleMessage(BeamPacket packet);
    
    protected abstract void handleHeartbeat(BeamPacket packet);
    
}
