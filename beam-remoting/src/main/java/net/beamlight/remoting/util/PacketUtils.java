package net.beamlight.remoting.util;

import net.beamlight.core.serialize.SerializerFactory;
import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.Protocol;

/**
 * Created on Mar 11, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class PacketUtils {
    
    public static BeamPacket encodeRequest(Object obj, byte cmd, byte codec) {
        return encode(obj, Protocol.nextReqId(), cmd, codec, Protocol.DEFAULT_TIMEOUT);
    }
    
    public static BeamPacket encodeRequest(Object obj, byte cmd, byte codec, int timeout) {
        return encode(obj, Protocol.nextReqId(), cmd, codec, timeout);
    }
    
    public static BeamPacket encodeResponse(Object obj, long id, byte cmd, byte codec) {
        return encode(obj, id, cmd, codec, Protocol.DEFAULT_TIMEOUT);
    }
    
    public static BeamPacket encode(Object obj, long id, byte cmd, byte codec, int timeout) {
        byte[] data = SerializerFactory.getSerializer(codec).serialize(obj);
        BeamPacket packet = new BeamPacket(id, cmd, codec, data);
        packet.setTimeout(timeout);
        return packet;
    }
    
    public static <T> T decode(BeamPacket packet, Class<T> clazz) {
       return SerializerFactory.getSerializer(packet.getCodec()).deserialize(packet.getData(), clazz);
    }

}
