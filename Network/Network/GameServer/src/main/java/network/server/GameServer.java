package network.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import network.code.DataObjectDecoder;
import network.code.DataObjectEncoder;

public class GameServer {
	private final int port;
	
	public GameServer(int aPort) {
		this.port = aPort;
	}
	
	public static void main(String[] args) throws Exception{
		new GameServer(8888).start();
	}
	
	public void start() throws Exception{
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)                              
             .channel(NioServerSocketChannel.class)       
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                	 ch.pipeline().addLast(new ObjectEncoder());
                     ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null)));
                     ch.pipeline().addLast(new ServerMainHandler());
                 }
             });

            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println(GameServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully().sync();
            work.shutdownGracefully().sync();
        }
	}
}
