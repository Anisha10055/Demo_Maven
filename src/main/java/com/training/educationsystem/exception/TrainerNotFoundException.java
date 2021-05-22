package com.training.educationsystem.exception;

public class TrainerNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public TrainerNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "TrainerNotFoundException [message=" + message + "]";
	}
	

}
