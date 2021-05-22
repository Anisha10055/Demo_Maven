package com.training.educationsystem.exceptions;

public class DateException extends Exception{
	private static final long serialVersionUID = 1L;
	public String str;
	public DateException(String str) {
		
		this.str = str;
	}
	@Override
	public String toString() {
		return "DateException [str=" + str + "]";
	}
	


}
