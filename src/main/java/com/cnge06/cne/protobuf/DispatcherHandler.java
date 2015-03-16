package com.cnge06.cne.protobuf;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessage;
import com.msg.Msg.Protocol;

public class DispatcherHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//super.channelRead(ctx, msg);
		Protocol p = (Protocol)msg;
		String path=p.getPath();
		ByteString data=p.getData();
		Channel channel=ctx.channel();
		Response response=Response.getResponse(channel);
		RequestFace<? extends GeneratedMessage> rf=PathManager.getRequestFace(path,data);
		ParameterizedType pt = (ParameterizedType) rf.getClass().getGenericInterfaces()[0]; 
		Type type=pt.getActualTypeArguments()[0];//GeneratedMessage type->User type
		Object dataObject=((Class<?>)type).getMethod("parseFrom", ByteString.class).invoke(null, data);
		rf.getClass().getMethod("request", dataObject.getClass(),Response.class).invoke(rf.getClass().newInstance(), dataObject,response);
	}
}
