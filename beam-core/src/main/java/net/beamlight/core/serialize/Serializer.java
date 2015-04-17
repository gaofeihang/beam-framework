package net.beamlight.core.serialize;

/**
 * Created on Mar 11, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public interface Serializer {
    
    byte[] serialize(Object obj);
    
    <T> T deserialize(byte[] bytes, Class<T> clazz);

}
