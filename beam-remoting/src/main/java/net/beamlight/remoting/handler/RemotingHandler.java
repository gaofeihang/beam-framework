package net.beamlight.remoting.handler;

import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.Protocol;
import net.beamlight.remoting.RemotingResponse;
import net.beamlight.remoting.ResponseFuture;
import net.beamlight.remoting.util.PacketUtils;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public class RemotingHandler {
    
    public static BeamPacket handleRequest(BeamPacket reqPacket, Object response) {
        return PacketUtils.encode(response, reqPacket.getId(), Protocol.CMD_RESPONSE, reqPacket.getCodec());
    }
    
    public static void handleResponse(BeamPacket packet) {
        RemotingResponse response = new RemotingResponse(packet.getId(), packet);
        ResponseFuture.receiveResponse(response);
    }

}
