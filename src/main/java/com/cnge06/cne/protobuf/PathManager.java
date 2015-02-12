package com.cnge06.cne.protobuf;

import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessage;

/**
 * 路径管理器
 * 
 * @author junmeng04
 * 
 */
public class PathManager {
	private static Map<String, RequestFace<? extends GeneratedMessage>> map = new HashMap<String, RequestFace<? extends GeneratedMessage>>() {
		private static final long serialVersionUID = 1L;

		{
			put("user", new UserRequest());
		}
	};

	public static RequestFace<? extends GeneratedMessage> getRequestFace(
			String path, ByteString data) {
		return map.get(path);
	}
}
