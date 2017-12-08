package com.ts.us.exception;

public class UrbanspoonException extends Exception {

	private String message;

	public UrbanspoonException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UrbanspoonException [message=" + message + "]";
	}

}
