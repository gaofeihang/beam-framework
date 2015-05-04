package net.beamlight.remoting.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.beamlight.remoting.BeamEndpoint;
import net.beamlight.remoting.handler.PacketHandler;
import net.beamlight.remoting.handler.PacketHandlerAdapter;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class DefaultBeamEndpoint implements BeamEndpoint {
    
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
    protected PacketHandler handler = new PacketHandlerAdapter();
    
    @Override
    public void setHandler(PacketHandler handler) {
        this.handler = handler;
    }

}
