package com.cnge06.cne.example.protobuf.m;

import io.netty.channel.Channel;

import com.cnge06.cne.protobuf.m.MappingManager;
import com.cnge06.cne.protobuf.m.MappingObject;
import com.msg.Msg.User;

public class Mapping {
	public static void registerPath() throws SecurityException, NoSuchMethodException{
		MappingObject mappingObject=new MappingObject();
		UserHandler uh=new UserHandler();
		mappingObject.setObject(uh);
		mappingObject.setMethod(uh.getClass().getMethod("request", User.class,Channel.class));
		mappingObject.setArg(User.class);
		MappingManager.registerPath("user", mappingObject);
	}

}
