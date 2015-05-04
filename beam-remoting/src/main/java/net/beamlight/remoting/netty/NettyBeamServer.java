package net.beamlight.remoting.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.beamlight.remoting.template.AbstractBeamServer;

/**
 * Created on May 4, 2015
 *
 * @author gaofeihang
 * @since 1.0.0
 */
public class NettyBeamServer extends AbstractBeamServer {
    
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    
    public NettyBeamServer(int port) {
        super(port);
    }
    
    public void start() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            
            .option(ChannelOption.SO_BACKLOG, 128)
            .option(ChannelOption.ALLOCATOR, NettyConfig.getAllocator())
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            .childOption(ChannelOption.ALLOCATOR, NettyConfig.getAllocator())
            
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
        
        LOG.warn("Netty beam server started!");
    }

    @Override
    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
