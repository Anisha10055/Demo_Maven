package com.training.educationsystem.exceptions;

public class InvalidStudyMaterialException extends Exception{
	private static final long serialVersionUID = 1L;
	public String str;
	public InvalidStudyMaterialException(String str) {
		
		this.str = str;
	}
	@Override
	public String toString() {
		return "InvalidStudyMaterialException [str=" + str + "]";
	}

}
