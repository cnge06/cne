package com.cnge06.cne.protobuf.m;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.google.protobuf.ByteString;
import com.msg.Msg.Protocol;

public class DispatcherHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Protocol p = (Protocol)msg;
		String path=p.getPath();
		ByteString data=p.getData();
		Channel channel=ctx.channel();
		MappingObject mapping=MappingManager.getPathMapping().get(path);
		Class<?> arg=mapping.getArg();
		Object dataObject=arg.getMethod("parseFrom", ByteString.class).invoke(null, data);
		mapping.getMethod().invoke(mapping.getObject(), dataObject,channel);
		/*RequestFace<? extends GeneratedMessage> rf=MappingManager.getRequestFace(path);
		ParameterizedType pt = (ParameterizedType) rf.getClass().getGenericInterfaces()[0]; 
		Type type=pt.getActualTypeArguments()[0];//GeneratedMessage type->User type
		Object dataObject=((Class<?>)type).getMethod("parseFrom", ByteString.class).invoke(null, data);
		rf.getClass().getMethod("request", dataObject.getClass(),Channel.class).invoke(rf.getClass().newInstance(), dataObject,channel);*/
	}
}
