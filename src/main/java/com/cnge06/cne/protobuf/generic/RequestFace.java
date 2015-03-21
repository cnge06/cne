package com.cnge06.cne.protobuf.generic;

import io.netty.channel.Channel;

import com.google.protobuf.GeneratedMessage;

public interface RequestFace<T extends GeneratedMessage> {
	public void request(T request,Channel channel);
}
