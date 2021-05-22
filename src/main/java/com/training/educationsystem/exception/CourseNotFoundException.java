package com.training.educationsystem.exception;

public class CourseNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public CourseNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "CourseNotFoundException [message=" + message + "]";
	}
	
	

}
