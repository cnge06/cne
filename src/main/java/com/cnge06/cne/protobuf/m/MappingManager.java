package com.cnge06.cne.protobuf.m;

import java.util.HashMap;
import java.util.Map;

public class MappingManager {
/**
 * path->mappingObject
 */
private static Map<String,MappingObject> pathMapping = new HashMap<String,MappingObject>();

public static Map<String, MappingObject> getPathMapping() {
	return pathMapping;
}

public static void setPathMapping(Map<String, MappingObject> pathMapping) {
	MappingManager.pathMapping = pathMapping;
}

public static MappingObject getMappingObject(String path) {
	return pathMapping.get(path);
}
public static void registerPath(String path,MappingObject mappingObject){
	pathMapping.put(path, mappingObject);
}
}
