package com.cnge06.cne.protobuf.generic;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.protobuf.GeneratedMessage;
import com.msg.Msg.Protocol;

public class Response {
	private static ConcurrentMap<Channel,Response> responseMap=new ConcurrentHashMap<Channel,Response>();

	private Response(){}

	private Channel channel;
	
	public static Response getResponse(Channel channel){
		Response response = responseMap.get(channel);
		if(response==null){
			response=new Response();
			response.setChannel(channel);
		}
		return response;
	}
	
	public void write(GeneratedMessage gm){
		Protocol.Builder pb=Protocol.newBuilder();
		pb.setData(gm.toByteString());
		pb.setPath("user");
		//Channel channel=getChannel();
		channel.write(pb);
		channel.flush();
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
	
	
}
