package net.beamlight.remoting.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.beamlight.commons.util.ByteArrayUtils;
import io.netty.buffer.ByteBuf;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class ByteBufUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(ByteBufUtils.class);
    
    public static void print(ByteBuf buf) {
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        LOG.warn(ByteArrayUtils.prettyPrint(bytes));
    }

}
