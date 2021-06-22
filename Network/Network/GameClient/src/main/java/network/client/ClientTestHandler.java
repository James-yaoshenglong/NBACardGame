package network.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import network.data.LoginData;

public class ClientTestHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
    	ctx.writeAndFlush(encode(new LoginData("hhh","hhh")));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {         
        cause.printStackTrace();
        ctx.close();
    }
    
    private ByteBuf encode(Object obj) {
    	byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
    	return buf;
    }
}

