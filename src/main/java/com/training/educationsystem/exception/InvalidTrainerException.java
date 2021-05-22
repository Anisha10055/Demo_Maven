package com.training.educationsystem.exception;

public class InvalidTrainerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public InvalidTrainerException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "TrainerException [message=" + message + "]";
	}
	
	

}
