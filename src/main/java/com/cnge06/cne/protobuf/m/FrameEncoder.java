package com.cnge06.cne.protobuf.m;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;

public class FrameEncoder extends MessageToByteEncoder<MessageLiteOrBuilder> {

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
	}

}
