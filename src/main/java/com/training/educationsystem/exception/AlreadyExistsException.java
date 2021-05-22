package com.training.educationsystem.exception;

public class AlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;
	public AlreadyExistsException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String toString() {
		return "AlreadyExistsException [message=" + message + "]";
	}
	

}
