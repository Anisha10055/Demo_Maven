package com.training.educationsystem.controller;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.dto.StudentDTO;
import com.training.educationsystem.entities.Course;
import com.training.educationsystem.entities.Student;
import com.training.educationsystem.exceptions.AlreadyEnrolledInCourseException;
import com.training.educationsystem.exceptions.CourseNotFoundException;
import com.training.educationsystem.exceptions.EmailAlreadyExistsException;
import com.training.educationsystem.exceptions.PasswordAndConfirmPasswordNotMatchException;
import com.training.educationsystem.exceptions.RegistrationRequestNotApprovedException;
import com.training.educationsystem.exceptions.StudentNotFoundException;
import com.training.educationsystem.exceptions.UserNameExistException;
import com.training.educationsystem.services.StudentServiceImpl;

@RestController
@RequestMapping("/api/educationsystem")
public class StudentController {

	private final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentServiceImpl studentService;

	/**
	 * 
	 * @param Student
	 * This controller function method takes Student object
	 * @return ResponseEntity object about whether the registration request has been
	 *         made or not
	 * @throws EmailAlreadyExistsException
	 * @throws UserNameExistException
	 */

	@PostMapping(value = "/request-registration")
	public ResponseEntity<String> makeRegistration(@Valid @RequestBody final Student student)
			throws EmailAlreadyExistsException, UserNameExistException, PasswordAndConfirmPasswordNotMatchException
	{
		logger.info(
				"This is inside the makeRegistration method of the controller which will let the student to make registration request in education system application- START");
		boolean isRequestMade = studentService.requestRegistration(student);
		
		if (isRequestMade) {
			
			logger.info("End of makeRegistration method- END");
			return new ResponseEntity<String>("Registration request has been made", HttpStatus.OK);
			
		} 
		else 
		{
			logger.info("End of makeRegistration method - END");
			return new ResponseEntity<String>("Registration request cannot be made", HttpStatus.BAD_REQUEST);
			
		}

	}

	/**
	 * This controller method will fetch all the students from the database whose
	 * registration request has not been approved yet
	 * 
	 * @return List of Student entity
	 */

	@GetMapping(value = "/get-all-students")
	public ResponseEntity<List<Student>> getAllRegistrationRequest() 
	{
		logger.info("Inside the getAllRegistrationRequest method of student controller - START");
		logger.info(
				"This method will let the admin to see which student had made an request to registration in education system");
		List<Student> listOfStudent = studentService.getAllStudentsRegistrationRequest();
		
		logger.info("End of getAllRegistrationRequest controller method - END");
		return new ResponseEntity<List<Student>>(listOfStudent, HttpStatus.OK);
		
	}

	/**
	 * 
	 * @param id
	 * @return Response Entity object of string about the status of student
	 *         registration request approval
	 * @throws EntityNotFoundException
	 */

	@PatchMapping(value = "/approve-student-request/{id}")
	public ResponseEntity<String> approveStudentRequest(@PathVariable("id") int id) throws EntityNotFoundException 
	{
		logger.info("Inside the approveStudentRequest method - START");
		boolean approveStudentRequest = studentService.getStudentByIdForValidatingRegistration(id);
		
		if (approveStudentRequest)
		{
			logger.info("Student is valid user controller method - END");
			return new ResponseEntity<String>("Student with Id: " + id + " is validated!", HttpStatus.OK);
			
		} 
		else
		{
			logger.info("Student is already validated controller method - END");
			return new ResponseEntity<String>("Student is already validated!", HttpStatus.OK);
			
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return Response Entity object of string about whether the student is allowed
	 *         to login or not
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@GetMapping(value = "/student-login")
	public ResponseEntity<String> studentLogin(@RequestParam("username") String username,
			@RequestParam("password") String password)
			throws StudentNotFoundException, RegistrationRequestNotApprovedException
	{
		logger.info("Inside the studentLogin method - START");
		boolean validateLogin = studentService.validateStudentLogin(username, password);
		
		if (validateLogin)
		{
			logger.info("Login is successsful controller method... - END");
			return new ResponseEntity<String>("Login Successful!! Welcome to the education system!!", HttpStatus.OK);
			
		} 
		else
		{
			logger.info("Login Failed controller method - END");
			return new ResponseEntity<String>("Invalid username and password!", HttpStatus.BAD_REQUEST);
			
		}

	}

	/**
	 * 
	 * @return List of students which are registered in the education system
	 */
	
	@GetMapping(value = "/view-all-students")
	public ResponseEntity<List<Student>> viewAllStudentDetails()
	{
		logger.info("Inside the viewAllStudentDetails method - START");
		List<Student> listofStudent = studentService.viewAllStudentDetails();
		
		logger.info("Returning the Student Entity controller method - END");
		return new ResponseEntity<List<Student>>(listofStudent, HttpStatus.OK);
		
	}

	/**
	 * 
	 * @param id
	 * @return StudentDTO object of a single student
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@GetMapping(value = "/view-single-validated-student/{id}")
	public ResponseEntity<StudentDTO> getIndividualValidatedStudentDetails(@PathVariable("id") int id)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException
	{
		logger.info(
				"This is inside the view single validated method which will get the individual student details who have validated - START");
		Student student = studentService.viewStudentById(id);
		
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(student.getStudentId());
		studentDTO.setFirstName(student.getFirstName());
		studentDTO.setMiddleName(student.getMiddleName());
		studentDTO.setLastName(student.getLastName());
		studentDTO.setEmailId(student.getEmailId());
		studentDTO.setContactNumber(student.getContactNumber());
		
		logger.info("End of view validated method and returning the student object controller method - END");
		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
		
	}

	/**
	 * 
	 * @param id
	 * @param studentDTO
	 * @return Updated Student object in response
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@PatchMapping(value = "/update-student-details/{id}")
	public ResponseEntity<Student> updateStudentDetails(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException
	{

		logger.info("Inside the updateStudentDetails method - START");
		Student student = studentService.updateStudentDetails(id, studentDTO);
		
		logger.info("Returning the Student response Entity from updateStudentDetails controller method - END");
		return new ResponseEntity<Student>(student, HttpStatus.OK);
		
	}

	/**
	 * 
	 * @param id
	 * @return List of Course object of course enrolled by student
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@GetMapping(value = "/get-courses-enrolled/{id}")
	public List<Course> getCourseEnrolledByStudent(@PathVariable("id") int id)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException, CourseNotFoundException
	{
		logger.info("Inside the getCourseEnrolledByStudent method - START");
		List<Course> listOfCourses = studentService.viewCourseForStudent(id);
		
		logger.info("Returning the list of courses from getCoursesEnrolledByStudent method controller method- END");
		return listOfCourses;
	
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @return List of Course object when student course is updated
	 * @throws RegistrationRequestNotApprovedException
	 * @throws StudentNotFoundException
	 * @throws EntityNotFoundException
	 * @throws AlreadyEnrolledInCourseException
	 */

	@PatchMapping(value = "/update-student-course")
	public List<Course> updateStudentCourse(@RequestParam("studentId") int id, @RequestParam("courseName") String name)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException,
			CourseNotFoundException, AlreadyEnrolledInCourseException
	{
		logger.info("Inside the updateStudentCourse method - START");
		Student updateCourse = studentService.updateStudentForCourse(id, name);
		
		logger.info("Returning the list of courses from updateStudentCourse controller method - END");
		return updateCourse.getCourses();
		
	}

}
