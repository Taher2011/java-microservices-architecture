package com.globallogic.microservices.constant;

public enum ErrorMessage {

	ERR_11_MESSAGE("Invalid input parameter"), ERR_12_MESSAGE("Authentication Error"),
	ERR_13_MESSAGE("Object not found"), ERR_14_MESSAGE("Internal Server Error");

	private final String message;

	private ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
