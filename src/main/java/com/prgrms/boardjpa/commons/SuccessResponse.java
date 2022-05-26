package com.prgrms.boardjpa.commons;

public class SuccessResponse<T> {
	private final T data;

	private SuccessResponse(T data) {
		this.data = data;
	}

	public static <T> SuccessResponse<T> of(T data) {
		return new SuccessResponse<>(data);
	}

	public T getData() {
		return data;
	}
}