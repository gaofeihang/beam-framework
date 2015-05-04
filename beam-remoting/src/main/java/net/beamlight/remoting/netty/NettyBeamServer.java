package net.beamlight.remoting.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.beamlight.remoting.BeamServer;
import net.beamlight.remoting.handler.PacketHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class NettyBeamServer implements BeamServer {
    
    private static final Logger logger = LoggerFactory.getLogger(NettyBeamServer.class);

    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    
    private PacketHandler handler;
    
    public NettyBeamServer(int port) {
        this.port = port;
    }
    
    @Override
    public void setHandler(PacketHandler handler) {
        this.handler = handler;
    }

    public void start() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        
        ByteBufAllocator allocator = new PooledByteBufAllocator();
        
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            
            .option(ChannelOption.SO_BACKLOG, 128)
            .option(ChannelOption.ALLOCATOR, allocator)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            .childOption(ChannelOption.ALLOCATOR, allocator)
            
            .childHandler(new ChannelInitializer<SocketChannel>() {
                
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyEncoder());
                ch.pipeline().addLast(new NettyDecoder());
                ch.pipeline().addLast(new NettyServerHandler(handler));
            }
        });
        
        try {
            bootstrap.bind(port).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        logger.warn("Netty 5 beam server started! ");
    }

    @Override
    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
