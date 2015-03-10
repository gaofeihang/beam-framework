package net.beamlight.commons.serializer;

import java.nio.charset.Charset;

import net.beamlight.commons.util.JsonUtils;

/**
 * @author gaofeihang
 * @since Mar 10, 2015
 */
public class JsonSerializer {
    
    private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    
    public static byte[] serialize(Object obj) {
        return JsonUtils.toJSON(obj).getBytes(DEFAULT_CHARSET);
    }
    
    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JsonUtils.toT(new String(bytes, DEFAULT_CHARSET), clazz);
    }

}
