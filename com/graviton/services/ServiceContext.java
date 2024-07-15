package com.graviton.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ServiceContext {

	private final Map<String, Object> data;

	public ServiceContext() {
		data = new HashMap<String, Object>();
	}

	public ServiceContext(Map<String, Object> data) {
		this.data = Objects.isNull(data) ? new HashMap<String, Object>() : data;
	}

	public Object getData(String key) {
		return data.get(key);
	}

	public <T> T getData(String key, Class<T> responseType) {
		return (T) getData(key);
	}

	public void putData(String key, Object obj) {
		this.data.put(key, obj);
	}

	public Map<String, Object> getData() {
		return data;
	} 
}
