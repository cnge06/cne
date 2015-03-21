package com.cnge06.cne.protobuf.generic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

public class MyFrameEncoder extends MessageToByteEncoder<MessageLiteOrBuilder> {

	@Override
	protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, ByteBuf out)
			throws Exception {
		if (msg instanceof MessageLite) {
			MessageLite ml=(MessageLite) msg;
			byte[] data=ml.toByteArray();
			out.writeInt(data.length);
	        out.writeBytes(data);
            return;
        }
        if (msg instanceof MessageLite.Builder) {
        	MessageLite.Builder mb=(MessageLite.Builder) msg;
        	byte[] data=mb.build().toByteArray();
        	out.writeInt(data.length);
            out.writeBytes(data);
        }
		/*byte[] data = protocol.toBuilder().build().toByteArray();
        out.writeInt(data.length);
        out.writeBytes(data);*/
		
	}

}
