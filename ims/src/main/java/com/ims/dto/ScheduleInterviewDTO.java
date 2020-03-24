package com.ims.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleInterviewDTO {

	private String candidateName;
	private long candidateId;
	private LocalDate interviewDate;
	private LocalTime interviewTime;
//	private String venue;
	private String interviewerDepartment;
	private String interviewerDesignation;
	private String interviewerName;
	private long userId;
	private String round;
	private String interviewRound;
	private String interviewVenue;
	private long interviewerId;
//	private String interviewVenue;
//	private String interviewerDesignation;
//	private String interviewStatus;
//
//	public String getInterviewVenue() {
//		return interviewVenue;
//	}
//
//	public void setInterviewVenue(String interviewVenue) {
//		this.interviewVenue = interviewVenue;
//	}
//
//	public String getInterviewerDesignation() {
//		return interviewerDesignation;
//	}
//
//	public void setInterviewerDesignation(String interviewerDesignation) {
//		this.interviewerDesignation = interviewerDesignation;
//	}

	// private long interviewerId;

//	public long getInterviewerId() {
//		return interviewerId;
//	}
//
//	public void setInterviewerId(long interviewerId) {
//		this.interviewerId = interviewerId;
//	}
//
//	public String getInterviewStatus() {
//		return interviewStatus;
//	}
//
//	public void setInterviewStatus(String interviewStatus) {
//		this.interviewStatus = interviewStatus;
//	}

	public long getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(long interviewerId) {
		this.interviewerId = interviewerId;
	}

	public String getInterviewVenue() {
		return interviewVenue;
	}

	public void setInterviewVenue(String interviewVenue) {
		this.interviewVenue = interviewVenue;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getInterviewRound() {
		return interviewRound;
	}

	public void setInterviewRound(String interviewRound) {
		this.interviewRound = interviewRound;
	}

	public ScheduleInterviewDTO() {

	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
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

//	public String getVenue() {
//		return venue;
//	}
//
//	public void setVenue(String venue) {
//		this.venue = venue;
//	}

	public String getInterviewerDepartment() {
		return interviewerDepartment;
	}

	public void setInterviewerDepartment(String interviewerDepartment) {
		this.interviewerDepartment = interviewerDepartment;
	}

	public String getInterviewerDesignation() {
		return interviewerDesignation;
	}

	public void setInterviewerDesignation(String interviewerDesignation) {
		this.interviewerDesignation = interviewerDesignation;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
