package net.beamlight.remoting;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class Protocol {
    
    private static final AtomicInteger REQ_ID = new AtomicInteger(0);
    
    public static final byte DEFAULT_VERSION = 1;
    
    public static final byte CMD_REQUEST = 1;
    public static final byte CMD_RESPONSE = 2;
    
    public static final byte CODEC_JSON = 1;
    public static final byte CODEC_MSGPACK = 2;
    
    public static int nextReqId() {
        return REQ_ID.incrementAndGet();
    }

}
