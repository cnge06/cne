package com.cnge06.cne.json.m;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.google.protobuf.MessageLiteOrBuilder;

public class FrameEncoder extends MessageToByteEncoder<MessageLiteOrBuilder> {

	@Override
	protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, ByteBuf out)
			throws Exception {
		if (msg instanceof JsonProtocol) {
			JsonProtocol jp=(JsonProtocol) msg;
			byte[] data=JsonProtocol.beanToJson(jp).getBytes();
			out.writeInt(data.length);
	        out.writeBytes(data);
            return;
        }
        
	}

}
