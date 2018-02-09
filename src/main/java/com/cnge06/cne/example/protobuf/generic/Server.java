package com.cnge06.cne.example.protobuf.generic;

import com.cnge06.cne.protobuf.generic.ServerStart;

public class Server {
	public static void main(String[] args) throws InterruptedException {
		Mapping.registerPath();
		ServerStart.start(9090);
	}
}
