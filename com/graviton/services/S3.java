package com.graviton.services;

public class S3 implements Service {

	int price;

	@Override
	public ServiceResult doService(ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServiceName() {
		return "S3";
	}
	
	@Override
	public int getServicePrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public void setServicePrice(int price) {
		this.price = price;
		
	}

}