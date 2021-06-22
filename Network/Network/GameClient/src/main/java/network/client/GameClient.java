package network.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class GameClient {
	private final String host;
    private final int port;

    public GameClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();              
            b.group(group)                       
             .channel(NioSocketChannel.class)        
             .remoteAddress(new InetSocketAddress(host, port)) 
             .handler(new ChannelInitializer<SocketChannel>() {  
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new ClientTestHandler());
                 }
             });

            ChannelFuture f = b.connect().sync();      
            f.channel().closeFuture().sync();        
        } finally {
            group.shutdownGracefully().sync();     
            System.out.println("closed");
        }
    }

    public static void main(String[] args) throws Exception {
        new GameClient("127.0.0.1", 8888).start();
    }
}

