package com.training.educationsystem.exceptions;

import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "error")
public class ErrorResponse {
	
	//General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private String status;

    //constructors
	public ErrorResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	//getters and setters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
    
}
