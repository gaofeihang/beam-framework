package net.beamlight.remoting.util;

import net.beamlight.core.serialize.SerializerFactory;
import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.Protocol;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public class PacketUtils {
    
    public static BeamPacket encode(Object obj, byte cmd, byte codec) {
        return encode(obj, Protocol.nextReqId(), cmd, codec);
    }
    
    public static BeamPacket encode(Object obj, int id, byte cmd, byte codec) {
        byte[] data = SerializerFactory.getSerializer(codec).serialize(obj);
        BeamPacket packet = new BeamPacket(id, cmd, codec, data);
        return packet;
    }
    
    public static <T> T decode(BeamPacket packet, Class<T> clazz) {
       return SerializerFactory.getSerializer(packet.getCodec()).deserialize(packet.getData(), clazz);
    }

}
