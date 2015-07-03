package com.cnge06.cne.json.m;

import com.cnge06.cne.protobuf.m.MappingManager;
import com.cnge06.cne.protobuf.m.MappingObject;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DispatcherHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		JsonProtocol p = (JsonProtocol)msg;
		String path=p.getPath();
		Channel channel=ctx.channel();
		MappingObject mapping=MappingManager.getPathMapping().get(path);
		Class<?> arg=mapping.getArg();
		Object argObject=JsonProtocol.jsonToBean(p.getContent(), arg);
		mapping.getMethod().invoke(mapping.getObject(), argObject,channel);
	}
}
