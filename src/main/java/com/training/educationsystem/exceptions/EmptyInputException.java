package com.training.educationsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyInputException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmptyInputException(String str) {
		 super(str);
	 }

}
