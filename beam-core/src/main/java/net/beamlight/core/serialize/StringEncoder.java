package net.beamlight.core.serialize;

import java.nio.charset.Charset;

/**
 * Created on May 6, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class StringEncoder {
    
    private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    
    public static byte[] encode(String str) {
        return str.getBytes(DEFAULT_CHARSET);
    }
    
    public static String decode(byte[] bytes) {
        return new String(bytes, DEFAULT_CHARSET);
    }

}
