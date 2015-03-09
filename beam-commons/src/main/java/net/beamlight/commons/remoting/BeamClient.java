package net.beamlight.commons.remoting;

import net.beamlight.commons.frame.BeamRequest;
import net.beamlight.commons.frame.BeamResponse;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public interface BeamClient {
    
    void start();
    
    void close();
    
    void send(BeamRequest request);
    
    BeamResponse sendAndGet(BeamRequest request);

}
