package net.beamlight.core.serialize;

import net.beamlight.commons.util.JsonUtils;

/**
 * Created on Mar 10, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class JsonSerializer implements Serializer {
    
    @Override
    public byte[] serialize(Object obj) {
        return StringEncoder.encode(JsonUtils.toJSON(obj));
    }
    
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JsonUtils.toT(StringEncoder.decode(bytes), clazz);
    }

}
