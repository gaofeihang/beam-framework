package net.beamlight.remoting.netty;

import io.netty.buffer.ByteBufAllocator;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class NettyConfig {
    
    public static final ByteBufAllocator DEFAULT_ALLOCATOR = ByteBufAllocator.DEFAULT;
    
    public static ByteBufAllocator getAllocator() {
        return DEFAULT_ALLOCATOR;
    }

}
