package com.training.educationsystem.exceptions;

public class PasswordAndConfirmPasswordNotMatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordAndConfirmPasswordNotMatchException(String message) {
		super(message);
	}

}
