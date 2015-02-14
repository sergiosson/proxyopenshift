package com.gos.veleta;

public class ErrorInfo extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int errorCode;

	public ErrorInfo(int code) {
		this.errorCode = code;

	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
