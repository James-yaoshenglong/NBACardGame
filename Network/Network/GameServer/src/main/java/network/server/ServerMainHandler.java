package network.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerMainHandler extends ChannelInboundHandlerAdapter{	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		System.out.println("New client ["+ctx.channel().remoteAddress()+"] in");
	}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Server received new message");
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
    	System.out.println("client ["+ctx.channel().remoteAddress()+"] out");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
