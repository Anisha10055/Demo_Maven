package com.training.educationsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "registration_table")
public class Registration {

	@Id
	@Column(name = "registration_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int registrationId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "contact_number")
	private Long contactNumber;

	@Column(name = "email_id")
	private String emaildId;

	@Column(name = "student_password")
	private String studentPassword;

	@Transient
	private String studentConfirmPassword;

	public Registration() {
		super();
	}

	public Registration(int registrationId, String firstName, String middleName, String lastName, String userName,
			Long contactNumber, String emaildId, String studentPassword, String studentConfirmPassword) {
		super();
		this.registrationId = registrationId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userName = userName;
		this.contactNumber = contactNumber;
		this.emaildId = emaildId;
		this.studentPassword = studentPassword;
		this.studentConfirmPassword = studentConfirmPassword;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmaildId() {
		return emaildId;
	}

	public void setEmaildId(String emaildId) {
		this.emaildId = emaildId;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentConfirmPassword() {
		return studentConfirmPassword;
	}

	public void setStudentConfirmPassword(String studentConfirmPassword) {
		this.studentConfirmPassword = studentConfirmPassword;
	}

	@Override
	public String toString() {
		return "Registration [registrationId=" + registrationId + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", userName=" + userName + ", contactNumber=" + contactNumber
				+ ", emaildId=" + emaildId + ", studentPassword=" + studentPassword + ", studentConfirmPassword="
				+ studentConfirmPassword + "]";
	}

}
