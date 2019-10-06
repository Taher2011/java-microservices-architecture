package com.globallogic.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

	@JsonProperty("errorCode")
	private Integer errorCode;

	@JsonProperty("errorMessage")
	private String errorMessage;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
