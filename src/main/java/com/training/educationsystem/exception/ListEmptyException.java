package com.training.educationsystem.exception;

public class ListEmptyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public ListEmptyException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ListEmptyException [message=" + message + "]";
	}
	

}
