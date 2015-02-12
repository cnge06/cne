package com.cnge06.cne.protobuf;

import com.google.protobuf.GeneratedMessage;

public interface RequestFace<T extends GeneratedMessage> {
	public void request(T request);
}
