package net.beamlight.remoting.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.beamlight.remoting.BeamPacket;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class NettyEncoder extends MessageToByteEncoder<BeamPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, BeamPacket msg, ByteBuf out) throws Exception {
        
        BeamPacket packet = (BeamPacket) msg;
        
        out.writeShort(BeamPacket.MAGIC);
        out.writeLong(packet.getId());
        out.writeByte(packet.getType());
        out.writeByte(packet.getCodec());
        out.writeInt(packet.getLength());
        out.writeBytes(packet.getBody());
    }
    
    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, BeamPacket msg, boolean preferDirect) throws Exception {
        
        BeamPacket packet = (BeamPacket) msg;
        return ctx.alloc().buffer(packet.getPacketLength()); 
    }

}
