package net.beamlight.remoting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class Protocol {
    
    private static final AtomicLong REQ_ID = new AtomicLong(0);
    
    public static final byte CMD_REQUEST = 1;
    public static final byte CMD_RESPONSE = 2;
    
    public static final int INFINITE_TIMEOUT = 0;
    public static final int DEFAULT_TIMEOUT = 1000;
    
    public static long nextReqId() {
        return REQ_ID.incrementAndGet();
    }
    
}
