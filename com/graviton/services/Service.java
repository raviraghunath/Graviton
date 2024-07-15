package com.graviton.services;

public interface Service {

	ServiceResult doService(ServiceContext serviceContext );
	
	String getServiceName();
	
	int getServicePrice();
	
	void setServicePrice(int price);
}
