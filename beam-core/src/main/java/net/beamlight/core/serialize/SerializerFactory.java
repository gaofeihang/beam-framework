package net.beamlight.core.serialize;

/**
 * Created on Mar 11, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class SerializerFactory {
    
    private static Serializer jsonSerializer = new JsonSerializer();
    private static Serializer msgPackSerializer = new MsgPackSerializer();
    
    public static Serializer getSerializer(byte codec) {
        switch (codec) {
        case Codec.JSON:
            return jsonSerializer;
        
        case Codec.MSGPACK:
            return msgPackSerializer;

        default:
            throw new IllegalArgumentException("unknown codec");
        }
    }

}
