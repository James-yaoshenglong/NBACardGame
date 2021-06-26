package network.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.Channel;
import network.code.DataObjectDecoder;
import network.code.DataObjectEncoder;
import network.data.ClientInterface;
import network.data.ResponseOperation;
import network.data.TransferedData;

public class GameClient implements ClientInterface{
	private static GameClient instance = new GameClient("127.0.0.1", 8888);
	
	private GameClient(String host, int port) {
        this.host = host;
        this.port = port;
    } 
	
	public static GameClient getInstance() {
		return instance;
	}
	
	private final String host;
    private final int port;
    private Channel channel;
    private ResponseOperation operation;


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
                     ch.pipeline().addLast(new DataObjectEncoder());
                     ch.pipeline().addLast(new DataObjectDecoder());
                     ch.pipeline().addLast(new ClientTestHandler());
                 }
             });

            ChannelFuture f = b.connect().sync();      
//            f.channel().closeFuture().sync();
            channel = f.channel();
        } finally {
//            group.shutdownGracefully().sync();     
//            System.out.println("closed");
        }
    }

    public void transportData(TransferedData data){
        channel.writeAndFlush(data);
    }
    
    public void registerOperation(ResponseOperation op) {
    	this.operation = op;
    }
    
    @Override
    public ResponseOperation getOperation() {
    	return operation;
    }

    public static void main(String[] args) throws Exception {
        GameClient.getInstance().start();
    }
}

