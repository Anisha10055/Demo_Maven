package com.training.educationsystem.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Question;
import com.training.educationsystem.entities.Test;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.exceptions.TestException;
import com.training.educationsystem.repositories.QuestionRepository;
import com.training.educationsystem.repositories.TestRepository;

/**
 * Implementing Service Layer for Test
 * 
 * @author Rita
 *
 */
@Transactional
@Service
public class ITestService {
	private static Logger logger = LoggerFactory.getLogger(ITestService.class);

	@Autowired
	TestRepository testRepo;

	@Autowired
	QuestionRepository questionRepo;

	/***
	 * Add Test in test-table
	 * 
	 * @param test
	 * @return test
	 * @throws EmptyInputException
	 */
	public Test addTest(Test test) throws EmptyInputException {
		logger.info("Inside Service Layer for Adding Test...START");
		if (test.getNumberOfAttempts() != 0 && test.getScore() != 0 && test.getTestName() != null) {
			logger.info("Test Added Sucessfully...END");
			testRepo.save(test);
			return test;
		} else {
			logger.error("EmptyInputException occured!....END");
			throw new EmptyInputException("Input provided are empty!");
		}

	}

	/***
	 * Display test By Id
	 * 
	 * @param id
	 * @return test
	 * @throws TestException
	 */
	public Test getTestById(int id) throws TestException {
		logger.info("Inside Service Layer for viewing Test By Id...START");
		Test test = testRepo.findById(id).orElse(null);
		if (test != null) {
			logger.info("Displaying Test..END");
			return test;
		} else {
			logger.error("TestException occured!....END");
			throw new TestException("Test cannot be found!");
		}
	}

	/***
	 * Display list of all test taken from test_table
	 * 
	 * @return test list
	 */
	public List<Test> getAllList() {
		logger.info("Inside Service Layer for viewing all the Test...START");
		List<Test> testList = testRepo.findAll();
		logger.info("Inside Service Layer for viewing all the Test...END");
		return testList;
	}

	/***
	 * Adding questions of Question_table to question field of test_table
	 * 
	 * @param tid
	 * @return test
	 * @throws TestException
	 */
	public Test updateTestforQuestion(int tid, int qid) throws TestException {
		logger.info("Inside Service Layer for Updating the Test for Questions...START");
		Test test = testRepo.findById(tid).orElse(null);
		if (test != null) {
			logger.info("Updating Test By Adding Question...END");
			test = testRepo.getOne(tid);
			Question question = questionRepo.getOne(qid);
			List<Question> q = new LinkedList<Question>(Arrays.asList(question));
			test.setQuestion(q);
			testRepo.save(test);
			logger.info("Saving the test..END");
			return test;
		} else {
			logger.error("TestException occured...END");
			throw new TestException("Test cannot be found!");
		}
	}

	/***
	 * Delete the test by Id
	 * 
	 * @param id
	 * @return test
	 * @throws TestException
	 */
	public Test removeTest(int id) throws TestException {
		logger.info("Inside Service Layer for removing Test By Id...START");
		Test test = testRepo.findById(id).orElse(null);
		if (test != null) {
			logger.info("Removed Test Sucessfully..END");
			test = testRepo.getOne(id);
			testRepo.delete(test);
			logger.info("Deleted Test...END");
			return test;
		} else {
			logger.error("TestException occured...END");
			throw new TestException("Test cannot be found!");
		}
	}

}
