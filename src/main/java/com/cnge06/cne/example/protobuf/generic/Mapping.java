package com.cnge06.cne.example.protobuf.generic;

import com.cnge06.cne.protobuf.generic.MappingManager;

public class Mapping {
	public static void registerPath(){
		MappingManager.registerPath("user", new UserRequest());
	}

}
