package com.ims.dto;

import java.time.LocalDate;

public class FeedbackDTO {
	
	private long feedbackId;
	private String candidateName;
	private String interviewerName;
	private LocalDate dateOfInterview;
    private String jobRole;
    private String technicalSkill;	
    private String educationSkill;
    private double experience;	
    private String organizationalSkill;
    private String communicationSkill;
    private String overallRating;	
    private String review;
    private String interviewResult;
    private String nextRound;
    private String hrLeaderAbility;
    private int round;
    
    private long interview;	
    private long interviewer;	 
    private long candidate;
    
    
	public long getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getInterviewerName() {
		return interviewerName;
	}
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}
	
	public LocalDate getDateOfInterview() {
		return dateOfInterview;
	}
	public void setDateOfInterview(LocalDate dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public String getTechnicalSkill() {
		return technicalSkill;
	}
	public void setTechnicalSkill(String technicalSkill) {
		this.technicalSkill = technicalSkill;
	}
	public String getEducationSkill() {
		return educationSkill;
	}
	public void setEducationSkill(String educationSkill) {
		this.educationSkill = educationSkill;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
	public String getOrganizationalSkill() {
		return organizationalSkill;
	}
	public void setOrganizationalSkill(String organizationalSkill) {
		this.organizationalSkill = organizationalSkill;
	}
	public String getCommunicationSkill() {
		return communicationSkill;
	}
	public void setCommunicationSkill(String communicationSkill) {
		this.communicationSkill = communicationSkill;
	}
	public String getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(String overallRating) {
		this.overallRating = overallRating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getInterviewResult() {
		return interviewResult;
	}
	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}
	public String getNextRound() {
		return nextRound;
	}
	public void setNextRound(String nextRound) {
		this.nextRound = nextRound;
	}
	
	public String getHrLeaderAbility() {
		return hrLeaderAbility;
	}
	public void setHrLeaderAbility(String hrLeaderAbility) {
		this.hrLeaderAbility = hrLeaderAbility;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public long getInterview() {
		return interview;
	}
	public void setInterview(long interview) {
		this.interview = interview;
	}
	public long getInterviewer() {
		return interviewer;
	}
	public void setInterviewer(long interviewer) {
		this.interviewer = interviewer;
	}
	public long getCandidate() {
		return candidate;
	}
	public void setCandidate(long candidate) {
		this.candidate = candidate;
	}
	
    
}
