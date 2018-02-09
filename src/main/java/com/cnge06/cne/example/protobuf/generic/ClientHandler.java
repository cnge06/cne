package com.cnge06.cne.example.protobuf.generic;

import com.google.protobuf.ByteString;
import com.msg.Msg.Protocol;
import com.msg.Msg.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

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
