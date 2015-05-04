package net.beamlight.remoting;

import net.beamlight.remoting.exception.RemotingException;
import net.beamlight.remoting.handler.PacketHandler;

/**
 * Created on Mar 9, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public interface BeamClient {
    
    void open() throws RemotingException;
    
    void close();
    
    void send(BeamPacket packet) throws RemotingException;
    
    BeamPacket sendAndGet(BeamPacket packet) throws RemotingException;
    
    void setHandler(PacketHandler handler);

}
