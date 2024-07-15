package com.graviton.process;

public class ProcessStatus {

	private PROCESS_STATUS status;

	private String statusMessage;

	private Object resultObj;

	public enum PROCESS_STATUS {
		SUCCESS, FAILURE;
	}

	public ProcessStatus(PROCESS_STATUS status, String statusMessage) {
		this.status = status;
		this.statusMessage = statusMessage;
	}

	public ProcessStatus(PROCESS_STATUS status, Object resultObj) {
		this.status = status;
		this.resultObj = resultObj;
	}

	public ProcessStatus(PROCESS_STATUS status, String statusMessage, Object resultObj) {
		this.status = status;
		this.statusMessage = statusMessage;
		this.resultObj = resultObj;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public PROCESS_STATUS getStatus() {
		return status;
	}

	public void setStatus(PROCESS_STATUS status) {
		this.status = status;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}
}
