package com.ims.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Class Interview.
 */
@Entity
@Table(name = "interview")
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long interviewId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate interviewDate;

	@JsonFormat(pattern = "hh:mm")
	private LocalTime interviewTime;

	@Column(name = "interviewerDesignation")
	private String interviewerDesignation;

	@Column(name = "interviewerDepartment")
	private String interviewerDepartment;

	@Column(name = "interviewRound")
	private String interviewRound;

	@Column(name = "interviewStatus")
	private String interviewStatus;

	@Column(name = "interviewVenue")
	private String interviewVenue;

	@OneToOne
	@JoinColumn(name = "userDetailsId")
	private UserDetails interviewer;

	@Column(name = "flag")
	private int flag;

	public String getInterviewVenue() {
		return interviewVenue;
	}

	public void setInterviewVenue(String interviewVenue) {
		this.interviewVenue = interviewVenue;
	}

	public Interview() {
		super();
	}

	public String getInterviewerDepartment() {
		return interviewerDepartment;
	}

	public void setInterviewerDepartment(String interviewerDepartment) {
		this.interviewerDepartment = interviewerDepartment;
	}

	public long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(long interviewId) {
		this.interviewId = interviewId;
	}

	public LocalDate getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}

	public LocalTime getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(LocalTime interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getInterviewerDesignation() {
		return interviewerDesignation;
	}

	public void setInterviewerDesignation(String interviewerDesignation) {
		this.interviewerDesignation = interviewerDesignation;
	}

	public String getInterviewRound() {
		return interviewRound;
	}

	public void setInterviewRound(String interviewRound) {
		this.interviewRound = interviewRound;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public UserDetails getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(UserDetails interviewer) {
		this.interviewer = interviewer;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
