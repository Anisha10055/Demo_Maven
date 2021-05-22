package com.training.educationsystem.exceptions;

public class EmailAlreadyExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistsException() {
		super();
	}
	
	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
