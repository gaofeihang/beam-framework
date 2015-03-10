package net.beamlight.commons.remoting;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gaofeihang
 * @since Mar 9, 2015
 */
public class Protocol {
    
    private static final AtomicInteger REQ_ID = new AtomicInteger(0);
    
    public static final byte PACKET_REQUEST = 1;
    public static final byte PACKET_RESPONSE = 2;
    
    public static int genReqId() {
        return REQ_ID.incrementAndGet();
    }

}
