package net.beamlight.remoting.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.handler.PacketHandler;
import net.beamlight.remoting.stat.RemotingStats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class NettyClientHandler extends ChannelHandlerAdapter {
    
    private static Logger LOG = LoggerFactory.getLogger(NettyClientHandler.class);
    
    private PacketHandler handler;
    
    public NettyClientHandler(PacketHandler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("Packet handler cannot be null!");
        }
        this.handler = handler;
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        
        RemotingStats.recordRead();
        BeamPacket packet = (BeamPacket) msg;
        
        handler.handle(packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOG.error("Client handler exception!", cause);
        ctx.close();
    }
    
}
