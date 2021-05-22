package com.training.educationsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback_table")
public class FeedBack {
	@Id
	@Column(name = "feedback_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String feedbackId;

	@Column(name = "feedback")
	private String feedback;

	@Column(name = "reply_feedback")
	private String replyFeedBack;

	@Column(name = "grievance")
	private String grievance;

	@Column(name = "reply_grievance")
	private String replyGrievance;

	public FeedBack() {
		super();
	}

	public FeedBack(String feedbackId, String feedback, String replyFeedBack, String grievance, String replyGrievance) {
		super();
		this.feedbackId = feedbackId;
		this.feedback = feedback;
		this.replyFeedBack = replyFeedBack;
		this.grievance = grievance;
		this.replyGrievance = replyGrievance;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getDescription() {
		return feedback;
	}

	public void setDescription(String description) {
		this.feedback = description;
	}

	public String getReplyFeedBack() {
		return replyFeedBack;
	}

	public void setReplyFeedBack(String replyFeedBack) {
		this.replyFeedBack = replyFeedBack;
	}

	public String getGrievance() {
		return grievance;
	}

	public void setGrievance(String grievance) {
		this.grievance = grievance;
	}

	public String getReplyGrievance() {
		return replyGrievance;
	}

	public void setReplyGrievance(String replyGrievance) {
		this.replyGrievance = replyGrievance;
	}

	@Override
	public String toString() {
		return "FeedBack [feedbackId=" + feedbackId + ", feedback=" + feedback + ", replyFeedBack=" + replyFeedBack
				+ ", grievance=" + grievance + ", replyGrievance=" + replyGrievance + "]";
	}

}

