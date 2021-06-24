package network.server;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import io.netty.buffer.ByteBuf;
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
        TransferedData input = (TransferedData)decode(msg);
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
    
    private Object decode(Object msg) {
    	//将msg转为ByteBuf
    	ByteBuf in = (ByteBuf)msg;
    	//将ByteBuf转为byte []
    	byte [] bytes = new byte[in.readableBytes()];
    	in.readBytes(bytes);
    	//将byte [] 反序列化
    	Object inputObject = null;
    	try (ObjectInputStream objStream = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            inputObject = objStream.readObject();
        } catch (Exception e) {
			e.printStackTrace();
		}
    	return inputObject;
    }
}
