package com.ims.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ims.entity.UserDetails;


public class InterviewResponseDTO {

	private long interviewId;

	private LocalDate interviewDate;

	private LocalTime interviewTime;
	private String interviewerDesignation;

	private String interviewerDepartment;

	private String interviewRound;

	private String interviewStatus;

	private String interviewVenue;

	
	private UserDetails interviewer;


	private int flag;


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


	public String getInterviewerDepartment() {
		return interviewerDepartment;
	}


	public void setInterviewerDepartment(String interviewerDepartment) {
		this.interviewerDepartment = interviewerDepartment;
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


	public String getInterviewVenue() {
		return interviewVenue;
	}


	public void setInterviewVenue(String interviewVenue) {
		this.interviewVenue = interviewVenue;
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
