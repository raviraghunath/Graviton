package com.graviton.services;

public class S4 implements Service {
	int price;

	@Override
	public ServiceResult doService(ServiceContext serviceContext) {
		System.out.println("S4 Service");
		return null;
	}

	@Override
	public String getServiceName() {
		return "S4";
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
