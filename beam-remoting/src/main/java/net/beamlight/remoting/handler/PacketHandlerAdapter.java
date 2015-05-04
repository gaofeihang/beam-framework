package net.beamlight.remoting.handler;

import net.beamlight.remoting.BeamPacket;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class PacketHandlerAdapter extends AbstractPacketHandler {

    @Override
    protected Object handleRequest(BeamPacket packet) {
        return null;
    }

    @Override
    protected void handleMessage(BeamPacket packet) {
    }

    @Override
    protected void handleHeartbeat(BeamPacket packet) {
    }

}
