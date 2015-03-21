package com.cnge06.cne.example.protobuf.generic;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.google.protobuf.ByteString;
import com.msg.Msg.Protocol;
import com.msg.Msg.User;

public class ClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Protocol.Builder pb=Protocol.newBuilder();
		User.Builder u=User.newBuilder();
		u.setID(5);
		u.setUserName("cnge06");
		u.setPassword("cnge06Password");
		u.setAbc("cnge06ABC");
		pb.setPath("user");
		pb.setData(u.build().toByteString());
		ctx.channel().write(pb.build());
		ctx.channel().flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Protocol p = (Protocol)msg;
		User request=(User) User.class.getMethod("parseFrom", ByteString.class).invoke(null, p.getData());
		System.out.println("success:"+request.getID());
		System.out.println("success:"+request.getUserName());
		System.out.println("success:"+request.getPassword());
		System.out.println("success:"+request.getAbc());
	}
}
