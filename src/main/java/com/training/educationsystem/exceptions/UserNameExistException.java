package com.training.educationsystem.exceptions;
public class UserNameExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameExistException() {
		super();
	}
	
	public UserNameExistException(String message) {
		super(message);
	}
	
}
