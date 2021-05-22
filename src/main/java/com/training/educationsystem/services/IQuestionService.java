package com.training.educationsystem.services;

import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Question;
import com.training.educationsystem.exceptions.EmptyInputException;
import com.training.educationsystem.exceptions.QuestionException;
import com.training.educationsystem.exceptions.TestException;
import com.training.educationsystem.repositories.QuestionRepository;

/**
 * Implementing Service Layer for Question
 * 
 * @author Rita
 *
 */
@Transactional
@Service
public class IQuestionService {
	private static Logger logger = LoggerFactory.getLogger(IQuestionService.class);

	@Autowired
	QuestionRepository questionRepo;

	/***
	 * Add Question to question_table
	 * 
	 * @param question
	 * @return question
	 * @throws EmptyInputException
	 */
	public Question addQuestion(Question question) throws EmptyInputException {
		logger.info("Inside Service Layer for Adding Questions...START");
		if (question.getQuestion() != null && question.getOption1() != null && question.getOption2() != null
				&& question.getOption3() != null && question.getOption4() != null
				&& question.getCorrectAnswer() != null) {
			logger.info("Question Added Sucessfully...END");
			questionRepo.save(question);
			logger.info("");
			return question;
		} else {
			logger.error("EmptyInputException occured!..END");
			throw new EmptyInputException("Input provided are empty!");
		}
	}

	/***
	 * We can view question by Id
	 * 
	 * @param id
	 * @return question
	 * @throws QuestionException
	 */
	public Question viewQuestionById(int id) throws QuestionException {
		logger.info("Inside Service Layer for viewing Question By Id...START");
		Question question = questionRepo.findById(id).orElse(null);
		if (question != null) {
			logger.info("View Question By Id...END");
			return question;
		} else {
			logger.error("QuestionException occured!..END");
			throw new QuestionException("Questions not found!!");
		}
	}

	/***
	 * Display all questions from question_table
	 * 
	 * @return list
	 */
	public List<Question> viewAllQuestions() {
		logger.info("Inside Service Layer for viewing All Questions...");
		List<Question> questionList = questionRepo.findAll();
		return questionList;
	}

	/***
	 * Updating Question
	 * 
	 * @param question
	 * @return Question
	 * @throws EmptyInputException
	 */
	public Question updateQuestion(Question question) throws EmptyInputException {
		logger.info("Inside Service Layer for Adding Questions...START");
		if (question.getQuestion() != null && question.getOption1() != null && question.getOption2() != null
				&& question.getOption3() != null && question.getOption4() != null
				&& question.getCorrectAnswer() != null) {
			logger.info("Updating Question...END");
			return questionRepo.findById(question.getQuestionId()).map(ques -> {
				ques.setQuestion(question.getQuestion());
				ques.setOption1(question.getOption1());
				ques.setOption2(question.getOption2());
				ques.setOption3(question.getOption3());
				ques.setOption4(question.getOption4());
				ques.setCorrectAnswer(question.getCorrectAnswer());
				logger.info("Saving the Updated Question...END");
				return questionRepo.save(question);
			}).orElseGet(() -> {
				question.setQuestionId(question.getQuestionId());
				logger.info("Updating the question by new questionId");
				return questionRepo.save(question);
			});
		} else {
			logger.error("EmptyInputException occured!..END");
			throw new EmptyInputException("Input provided are empty!");
		}
	}

	/***
	 * delete any question from question_table
	 * 
	 * @param id
	 * @return question
	 * @throws TestException
	 */
	public Question deleteQuestionById(int id) throws QuestionException {
		logger.info("Inside Service Layer for removing Question By Id...START");
		Question question = questionRepo.findById(id).orElse(null);
		if (question != null) {
			question = questionRepo.getOne(id);
			questionRepo.delete(question);
			logger.info("Deleted Question Sucessfully...END");
			return question;
		} else {
			logger.error("QuestionException occured...END");
			throw new QuestionException("Test cannot be found!");
		}
	}
}
