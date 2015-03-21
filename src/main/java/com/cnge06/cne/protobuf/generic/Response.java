package com.cnge06.cne.protobuf.generic;

import io.netty.channel.Channel;

import com.google.protobuf.GeneratedMessage;
import com.msg.Msg.Protocol;

public class Response {
	public static void write(GeneratedMessage gm,Channel channel,String path){
		Protocol.Builder pb=Protocol.newBuilder();
		pb.setData(gm.toByteString());
		pb.setPath(path);
		channel.write(pb);
		channel.flush();
	}
	
	
}
