package net.beamlight.remoting.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import net.beamlight.remoting.BeamPacket;
import net.beamlight.remoting.handler.DefaultPacketHandler;
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
public class NettyServerHandler extends ChannelHandlerAdapter {
    
    private static final Logger LOG = LoggerFactory.getLogger(NettyServerHandler.class);
    
    private PacketHandler handler;
    
    public NettyServerHandler(PacketHandler handler) {
        if (handler != null) {
            this.handler = handler;
        } else {
            this.handler = new DefaultPacketHandler();
        }
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        
        RemotingStats.recordRead();
        final BeamPacket reqPacket = (BeamPacket) msg;
        
        BeamPacket respPacket = handler.handle(reqPacket);
        if (respPacket != null) {
            ChannelFuture future = ctx.write(respPacket);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        RemotingStats.recordWrite();
                    }
                }
            });
            ctx.flush();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOG.error("Server handler exception!", cause);
        ctx.close();
    }
    
}
