package com.graviton.services;

public class ServiceResult {

	private SERVICE_STATUS status;

	private String statusMessage;

	public enum SERVICE_STATUS {
		SUCCESS, FAILURE;
	}

	public ServiceResult(SERVICE_STATUS status, String statusMessage) {
		this.status = status;
		this.statusMessage = statusMessage;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public SERVICE_STATUS getStatus() {
		return status;
	}

	public void setStatus(SERVICE_STATUS status) {
		this.status = status;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
