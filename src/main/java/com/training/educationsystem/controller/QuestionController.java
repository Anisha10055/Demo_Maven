package com.training.educationsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Question;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.exceptions.ErrorResponse;
import com.training.educationsystem.exceptions.QuestionException;
import com.training.educationsystem.exceptions.TestException;
import com.training.educationsystem.services.IQuestionService;

/***
 * Implementing Controller for Test 
 * @author Rita
 *
 */
@RestController
@RequestMapping("/api/educationsystem/question")
public class QuestionController {
private static Logger logger = LoggerFactory.getLogger(EducationSystemController.class);

	@Autowired
	IQuestionService questionService;
	
	/***
	 * Implementing EmptyInputException
	 * @param e
	 * @return Error Response
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmptyInputException.class)
	ErrorResponse handlingEmptyException(EmptyInputException e) {
		return new ErrorResponse("Input provided are empty!", "400");
	}
	
	
	/***
	 * Implementing QuestionException
	 * @param e
	 * @return Error Response
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(QuestionException.class)
	ErrorResponse handlingException(QuestionException e) {
		return new ErrorResponse("Questions not found!", "404");
	}

	/***
	 * Add Question Request Handler
	 * @param question for adding question in question_table
	 * @return Response Entity
	 * @throws EmptyInputException 
	 */
	@PostMapping(value="/addQuestion")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) throws EmptyInputException{
		Question addquestion = questionService.addQuestion(question);
		logger.info("Adding Questions...");
		return new ResponseEntity<Question>(addquestion, HttpStatus.OK);
	}
	
	/***
	 * Viewing Question By Taking Input as Id
	 * @param id
	 * @return Question 
	 * @throws QuestionException
	 */
	@GetMapping(value="/getQId")	
	public Question getQuestion(@RequestParam("id") int id) throws QuestionException{
		Question getById = questionService.viewQuestionById(id);
		logger.info("Displaying Question For Id: " + id);
		return getById;
	}
	
	/***
	 * Displaying All Questions from question_table
	 * @return Response Entity
	 * @throws TestException 
	 */
	@GetMapping(value="/getlistQuestion")
	public List<Question> getList() throws QuestionException{
		List<Question> getlist = questionService.viewAllQuestions();
		logger.info("Displaying All Question...");
		if(getlist == null) {
			throw new QuestionException("No question found");
		}
		return getlist;
	}
	
	/***
	 * Updating Questions
	 * @param question
	 * @return Response Entity
	 * @throws EmptyInputException
	 */
	@PostMapping(value="/updatequestion")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) throws EmptyInputException{
		Question updatequestion = questionService.updateQuestion(question);
		logger.info("Updating Question Records for Id: ");
		return new ResponseEntity<Question>(updatequestion, HttpStatus.OK);
	}
	
	/***
	 * Deleting question for a specific Id
	 * @param id
	 * @return
	 * @throws TestException 
	 */
	@DeleteMapping(value="/removeQuestion")
	public Question removeQuestions(@RequestParam("id") int id) throws QuestionException{
		Question removeQuestion = questionService.deleteQuestionById(id);
		logger.info("Removing Question For Id: "+ id);
		return removeQuestion;
	}
}
