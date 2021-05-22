package com.training.educationsystem.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course_table")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_Id", nullable = false)
	private int courseId;

	@Column(name = "course_name", nullable = false)
	private String courseName;

	@Column(name = "student_fk", nullable = true)
	//@ManyToMany(cascade = CascadeType.ALL)
	@ManyToMany(targetEntity = Student.class)
	private List<Student> students;

	@Column(name = "trainer_fk", nullable = true)
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	private List<Trainer> trainers;

	@Column(name = "hours")
	private float hours;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "test_fk")
	private Test test;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_fk")
	private Payment payment;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "progress_id", nullable = true)
	private Progress progress;

	public Course() {
		super();
	}

	public Course(int courseId, String courseName, List<Student> students, List<Trainer> trainers, float hours,
			Test test, Payment payment, Progress progress) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.students = students;
		this.trainers = trainers;
		this.hours = hours;
		this.test = test;
		this.payment = payment;
		this.progress = progress;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(List<Trainer> trainers) {
		this.trainers = trainers;
	}

	public float getHours() {
		return hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", students=" + students + ", trainers="
				+ trainers + ", hours=" + hours + ", test=" + test + ", payment=" + payment + ", progress=" + progress
				+ "]";
	}

}

