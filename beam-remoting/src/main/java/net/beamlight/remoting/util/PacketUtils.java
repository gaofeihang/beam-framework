package net.beamlight.remoting.util;

import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.Protocol;
import net.beamlight.remoting.serialize.SerializerFactory;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public class PacketUtils {
    
    public static BeamPacket encode(Object request, byte codec) {
        byte[] data = SerializerFactory.getSerializer(codec).serialize(request);
        BeamPacket packet = new BeamPacket(Protocol.nextReqId(), 
                Protocol.CMD_REQUEST, codec, data);
        return packet;
    }
    
    public static <T> T decode(BeamPacket packet, Class<T> clazz) {
       return SerializerFactory.getSerializer(packet.getCodec()).deserialize(packet.getData(), clazz);
    }

}
