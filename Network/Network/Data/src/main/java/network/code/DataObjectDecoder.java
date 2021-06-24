package network.code;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class DataObjectDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    	//将ByteBuf转为byte []
    	byte [] bytes = new byte[in.readableBytes()];
    	in.readBytes(bytes);
    	//将byte [] 反序列化
    	Object inputObject = null;
    	try (ObjectInputStream objStream = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            inputObject = objStream.readObject();
            out.add(inputObject);
        } catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
