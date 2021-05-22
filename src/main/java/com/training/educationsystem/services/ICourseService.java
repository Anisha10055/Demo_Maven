package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.Course;
import com.training.educationsystem.entities.Student;
import com.training.educationsystem.entities.Test;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exception.AlreadyExistsException;
import com.training.educationsystem.exception.CourseNotFoundException;
import com.training.educationsystem.exception.ListEmptyException;
import com.training.educationsystem.exception.TestNotFoundException;

public interface ICourseService {

	
	public Course addCourse(Course course);
	public void deleteCourse(int id) throws CourseNotFoundException ;
	public Course viewCourse(int id)throws CourseNotFoundException;
	public List<Course> viewAllCourses() throws ListEmptyException;
	public Course updateCourseForTrainers(int cid,String tname) throws CourseNotFoundException,AlreadyExistsException;
	public Course updateCourseForStudents(int cid,String sname) throws CourseNotFoundException,AlreadyExistsException;
	public Course updateCourseForPayment(int cid,int tid) throws CourseNotFoundException;
	public Course updateCourseForTest(int cid,int tid) throws CourseNotFoundException;
	public Course updateCourseForProgress(int cid,int pid) throws CourseNotFoundException;
	public List<Trainer> viewTrainers(int cid)throws ListEmptyException;
	public List<Student> viewStudents(int cid)throws ListEmptyException;
	public Test viewTest(int cid) throws TestNotFoundException;
}

