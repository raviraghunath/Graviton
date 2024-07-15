package com.graviton.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceFactory {

	private final Map<String, Service> serviceMap;

	public ServiceFactory(List<Service> services) {
		serviceMap = new HashMap<String, Service>();
		for (Service service : services) {
			serviceMap.put(service.getServiceName(), service);
		}
	}

	public Service getService(String serviceName) {
		if (serviceMap.containsKey(serviceName)) {
			return serviceMap.get(serviceName);
		} else {
			throw new IllegalArgumentException("Unexpected value: " + serviceName);
		}
	}

}
