package com.cnge06.cne.example.protobuf.m;

import com.cnge06.cne.protobuf.m.ServerStart;


public class Server {
	public static void main(String[] args) throws InterruptedException, SecurityException, NoSuchMethodException {
		Mapping.registerPath();
		ServerStart.start();
	}
}
