package com.cnge06.cne.example.protobuf.generic;

import io.netty.channel.Channel;

import java.lang.reflect.ParameterizedType;

import com.cnge06.cne.protobuf.generic.RequestFace;
import com.cnge06.cne.protobuf.generic.Response;
import com.google.protobuf.GeneratedMessage;
import com.msg.Msg.User;


public class UserRequest implements RequestFace<User> {
	public void request(User request,Channel channel) {
		// TODO Auto-generated method stub
		System.out.println("success:"+request.getID());
		System.out.println("success:"+request.getUserName());
		System.out.println("success:"+request.getPassword());
		System.out.println("success:"+request.getAbc());
		
		Response.write(request,channel,"user");
		
	}
	
	public static void main(String[] args) {
		RequestFace<? extends GeneratedMessage> a=new UserRequest();
		ParameterizedType pt =  (ParameterizedType)a.getClass().getGenericInterfaces()[0];
        System.out.println(pt.getActualTypeArguments().length);  
        System.out.println(pt.getActualTypeArguments()[0]);  
	}
}
