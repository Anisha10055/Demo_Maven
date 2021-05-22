package com.training.educationsystem.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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

public interface IStudentService
{
	public boolean requestRegistration(Student student)
			throws EmailAlreadyExistsException, UserNameExistException, PasswordAndConfirmPasswordNotMatchException;

	public List<Student> getAllStudentsRegistrationRequest();

	public boolean getStudentByIdForValidatingRegistration(int id) throws EntityNotFoundException;

	public boolean validateStudentLogin(String userName, String password)
			throws StudentNotFoundException, RegistrationRequestNotApprovedException;

	public List<Student> viewAllStudentDetails();

	public Student viewStudentById(int id)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException;

	public Student updateStudentDetails(int id, StudentDTO studentDTO)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException;

	public List<Course> viewCourseForStudent(int id)
			throws EntityNotFoundException, StudentNotFoundException, RegistrationRequestNotApprovedException, CourseNotFoundException;

	public Student updateStudentForCourse(int id, String name) throws EntityNotFoundException, StudentNotFoundException,
			RegistrationRequestNotApprovedException, CourseNotFoundException, AlreadyEnrolledInCourseException;

}
