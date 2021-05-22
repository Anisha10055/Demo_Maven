package com.training.educationsystem.exceptions;

public class PaymentException extends Exception{

	private static final long serialVersionUID = 1L;
	public String str;
	public PaymentException(String str) {
		
		this.str = str;
	}
	@Override
	public String toString() {
		return "PaymentException [str=" + str + "]";
	}
	
}
