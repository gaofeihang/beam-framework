package net.beamlight.remoting.template;

import net.beamlight.remoting.BeamClient;
import net.beamlight.remoting.handler.PacketHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public abstract class AbstractBeamServer implements BeamClient {
    
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
    protected int port;
    protected PacketHandler handler;
    
    public AbstractBeamServer(int port) {
        this.port = port;
    }
    
    @Override
    public void setHandler(PacketHandler handler) {
        this.handler = handler;
    }
    
}
