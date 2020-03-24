package com.ims.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class CandidateResponseDTO {

	private String candidateName;
	private long candidateId;
	private LocalDate interviewDate;
	private LocalTime interviewTime;
	private String interviewerDepartment;
	private String interviewerDesignation;
	private String interviewerName;
	private long userId;
	private int round;
	private String interviewRound;
	private String interviewVenue;
	private long interviewerId;
	private long interviewId;
	
	private String contactNumber;
	private String qualification;
	
	private LocalDate dateOfBirth;
    private String emailId;
	private float experience;
	private String candidateDepartment;
	private String candidateDesignation;
	
	

	public long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(long interviewId) {
		this.interviewId = interviewId;
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

	public String getInterviewRound() {
		return interviewRound;
	}

	public void setInterviewRound(String interviewRound) {
		this.interviewRound = interviewRound;
	}

	public String getInterviewVenue() {
		return interviewVenue;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public void setInterviewVenue(String interviewVenue) {
		this.interviewVenue = interviewVenue;
	}

	public long getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(long interviewerId) {
		this.interviewerId = interviewerId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public String getCandidateDepartment() {
		return candidateDepartment;
	}

	public void setCandidateDepartment(String candidateDepartment) {
		this.candidateDepartment = candidateDepartment;
	}

	public String getCandidateDesignation() {
		return candidateDesignation;
	}

	public void setCandidateDesignation(String candidateDesignation) {
		this.candidateDesignation = candidateDesignation;
	}
	
	
}
