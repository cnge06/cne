package com.cnge06.cne.protobuf.m;

import java.lang.reflect.Method;

public class MappingObject {
	private Object object;//实例
	private Method method;//方法
	private Class<?> arg;//协议参数
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Class<?> getArg() {
		return arg;
	}
	public void setArg(Class<?> arg) {
		this.arg = arg;
	}
	

}
