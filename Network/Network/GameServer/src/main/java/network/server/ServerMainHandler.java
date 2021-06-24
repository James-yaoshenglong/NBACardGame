package network.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import network.data.TransferedData;
import network.user.UserPair;

public class ServerMainHandler extends ChannelInboundHandlerAdapter{	
	private UserPair pair;
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		System.out.println("New client ["+ctx.channel().remoteAddress()+"] in");
		pair = new UserPair(ctx.channel());
	}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Server received new message");
        TransferedData input = (TransferedData)msg;
        if(input != null) {
        	input.process(pair);
        }
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
