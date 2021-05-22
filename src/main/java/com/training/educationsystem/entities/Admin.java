package com.training.educationsystem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "admin_table")
public class Admin {

	@Id
	@Column(name = "admin_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;

	@Column(name = "admin_username")
	private String userName;

	@Column(name = "admin_password")
	private String password;

	@Column(name = "message_id")
	@OneToMany(targetEntity = Message.class)
	private List<Message> messageId;

	public Admin() {
		super();
	}

	public Admin(int adminId, String userName, String password, List<Message> messageId) {
		super();
		this.adminId = adminId;
		this.userName = userName;
		this.password = password;
		this.messageId = messageId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Message> getMessageId() {
		return messageId;
	}

	public void setMessageId(List<Message> messageId) {
		this.messageId = messageId;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", userName=" + userName + ", password=" + password + ", messageId="
				+ messageId + "]";
	}

}

