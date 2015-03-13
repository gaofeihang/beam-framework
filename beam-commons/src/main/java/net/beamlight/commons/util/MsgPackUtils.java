package net.beamlight.commons.util;

import java.util.Map;

import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gaofeihang
 * @since Feb 3, 2015
 */
public class MsgPackUtils {

    private static final Logger logger = LoggerFactory.getLogger(MsgPackUtils.class);

    private static ThreadLocal<ObjectMapper> msgPackLocal = new ThreadLocal<ObjectMapper>() {
        @Override
        public ObjectMapper initialValue() {
            return new ObjectMapper(new MessagePackFactory())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    };

    public static byte[] encode(Object value) {
        try {
            return msgPackLocal.get().writeValueAsBytes(value);
        } catch (Exception e) {
            logger.error("encode error: {}", value, e);
            return null;
        }
    }

    public static <T> T decode(byte[] bytes, Class<T> clazz) {
        try {
            return msgPackLocal.get().readValue(bytes, clazz);
        } catch (Exception e) {
            logger.error("decode error: {}", ByteArrayUtils.prettyPrint(bytes), e);
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(byte[] bytes) {
        return decode(bytes, Map.class);
    }
    
}
