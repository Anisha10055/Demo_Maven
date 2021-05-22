package com.training.educationsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.training.educationsystem.repositories.CourseRepository;
import com.training.educationsystem.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {

	private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	/**
	 * 
	 * @param student
	 * @return boolean value whether the request for registration has been made or not
	 * @throws EmailAlreadyExistsException
	 * @throws UserNameExistException
	 * @throws PasswordAndConfirmPasswordNotMatchException
	 */

	@Override
	public boolean requestRegistration(Student student)
			throws EmailAlreadyExistsException, UserNameExistException, PasswordAndConfirmPasswordNotMatchException
	{

		logger.info("Inside the requestRegistration method of service layer - START");
		boolean isRequestMade = false;

		if (studentRepository.findByEmailId(student.getEmailId()) != null)
		{
			logger.warn("Email already taken!! Please use other email");
			throw new EmailAlreadyExistsException("Email already taken!! Please use other email");
		}
		else if (studentRepository.findByUserName(student.getUserName()) != null)
		{
			logger.warn("Username already taken!! Please use other username");
			throw new UserNameExistException("Username already taken!! Please use other username");
		}
		else if (!(student.getPassword().equals(student.getConfirmPassword())))
		{
			logger.warn("Password and Confirm password doesn't matched");
			throw new PasswordAndConfirmPasswordNotMatchException("Password and Confirm password doesn't matched");
		}
		else
		{
			studentRepository.saveAndFlush(student);
			isRequestMade = true;
		}
		
		logger.info("End of the requestRegistration method of service layer - END");
		return isRequestMade;

	}

	/**
	 * 
	 * @return List of student who has made request for registration in the system
	 */

	@Override
	public List<Student> getAllStudentsRegistrationRequest()
	{
		logger.info("Inside the getAllStudentsRegistrationRequest method of service layer - START");
		List<Student> getAllRegistrationRequest = studentRepository.findByIsValidateFalse();
		
		if(getAllRegistrationRequest.isEmpty())
		{	
			logger.info("End of the getAllStudentsRegistrationRequest method of service layer and returning null - END");
			return null;
			
		}
		else
		{
			logger.info("End of the getAllStudentsRegistrationRequest method of service layer - END");
			return getAllRegistrationRequest;
			
		}
		
		

	}

	/**
	 * 
	 * @param id
	 * @return boolean about whether the student request is approved or not
	 * @throws StudentNotFoundException
	 * @throws EntityNotFoundException
	 */

	@Override
	public boolean getStudentByIdForValidatingRegistration(int id) throws EntityNotFoundException
	{
		logger.info("Inside the getStudentByIdForValidatingRegistration method of service layer - START");
		Student studentObj = studentRepository.getOne(id);
		boolean validated = false;

		if (!(studentObj.isValidate()))
		{
			studentObj.setValidate(true);
			studentObj.setConfirmPassword(studentObj.getPassword());
			studentRepository.save(studentObj);
			validated = true;
			
		} 
		else
		{
			validated = false;
			
		}
		
		logger.info("End of the getStudentByIdForValidatingRegistration method of service layer - END ");
		return validated;
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 * @return boolean about whether the student is allowed to login or not
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@Override
	public boolean validateStudentLogin(String userName, String password)
			throws StudentNotFoundException, RegistrationRequestNotApprovedException
	{

		logger.info("Inside the validateStudentLogin method of the service layer - START");
		boolean isAuthorized = false;
		Student studentObj = studentRepository.findByUserName(userName);
		
		if (studentObj != null)
		{
			if (!(studentObj.isValidate()))
			{
				logger.warn(
						"You are not allowed to login because you're registration request haven't been approved yet");
				throw new RegistrationRequestNotApprovedException(
						"You are not allowed to login because you're registration request haven't been approved yet");
				
			}
			else
			{
				Student student = studentRepository.findByUserNameAndPassword(userName, password);
				
				if (student != null)
				{
					isAuthorized = true;
				} 
				else
				{
					isAuthorized = false;
				}
			}
		} 
		else
		{
			logger.warn("Student not exists!!");
			throw new StudentNotFoundException("Student not exists!!");
			
		}
		
		logger.info("End of the validateStudentLogin method of service layer - END");
		return isAuthorized;
	}

	/**
	 * 
	 * @return list of all the Student details who are validated when this method is called
	 */

	@Override
	public List<Student> viewAllStudentDetails()
	{
		logger.info("Inside the viewAllStudentDetails method of the service layer - START");
		List<Student> studentList = studentRepository.findByIsValidateTrue();
		
		if(studentList != null)
		{
			logger.info("End of the viewAllStudentDetails method of the service layer - END");
			return studentList;
		}
		else
		{
			logger.info("End of the viewAllStudentDetails method of the service layer and returning null is i.e no student available - END");
			return null;
		}
		
		
	}

	/**
	 * 
	 * @param id
	 * @return Individual Student object when this method is called
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@Override
	public Student viewStudentById(int id)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException
	{
		logger.info("Inside the viewStudentById method of the service layer - START");
		Student studentObj = studentRepository.getOne(id);
		
		if (studentObj != null)
		{
			if (!(studentObj.isValidate()))
			{
				logger.warn("This student details can't be shown because it is not validated yet!!");
				throw new RegistrationRequestNotApprovedException(
						"This student details can't be shown because it is not validated yet!!");
				
			} else
			{
				logger.info("End of the viewStudentById method of the service layer - END");
				return studentObj;
				
			}
		}
		else
		{
			logger.warn("Student with given id is not available");
			throw new StudentNotFoundException("Student with given id is not available");
			
		}
	}

	/**
	 * 
	 * @param id
	 * @param studentDTO
	 * @return Individual updated Student Object when this method is called
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException
	 */

	@Override
	public Student updateStudentDetails(int id, StudentDTO studentDTO)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException
	{
		logger.info("Inside the updateStudentDetails method of the service layer - START");
		Student student = studentRepository.getOne(id);
		
		if (!(student.isValidate()))
		{
			logger.warn("This student details can't be shown because it is not validated yet!!");
			throw new RegistrationRequestNotApprovedException(
					"This student details can't be shown because it is not validated yet!!");
			
		}
		else
		{
			student.setFirstName(studentDTO.getFirstName());
			student.setMiddleName(studentDTO.getMiddleName());
			student.setLastName(studentDTO.getLastName());
			student.setEmailId(studentDTO.getEmailId());
			student.setContactNumber(studentDTO.getContactNumber());
			student.setConfirmPassword(student.getPassword());
			
			Student updateStudentObj = studentRepository.save(student);
			
			logger.info("End of the updateStudentDetails method of the service layer - END");
			return updateStudentObj;
			
		}
	}
	
	/**
	 * @param id
	 * @return List of Course object which are enrolled by student
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException 
	 */

	@Override
	public List<Course> viewCourseForStudent(int id)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException, CourseNotFoundException
	{
		logger.info("Inside the viewCourseForStudent method of the service layer - START");
		Student student = studentRepository.getOne(id);

		if (!(student.isValidate()))
		{
			logger.warn("This student details can't be shown because it is not validated yet!!");
			throw new RegistrationRequestNotApprovedException(
					"This student details can't be shown because it is not validated yet!!");
			
		}
		else
		{
			if (student.getCourses().isEmpty())
			{
				logger.info("End of the viewCourseForStudent method of the service layer adn retuning null i.e no courses enrolled- END");
				throw new CourseNotFoundException("No courses enrolled by student yet!!");
				
			}
			
			logger.info("End of the viewCourseForStudent method of the service layer - END");
			return student.getCourses();
			
		}
	}
	
	
	/**
	 * @param id and name
	 * @return Individual Student object whose course are updated or added
	 * @throws EntityNotFoundException
	 * @throws StudentNotFoundException
	 * @throws RegistrationRequestNotApprovedException 
	 * @throws AlreadyEnrolledInCourseException 
	 * @throws CourseNotFoundException 
	 */

	@Override
	public Student updateStudentForCourse(int id, String name) throws EntityNotFoundException, StudentNotFoundException,
			RegistrationRequestNotApprovedException, CourseNotFoundException, AlreadyEnrolledInCourseException
	{
		logger.info("Inside the updateStudentForCourse method of the service layer - START");
		Student student = studentRepository.getOne(id);

		if (student != null)
		{
			if (!(student.isValidate()))
			{
				logger.warn("This student details can't be shown because it is not validated yet!!");
				throw new RegistrationRequestNotApprovedException(
						"This student details can't be shown because it is not validated yet!!");
				
			}
			else
			{
				List<Course> listOfCourses = student.getCourses();
				List<String> courseNameList = listOfCourses.stream().map(e -> e.getCourseName())
						.collect(Collectors.toList());
				
				if (courseNameList.contains(name))
				{
					logger.warn("Already enrolled in the given course!!");
					throw new AlreadyEnrolledInCourseException("Already enrolled in the given course!!");
					
				}
				else
				{
					List<Course> courseList = new ArrayList<Course>();
					Course course = courseRepository.findByCourseName(name);
					
					if (course != null)
					{
						courseList.add(course);
						student.setConfirmPassword(student.getPassword());
						student.setCourses(courseList);
						studentRepository.save(student);
						
						logger.info("End of the updateStudentForCourse method of the service layer - END");
						return student;
						
					}
					else
					{
						logger.warn("Course with the given name is not available!!");
						throw new CourseNotFoundException("Course with the given name is not available!!");
						
					}
				}

			}
		}
		else
		{
			logger.warn("Student with the given ID is not available");
			throw new StudentNotFoundException("Student with the given ID is not available");
			
		}
	}
}
