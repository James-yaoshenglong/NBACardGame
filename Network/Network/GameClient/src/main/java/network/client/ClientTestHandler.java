package network.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import network.data.LoginData;
import network.data.LoginResponse;
import network.data.ResponseData;

public class ClientTestHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//    	ctx.writeAndFlush(new LoginData("hhh","hhh"));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object in) {
        System.out.println("Client received: " + in.toString());
        ((ResponseData)in).reProcess(GameClient.getInstance());
//        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {         
        cause.printStackTrace();
        ctx.close();
    }
}

