package com.cnge06.cne.protobuf;

import java.lang.reflect.ParameterizedType;

import com.google.protobuf.GeneratedMessage;
import com.msg.Msg.User;


public class UserRequest implements RequestFace<User> {
	public void request(User request) {
		// TODO Auto-generated method stub
		System.out.println("success:"+request.getID());
		System.out.println("success:"+request.getUserName());
		System.out.println("success:"+request.getPassword());
		System.out.println("success:"+request.getAbc());
	}
	
	public static void main(String[] args) {
		RequestFace<? extends GeneratedMessage> a=new UserRequest();
		ParameterizedType pt =  (ParameterizedType)a.getClass().getGenericInterfaces()[0];
        System.out.println(pt.getActualTypeArguments().length);  
        System.out.println(pt.getActualTypeArguments()[0]);  
	}
}
