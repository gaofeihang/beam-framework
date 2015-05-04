package net.beamlight.remoting;

import net.beamlight.remoting.handler.PacketHandler;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public interface BeamEndpoint {
    
    void setHandler(PacketHandler handler);

}
