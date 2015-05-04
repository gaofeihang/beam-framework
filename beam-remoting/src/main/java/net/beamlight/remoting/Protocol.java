package net.beamlight.remoting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on Mar 9, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class Protocol {
    
    private static final AtomicLong REQ_ID = new AtomicLong(0);
    
    public static final byte PACKET_REQUEST = 1;
    public static final byte PACKET_RESPONSE = 2;
    public static final byte PACKET_MESSAGE = 3;
    public static final byte PACKET_HEARTBEAT = 4;
    
    public static final int INFINITE_TIMEOUT = 0;
    public static final int DEFAULT_TIMEOUT = 1000;
    
    public static long nextReqId() {
        return REQ_ID.incrementAndGet();
    }
    
}
