package com.training.educationsystem.exceptions;

public class StudyMaterialException extends Exception{
	private static final long serialVersionUID = 1L;
	public String str;
	public StudyMaterialException(String str) {
		
		this.str = str;
	}
	@Override
	public String toString() {
		return "StudyMaterialException [str=" + str + "]";
	}

}
