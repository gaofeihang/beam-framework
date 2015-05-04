package net.beamlight.remoting;

import net.beamlight.remoting.handler.PacketHandler;

/**
 * Created on Mar 9, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public interface BeamServer {
    
    void start();
    
    void stop();
    
    void setHandler(PacketHandler handler);

}
