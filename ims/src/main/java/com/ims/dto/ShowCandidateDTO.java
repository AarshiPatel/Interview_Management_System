package com.ims.dto;

import java.time.LocalDate;



public class ShowCandidateDTO {
	private long candidateId;
	private String candidateName;
	private String contactNumber;
	private String qualification;
	private LocalDate dateOfBirth;
	private String emailId;
	private String candidateDepartment;
	private String candidateDesignation;
	private int round;
	private float experience;
	private String gender;
	private String name;
	private LocalDate interviewDate;
	private String interviewResult;
	private String interviewStatus; 
	private String candidateSkill;
	private Long interviewId;

	
	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getInterviewResult() {
		return interviewResult;
	}

	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}
	
	public String getCandidateSkill() {
		return candidateSkill;
	}

	public void setCandidateSkill(String candidateSkill) {
		this.candidateSkill = candidateSkill;
	}

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	public LocalDate getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
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
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public float getExperience() {
		return experience;
	}
	public void setExperience(float experience) {
		this.experience = experience;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
