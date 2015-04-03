package com.cnge06.cne.example.protobuf.m;

import io.netty.channel.Channel;

import com.cnge06.cne.protobuf.generic.Response;
import com.msg.Msg.User;

public class UserHandler {
	public void request(User request,Channel channel) {
		System.out.println("success:"+request.getID());
		System.out.println("success:"+request.getUserName());
		System.out.println("success:"+request.getPassword());
		System.out.println("success:"+request.getAbc());
		Response.write(request,channel,"user");
		
	}
}
