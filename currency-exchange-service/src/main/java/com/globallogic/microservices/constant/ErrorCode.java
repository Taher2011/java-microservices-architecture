package com.globallogic.microservices.constant;

public enum ErrorCode {

	ERR_11(11), // Invalid input parameter
	ERR_12(12), // Authentication Error
	ERR_13(13), // Object not found
	ERR_14(14); // Internal Server Error

	private final int code;

	private ErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
