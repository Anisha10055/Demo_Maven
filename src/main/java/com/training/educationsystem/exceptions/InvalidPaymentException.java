package com.training.educationsystem.exceptions;

public class InvalidPaymentException extends Exception {
	private static final long serialVersionUID = 1L;
	public String str;
	public InvalidPaymentException(String str) {
		
		this.str = str;
	}
	@Override
	public String toString() {
		return "InvalidPaymentException [str=" + str + "]";
	}
	

}
