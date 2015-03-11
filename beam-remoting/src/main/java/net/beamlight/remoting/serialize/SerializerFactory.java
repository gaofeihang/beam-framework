package net.beamlight.remoting.serialize;

import net.beamlight.remoting.Protocol;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public class SerializerFactory {
    
    private static Serializer jsonSerializer = new JsonSerializer();
    private static Serializer msgPackSerializer = new MsgPackSerializer();
    
    public static Serializer getSerializer(byte codec) {
        switch (codec) {
        case Protocol.CODEC_JSON:
            return jsonSerializer;
        
        case Protocol.CODEC_MSGPACK:
            return msgPackSerializer;

        default:
            throw new IllegalArgumentException("unknown codec");
        }
    }

}
