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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Test;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.exceptions.ErrorResponse;
import com.training.educationsystem.exceptions.TestException;
import com.training.educationsystem.services.ITestService;

/***
 * Implementing Controller for Test 
 * @author Rita
 *
 */
@RestController
@RequestMapping("/api/educationsystem/test")
public class TestController {

private static Logger logger = LoggerFactory.getLogger(EducationSystemController.class);

	
	@Autowired
	ITestService testService;
	
	
	/***
	 * Implementing TestException 
	 * @param e
	 * @return Error Response
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TestException.class)
	ErrorResponse handlingException(TestException e) {
		return new ErrorResponse("Test Not Found", "404");
	}
	
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
	 * Add Test Request Handler
	 * @param test for adding test in test_table
	 * @return Response Entity
	 * @throws EmptyInputException 
	 * @throws Exception 
	 */
	@PostMapping(value= "/addTest")
	public ResponseEntity<Test> addTest(@RequestBody Test test) throws EmptyInputException{
		logger.info("Add Test Method..");
		Test addtest = testService.addTest(test);
		return new ResponseEntity<Test>(addtest, HttpStatus.OK);
	}
	
	/***
	 * Displaying Test By Id
	 * @param id
	 * @return Test
	 * @throws TestException 
	 */
	@GetMapping(value="/get-test-ById")	
	public Test getTest(@RequestParam("id") int id) throws TestException {
		Test getById = testService.getTestById(id);
		logger.info("Displaying Test For Id: " + id);
		return getById;
	}
	
	/***
	 * Displaying All Test from test_table
	 * @return list
	 * @throws TestException 
	 */
	@GetMapping(value="/getlist")	
	public List<Test> getTestList() throws TestException{
		List<Test> gettestlist = testService.getAllList();
		if(gettestlist == null) {
			logger.error("Test Not Found");
			throw new TestException("Test Not Found");
		}
		logger.info("Displaying Test list.....");
		return gettestlist;
	}
	
	/***
	 * Remove Test 
	 * @param id
	 * @return Test 
	 * @throws TestException 
	 */
	@DeleteMapping(value="/remove")
	public ResponseEntity<Test> removeTest(@RequestParam("id") int id) throws TestException{
		Test removeTest = testService.removeTest(id);
		logger.info("Removing Test For Id: "+ id);
		return new ResponseEntity<Test>(removeTest,HttpStatus.OK);
	}
	
	/***
	 * Adding Questions to Test
	 * @param testId, quesId
	 * @return Test
	 * @throws TestException 
	 */
	@PatchMapping("/update-question")
	public Test updateTestForQuestion(@RequestParam("testId") int testId, @RequestParam("quesId") int quesId) throws TestException {
		Test updateTest = testService.updateTestforQuestion(testId, quesId);
		logger.info("Updating Test for Each Question...");
		return updateTest;
	}
}
