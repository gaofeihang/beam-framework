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
    
    public static BeamPacket encodeRequest(Object obj, byte type, byte codec) {
        return encode(obj, Protocol.nextReqId(), type, codec, Protocol.DEFAULT_TIMEOUT);
    }
    
    public static BeamPacket encodeRequest(Object obj, byte type, byte codec, int timeout) {
        return encode(obj, Protocol.nextReqId(), type, codec, timeout);
    }
    
    public static BeamPacket encodeResponse(Object obj, long id, byte type, byte codec) {
        return encode(obj, id, type, codec, Protocol.DEFAULT_TIMEOUT);
    }
    
    public static BeamPacket encode(Object obj, long id, byte type, byte codec, int timeout) {
        byte[] body = SerializerFactory.getSerializer(codec).serialize(obj);
        BeamPacket packet = new BeamPacket(id, type, codec, body);
        packet.setTimeout(timeout);
        return packet;
    }
    
    public static <T> T decode(BeamPacket packet, Class<T> clazz) {
       return SerializerFactory.getSerializer(packet.getCodec()).deserialize(packet.getBody(), clazz);
    }

}
