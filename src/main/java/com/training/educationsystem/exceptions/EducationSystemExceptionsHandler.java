package com.training.educationsystem.exceptions;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EducationSystemExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * This handler method will validate the student object 
	 * using the javax.validation API
	 * @return ResponseEntity of object about the validation error that has been happened
	 * during the registration request  
	 */

	@Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
	    ErrorMessage errorMessage = new ErrorMessage();
	    errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setErrorMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	  } 
	
	/**
	 * 
	 * @param Object of EmailAlreadyExistsException
	 * @return ResponseEntity of ErrorMessage object
	 */
	
	
	@ExceptionHandler(value = {EmailAlreadyExistsException.class})
	public ResponseEntity<ErrorMessage> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param Object of EmailAlreadyExistsException
	 * @return ResponseEntity of ErrorMessage object
	 */
	
	
	@ExceptionHandler(value = {UserNameExistException.class})
	public ResponseEntity<ErrorMessage> handleUserNameAlreadyExistsException(UserNameExistException ex){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param ex
	 * @return
	 */

	@ExceptionHandler(value = {PasswordAndConfirmPasswordNotMatchException.class})
	public ResponseEntity<ErrorMessage> handlePasswordAndConfirmPassword(PasswordAndConfirmPasswordNotMatchException ex){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	
	@ExceptionHandler(value = {StudentNotFoundException.class})
	public ResponseEntity<ErrorMessage> handleStudentNotFoundException(StudentNotFoundException ex){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	
	@ExceptionHandler(value = {EntityNotFoundException.class})
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){		
		return new ResponseEntity<String>("Student With given Id is not available", HttpStatus.OK);
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	
	@ExceptionHandler(value = {RegistrationRequestNotApprovedException.class})
	public ResponseEntity<ErrorMessage> handleRegistrationNotApprovedException(RegistrationRequestNotApprovedException ex){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.FORBIDDEN.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.FORBIDDEN);
	}
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = {CourseNotFoundException.class})
	public ResponseEntity<ErrorMessage> handleCourseNotFoundException(CourseNotFoundException ex){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = {AlreadyEnrolledInCourseException.class})
	public ResponseEntity<ErrorMessage> handleAlreadyEnrolledInCourseException(AlreadyEnrolledInCourseException ex){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
}
