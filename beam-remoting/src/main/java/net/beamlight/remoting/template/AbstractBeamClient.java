package net.beamlight.remoting.template;

import java.util.concurrent.TimeUnit;

import net.beamlight.remoting.BeamClient;
import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.RemotingResponse;
import net.beamlight.remoting.ResponseFuture;
import net.beamlight.remoting.exception.RemotingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public abstract class AbstractBeamClient implements BeamClient {
    
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    protected String host;
    protected int port;
    
    public AbstractBeamClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public void send(BeamPacket packet) throws RemotingException {
        doWrite(packet);
    }

    @Override
    public BeamPacket sendAndGet(final BeamPacket packet) throws RemotingException {
        ResponseFuture responseFuture = new ResponseFuture(packet.getId());
        
        doWrite(packet);
        
        try {
            RemotingResponse response = responseFuture.get(1000, TimeUnit.MILLISECONDS);
            return response.getModel();
        } catch (Exception e) {
            logger.error("Receive response timeout! request: {}", packet);
        }
        return null;
    }
    
    protected abstract void doWrite(BeamPacket packet);

}
