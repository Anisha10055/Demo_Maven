package com.training.educationsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "progress_table")
public class Progress {
	@Id
	@Column(name = "progress_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int progressId;

	@Column(name = "completed_hours")
	private int completedHours;

	public Progress() {
		super();
	}

	public Progress(int progressId, int completedHours) {
		super();
		this.progressId = progressId;
		this.completedHours = completedHours;
	}

	public int getProgressId() {
		return progressId;
	}

	public void setProgressId(int progressId) {
		this.progressId = progressId;
	}

	public int getCompletedHours() {
		return completedHours;
	}

	public void setCompletedHours(int completedHours) {
		this.completedHours = completedHours;
	}

	@Override
	public String toString() {
		return "Progress [progressId=" + progressId + ", completedHours=" + completedHours + "]";
	}

}
