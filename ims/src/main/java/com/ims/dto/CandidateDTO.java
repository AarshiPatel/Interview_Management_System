package com.ims.dto;

import java.time.LocalDate;

import com.ims.entity.Interview;


public class CandidateDTO {

	private long candidateId;
	private String candidateName;
	private String address;
	private String contactNumber;
	private String qualification;
	
	private LocalDate dateOfBirth;
    private String emailId;
	private String username;
	private int round;
	private float experience;
	private String gender;
	private String candidateDepartment;
	private String candidateDesignation;
	private String interviewResult;
	private int flag;
	private String candidateSkill;
	private String candidateGroup;
	
	 private byte[] resume;
	 private String filepath;
	 
	 private Interview interview;
	 
	public Interview getInterview() {
		return interview;
	}

	
	
	public String getCandidateGroup() {
		return candidateGroup;
	}



	public void setCandidateGroup(String candidateGroup) {
		this.candidateGroup = candidateGroup;
	}



	public void setInterview(Interview interview) {
		this.interview = interview;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getInterviewResult() {
		return interviewResult;
	}
	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getCandidateSkill() {
		return candidateSkill;
	}

	public void setCandidateSkill(String candidateSkill) {
		this.candidateSkill = candidateSkill;
	}
	
	
	
}
