package com.training.educationsystem.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message_table")
public class Message {
	@Id
	@Column(name = "message_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int messageId;

	@Column(name = "message_description")
	private String messageDescription;

	@Column(name = "message_date")
	private LocalDate messageDate;

	public Message() {
		super();
	}

	public Message(int messageId, String messageDescription, LocalDate messageDate) {
		super();
		this.messageId = messageId;
		this.messageDescription = messageDescription;
		this.messageDate = messageDate;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageDescription() {
		return messageDescription;
	}

	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}

	public LocalDate getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(LocalDate messageDate) {
		this.messageDate = messageDate;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageDescription=" + messageDescription + ", messageDate="
				+ messageDate + "]";
	}

}
