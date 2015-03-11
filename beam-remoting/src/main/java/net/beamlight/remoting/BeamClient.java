package net.beamlight.remoting;

import net.beamlight.remoting.exception.RemotingException;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public interface BeamClient {
    
    void open() throws RemotingException;
    
    void close();
    
    void send(BeamPacket packet) throws RemotingException;
    
    BeamPacket sendAndGet(BeamPacket packet) throws RemotingException;

}
