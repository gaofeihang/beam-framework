package net.beamlight.core.serialize;

import java.nio.charset.Charset;

import net.beamlight.commons.util.JsonUtils;

/**
 * @author gaofeihang
 * @since Mar 10, 2015
 */
public class JsonSerializer implements Serializer {
    
    private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    
    @Override
    public byte[] serialize(Object obj) {
        return JsonUtils.toJSON(obj).getBytes(DEFAULT_CHARSET);
    }
    
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JsonUtils.toT(new String(bytes, DEFAULT_CHARSET), clazz);
    }

}
