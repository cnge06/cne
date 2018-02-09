package com.cnge06.cne.protobuf.generic;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessage;
import com.msg.Msg.Protocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class DispatcherHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Protocol p = (Protocol)msg;
		String path=p.getPath();
		ByteString data=p.getData();
		Channel channel=ctx.channel();
		RequestFace<? extends GeneratedMessage> rf=MappingManager.getRequestFace(path);
		ParameterizedType pt = (ParameterizedType) rf.getClass().getGenericInterfaces()[0]; 
		Type type=pt.getActualTypeArguments()[0];//GeneratedMessage type->User type
		Object dataObject=((Class<?>)type).getMethod("parseFrom", ByteString.class).invoke(null, data);
		rf.getClass().getMethod("request", dataObject.getClass(),Channel.class).invoke(rf, dataObject,channel);
	}
}
