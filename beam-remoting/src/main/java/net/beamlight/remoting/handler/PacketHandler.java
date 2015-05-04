package net.beamlight.remoting.handler;

import net.beamlight.remoting.BeamPacket;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public interface PacketHandler {
    
    BeamPacket handle(BeamPacket packet);

}
