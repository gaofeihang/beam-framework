package net.beamlight.core.serialize;

/**
 * @author gaofeihang
 * @since Mar 11, 2015
 */
public interface Serializer {
    
    byte[] serialize(Object obj);
    
    <T> T deserialize(byte[] bytes, Class<T> clazz);

}
