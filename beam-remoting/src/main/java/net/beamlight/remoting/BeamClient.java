package net.beamlight.remoting;

import net.beamlight.commons.exception.RemotingException;
import net.beamlight.commons.frame.BeamRequest;
import net.beamlight.commons.frame.BeamResponse;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public interface BeamClient {
    
    void open() throws RemotingException;
    
    void close();
    
    void send(BeamRequest request) throws RemotingException;
    
    BeamResponse sendAndGet(BeamRequest request) throws RemotingException;

}
