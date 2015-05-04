package net.beamlight.remoting.template;

import net.beamlight.remoting.BeamServer;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public abstract class AbstractBeamServer extends DefaultBeamEndpoint implements BeamServer {
    
    protected int port;
    
    public AbstractBeamServer(int port) {
        this.port = port;
    }
    
}
