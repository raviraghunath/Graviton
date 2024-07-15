package com.graviton.services;

import java.util.HashMap;
import java.util.Map;

import com.graviton.process.Constants;

public class ServiceManager {

	private final ServiceFactory serviceFactory;

	public ServiceManager(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	private Map<String, Service> services = new HashMap<String, Service>();

	public Service createService(String serviceInfo) {
		String[] serviceInfoSplit = serviceInfo.split(Constants.COLON);

		Service service = serviceFactory.getService(serviceInfoSplit[0]);
		if (null != service) {
			services.put(serviceInfoSplit[0], service);
			service.setServicePrice(Integer.parseInt(serviceInfoSplit[1]));
		}
		return service;
	}

	public Service getService(String serviceName) {
		return services.get(serviceName);
	}

}
