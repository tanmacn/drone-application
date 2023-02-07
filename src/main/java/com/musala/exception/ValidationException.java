package com.musala.exception;

public class ValidationException extends RuntimeException {

	private String displayError;

	public ValidationException(String message) {

		super(message);
		this.displayError = message;
	}

	public ValidationException(String message, String displayError) {

		super(message);
		this.displayError = displayError;
	}

	public String getDisplayError() {

		return displayError;
	}
}
