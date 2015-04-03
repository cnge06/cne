package com.cnge06.cne.protobuf.generic;

import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.GeneratedMessage;

/**
 * MappingManager
 * 
 * @author junmeng04
 * 
 */
public class MappingManager {
	private static Map<String, RequestFace<? extends GeneratedMessage>> map = new HashMap<String, RequestFace<? extends GeneratedMessage>>() ;

	
	
	public static Map<String, RequestFace<? extends GeneratedMessage>> getMap() {
		return map;
	}

	public static void setMap(
			Map<String, RequestFace<? extends GeneratedMessage>> map) {
		MappingManager.map = map;
	}

	public static RequestFace<? extends GeneratedMessage> getRequestFace(String path) {
		return map.get(path);
	}
	public static void registerPath(String path,RequestFace<? extends GeneratedMessage> requestFace){
		map.put(path, requestFace);
	}
}
