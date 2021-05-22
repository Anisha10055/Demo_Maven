package com.training.educationsystem.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Course;
import com.training.educationsystem.entities.Student;
import com.training.educationsystem.entities.Test;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exception.AlreadyExistsException;
import com.training.educationsystem.exception.CourseNotFoundException;
import com.training.educationsystem.exception.ErrorMessage;
import com.training.educationsystem.exception.InvalidCourseException;
import com.training.educationsystem.exception.ListEmptyException;
import com.training.educationsystem.exception.TestNotFoundException;
import com.training.educationsystem.services.ICourseService;

/**
 * This is the controller for Course module
 * 
 * @author Afeeda A.H
 *
 */
@RestController
@RequestMapping("/api/educationsystem/course")
public class CourseController {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	ICourseService service;

	/**
	 * This method adds the course in the System
	 * 
	 * @param course
	 * @return Course 
	 * @throws InvalidCourseException
	 */
	@PostMapping("/add-course")
	public Course addCourse(@RequestBody Course course) throws InvalidCourseException {
		logger.info("Add Course (Controller) - START");
		String coursePattern = "^[a-zA-Z0-9_+-]*$";
		if (course.getCourseName() == "") {
			logger.error("Course name cannot be Empty!");
			throw new InvalidCourseException("Course name cannot be Empty!");
		} else if (course.getHours() < 0 || course.getHours() > 10) {
			logger.error("Course duration must be greater than 0 hrs and less than 10 hrs!");
			throw new InvalidCourseException("Course duration must be greater than 0 hrs and less than 10 hrs!");
		} else if (!(Pattern.matches(coursePattern, course.getCourseName()))) {
			logger.error("Course name can only contain alphanumeric characters!");
			throw new InvalidCourseException("Course name can only contain alphanumeric characters!");
		} else {
			logger.info("Adding Course");
			Course addedCourse = service.addCourse(course);
			logger.info("Add Course (Controller) - END");
			return addedCourse;
		}
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidCourseException.class)
	ErrorMessage exceptionHandler(InvalidCourseException e) {
		return new ErrorMessage("400", e.str);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CourseNotFoundException.class)
	ErrorMessage exceptionHandler(CourseNotFoundException e) {
		return new ErrorMessage("404", e.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ListEmptyException.class)
	ErrorMessage exceptionHandler(ListEmptyException e) {
		return new ErrorMessage("404", e.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AlreadyExistsException.class)
	ErrorMessage exceptionHandler(AlreadyExistsException e) {
		return new ErrorMessage("400", e.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(TestNotFoundException.class)
	ErrorMessage exceptionHandler(TestNotFoundException e) {
		return new ErrorMessage("404", e.message);
	}

	/**
	 * This method displays individual courses
	 * 
	 * @param courseId
	 * @return Course 
	 * @throws CourseNotFoundException
	 */
	@GetMapping("/view-course/{courseId}")
	public Course viewCourse(@PathVariable("courseId") int courseId) throws CourseNotFoundException {
		logger.info("View Course (Controller) -START!");
		logger.info("Fetching Course");
		Course course = service.viewCourse(courseId);
		logger.info("View Course  (Controller) -END!");
		return course;
	}

	/**
	 * This method deletes individual courses from the System
	 * 
	 * @param courseId
	 * @return String
	 * @throws CourseNotFoundException 
	 */
	@DeleteMapping("/delete-course/{courseId}")
	public String deleteCourse(@PathVariable("courseId") int courseId) throws CourseNotFoundException {
		logger.info("Delete Course (Controller) - START");
		logger.info("Deleting Course");
		service.deleteCourse(courseId);
		logger.info("Delete Course (Controller) - END");
		return "Course deleted!";
	}

	/**
	 * This method displays list of all courses
	 * 
	 * @return List of courses
	 * @throws ListEmptyException
	 */
	@GetMapping("/view-all-courses")
	public List<Course> viewAllCourses() throws ListEmptyException {
		logger.info("View All Courses (Controller) -START!");
		logger.info("Fetching  Courses");
		List<Course> courseList = service.viewAllCourses();
		logger.info("View All Courses  (Controller) -END!");
		return courseList;
	}

	/**
	 * This method displays available Trainers for individual courses
	 * 
	 * @param courseId
	 * @return List of Trainers
	 * @throws ListEmptyException
	 */
	@GetMapping("/view-trainers/{courseId}")
	public List<Trainer> viewTrainers(@PathVariable("courseId") int courseId) throws ListEmptyException {
		logger.info("View Trainers (Controller) -START!");
		logger.info("Fetching Trainers");
		List<Trainer> trainerList = service.viewTrainers(courseId);
		logger.info("View Trainers (Controller) -END!");
		return trainerList;
	}

	/**
	 * This method displays Students for individual courses
	 * 
	 * @param courseId
	 * @return List of Students
	 * @throws ListEmptyException
	 */
	@GetMapping("/views-students/{courseId}")
	public List<Student> viewStudents(@PathVariable("courseId") int courseId) throws ListEmptyException {
		logger.info("View Students (Controller) -START!");
		logger.info("Fetching Students");
		List<Student> studentList = service.viewStudents(courseId);
		logger.info("View Students (Controller) -END!");
		return studentList;
	}

	/**
	 * This method displays Test for each course
	 * 
	 * @param courseId
	 * @return Test 
	 * @throws TestNotFoundException
	 */
	@GetMapping("/view-test/{courseId}")
	public Test viewTest(@PathVariable("courseId") int courseId) throws TestNotFoundException {
		logger.info("View Test (Controller) -START!");
		logger.info("Fetching test");
		Test test = service.viewTest(courseId);
		logger.info("View Test  (Controller) -END!");
		return test;
	}

	/**
	 * This method adds Trainers in the Course specified
	 * 
	 * @param courseId
	 * @param firstName
	 * @return Course 
	 * @throws InvalidCourseException
	 * @throws CourseNotFoundException
	 * @throws AlreadyExistsException
	 */
	@PatchMapping("/update-trainers")
	public Course updateCourseForTrainers(@RequestParam("courseId") int courseId, @RequestParam("firstName") String firstName)
			throws CourseNotFoundException, InvalidCourseException, AlreadyExistsException {
		logger.info("View All Courses  (Controller) -END!");
		String namePattern = "^[a-zA-Z]+$";
		if (!(Pattern.matches(namePattern, firstName))) {
			throw new InvalidCourseException("Name input can only accept alphabets!");
		} else {
			logger.info("Adding Trainer");
			Course course = service.updateCourseForTrainers(courseId, firstName);
			logger.info("Updating Course for Trainers (Controller) -END!");
			return course;
		}
	}

	/**
	 * This method adds students who have enrolled in the course
	 * 
	 * @param courseId
	 * @param userName
	 * @return Course object
	 * @throws InvalidCourseException
	 * @throws CourseNotFoundException
	 * @throws AlreadyExistsException
	 */
	@PatchMapping("/update-students")
	public Course updateCourseForStudents(@RequestParam("courseId") int courseId, @RequestParam("userName") String userName)
			throws InvalidCourseException, CourseNotFoundException, AlreadyExistsException {
		logger.info("Updating Course for Students (Controller) -START!");
		String namePattern = "^[a-zA-Z]+$";
		if (!(Pattern.matches(namePattern, userName))) {
			throw new InvalidCourseException("Name input can only accept alphabets!");
		} else {
			logger.info("Adding Student");
			Course course = service.updateCourseForStudents(courseId, userName);
			logger.info("Updating Course for Students (Controller) -END!");
			return course;
		}
	}

	/**
	 * This method adds payment in the course after enrollment
	 * 
	 * @param courseId
	 * @param transactionId
	 * @return Course 
	 * @throws CourseNotFoundException
	 */
	@PatchMapping("/update-payment")
	public Course updateCourseForPayment(@RequestParam("courseId") int courseId, @RequestParam("transactionId") int transactionId)
			throws CourseNotFoundException {
		logger.info("Updating Course  for Payment (Controller) -START!");
		logger.info("Adding Payment");
		Course course = service.updateCourseForPayment(courseId, transactionId);
		logger.info("Updating Course for Payment (Controller) -END!");
		return course;
	}

	/**
	 * This method adds Test for each course specified
	 * 
	 * @param courseId
	 * @param testId
	 * @return Course 
	 * @throws CourseNotFoundException
	 */
	@PatchMapping("/update-test")
	public Course updateCourseForTest(@RequestParam("courseId") int courseId, @RequestParam("testId") int testId)
			throws CourseNotFoundException {
		logger.info("Updating Course for  Test (Controller) -START!");
		logger.info("Adding Test");
		Course course = service.updateCourseForTest(courseId, testId);
		logger.info("Updating Course for Test  (Controller) -END!");
		return course;
	}

	/**
	 * This method adds Progress for each Course specified
	 * @param courseId
	 * @param progressId
	 * @return Course
	 * @throws CourseNotFoundException
	 */
	@PatchMapping("/update-progress")
	public Course updateCourseForProgress(@RequestParam("courseId") int courseId, @RequestParam("progressId") int progressId)
			throws CourseNotFoundException {
		logger.info("Updating Course for Progress (Controller) -START!");
		logger.info("Adding Progress");

		Course course = service.updateCourseForProgress(courseId, progressId);
		logger.info("Updating Course for Progress (Controller) -END!");
		return course;
	}
}
