package com.cnge06.cne.protobuf.generic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import com.msg.Msg.Protocol;

public class MyFrameDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		 if (in.readableBytes() < 4) {  //表示头长度的字节数,int类型，长度为4;
	            return;
	        }
	        in.markReaderIndex();                  //标记一下当前的readIndex的位置
	        int dataLength = in.readInt();       // 读取传送过来的消息的长度。ByteBuf 的readInt()方法会让readIndex增加4
	        if (dataLength < 0) { // 如果消息体长度为0，关闭连接。
	            ctx.close();
	        }
	 
	        if (in.readableBytes() < dataLength) { //消息体长度小于传送的消息长度，则resetReaderIndex，把readIndex重置到mark的地方
	            in.resetReaderIndex();
	            return;
	        }
	        
	        //取数据，转成定义的类型
	        byte[] data = new byte[dataLength];
	        in.readBytes(data);
	        out.add(Protocol.parseFrom(data));
	}

}
