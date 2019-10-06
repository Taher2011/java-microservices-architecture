package com.globallogic.microservices.constant;

public enum ErrorMessage {

	ERR_11_MESSAGE("Invalid input parameter"), ERR_12_MESSAGE("${object} does not exist"),
	ERR_13_MESSAGE("${object} not found"), ERR_14_MESSAGE("Internal Server Error");

	private final String message;

	private ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
