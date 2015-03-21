package com.cnge06.cne.protobuf.generic;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.google.protobuf.ByteString;
import com.msg.Msg.Protocol;
import com.msg.Msg.User;

public class ClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//super.channelActive(ctx);
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
		//ctx.writeAndFlush(pb.build());
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
		// TODO Auto-generated method stub
		/*super.channelRead(ctx, msg);
		Protocol p = (Protocol)msg;
		String path=p.getPath();
		ByteString data=p.getData();
		
		RequestFace<? extends GeneratedMessage> rf=PathManager.getRequestFace(path,data);
		//T
		ParameterizedType pt = (ParameterizedType) rf.getClass().getGenericSuperclass(); 
		Type type=pt.getActualTypeArguments()[0];
		Object dataObject=type.getClass().getMethod("parseFrom", ByteString.class).invoke(null, data);
		rf.getClass().getMethod("request", (pt.getActualTypeArguments()[0]).getClass()).invoke(null, dataObject);*/
		//rf.request(dataObject);
		
		/*
		// 从path知道是哪个class
		Class<? extends RequestAbstract<? extends GeneratedMessage>> raClz=PathManager.getAb(path);
		// 实例化该class;
		RequestAbstract<? extends GeneratedMessage> ra=raClz.newInstance(); 
		//获取该class对象的泛型参数类型，用其类型对data进行反序列化
		ParameterizedType pt = (ParameterizedType) ra.getClass().getGenericSuperclass(); 
		GeneratedMessage request=(GeneratedMessage)(pt.getActualTypeArguments()[0]).getClass().getMethod("parseFrom", ByteString.class).invoke(null, data);
		ra.setRequest(request);
		ra.getRequest();
		*/
	}
}
