package com.prgrms.boardjpa.commons.api;

public class ErrorResponse {
	private final String errorMessage;

	public ErrorResponse(Throwable throwable) {
		this.errorMessage = throwable.getMessage();
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}