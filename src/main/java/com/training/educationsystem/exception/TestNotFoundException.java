package com.training.educationsystem.exception;

public class TestNotFoundException extends Exception{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public TestNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "TestNotFoundException [message=" + message + "]";
	}
     
     
}
