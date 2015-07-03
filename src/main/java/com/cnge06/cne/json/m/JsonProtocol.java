package com.cnge06.cne.json.m;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProtocol {
	private static final ObjectMapper mapper = new ObjectMapper();
	private String path;
	private String content;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static ObjectMapper getObjectMapper() {
		return mapper;
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 *            准备转换的对象
	 * @return json字符串
	 * @throws Exception
	 */
	public static String beanToJson(Object obj) throws Exception {
		try {
			ObjectMapper objectMapper = getObjectMapper();
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 将json字符串转换成java对象
	 * @param json 准备转换的json字符串
	 * @param clazz  准备转换的类
	 * @return 
	 * @throws Exception 
	 */
	public static Object jsonToBean(String json, Class<?> clazz) throws Exception {
		try {
		ObjectMapper objectMapper = getObjectMapper();
		Object o = objectMapper.readValue(json, clazz);
		return o;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}
}
