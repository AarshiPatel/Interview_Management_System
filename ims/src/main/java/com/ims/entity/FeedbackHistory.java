package com.ims.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The Class FeedbackHistory.
 */
@Entity
@Table(name = "feedbackHistory")
public class FeedbackHistory {

	/** The feedback history id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long feedbackHistoryId;

	/** The feedback id. */
	@Column(name = "feedbackId")
	private long feedbackId;

	/** The candidate name. */
	@Column(name = "candidate_name")
	private String candidateName;

	/** The interviewer name. */
	@Column(name = "interviewer_name")
	private String interviewerName;

	/** The date of interview. */
	@Column(name = "dateOfInterview")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfInterview;

	/** The job role. */
	@Column(name = "job_role")
	private String jobRole;

	/** The technical skill. */
	@Column(name = "technical_skill")
	private String technicalSkill;

	/** The education skill. */
	@Column(name = "education_skill")
	private String educationSkill;

	/** The experience. */
	@Column(name = "experience")
	private double experience;

	/** The organizational skill. */
	@Column(name = "organizational_skill")
	private String organizationalSkill;

	/** The communication skill. */
	@Column(name = "communication_skill")
	private String communicationSkill;

	/** The overall rating. */
	@Column(name = "overall_rating")
	private String overallRating;

	/** The review. */
	@Column(name = "review")
	private String review;

	/** The round. */
	@Column(name = "round")
	private int round;

	/** The interview result. */
	@Column(name = "interview_result")
	private String interviewResult;

	/** The next round. */
	@Column(name = "next_round")
	private String nextRound;

	/** The hrleader ability. */
	@Column(name = "hrleader_ability")
	private String hrleaderAbility;

	/** The candidate department. */
	@Column(name = "candidateDepartment")
	private String candidateDepartment;

	/** The candidate designation. */
	@Column(name = "candidateDesignation")
	private String candidateDesignation;
	
	@Column(name = "qualification")
	private String qualification;

	/** The candidate id. */
	@Column(name = "candidateId")
	private long candidateId;

	/** The user id. */
	@Column(name = "userId")
	private long userId;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * Gets the feedback history id.
	 *
	 * @return the feedback history id
	 */
	public long getFeedbackHistoryId() {
		return feedbackHistoryId;
	}

	/**
	 * Sets the feedback history id.
	 *
	 * @param feedbackHistoryId the new feedback history id
	 */
	public void setFeedbackHistoryId(long feedbackHistoryId) {
		this.feedbackHistoryId = feedbackHistoryId;
	}

	/**
	 * Gets the feedback id.
	 *
	 * @return the feedback id
	 */
	public long getFeedbackId() {
		return feedbackId;
	}

	/**
	 * Sets the feedback id.
	 *
	 * @param feedbackId the new feedback id
	 */
	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	/**
	 * Gets the candidate name.
	 *
	 * @return the candidate name
	 */
	public String getCandidateName() {
		return candidateName;
	}

	/**
	 * Sets the candidate name.
	 *
	 * @param candidateName the new candidate name
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	/**
	 * Gets the interviewer name.
	 *
	 * @return the interviewer name
	 */
	public String getInterviewerName() {
		return interviewerName;
	}

	/**
	 * Sets the interviewer name.
	 *
	 * @param interviewerName the new interviewer name
	 */
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	/**
	 * Gets the date of interview.
	 *
	 * @return the date of interview
	 */
	public LocalDate getDateOfInterview() {
		return dateOfInterview;
	}

	/**
	 * Sets the date of interview.
	 *
	 * @param dateOfInterview the new date of interview
	 */
	public void setDateOfInterview(LocalDate dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}

	/**
	 * Gets the job role.
	 *
	 * @return the job role
	 */
	public String getJobRole() {
		return jobRole;
	}

	/**
	 * Sets the job role.
	 *
	 * @param jobRole the new job role
	 */
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	/**
	 * Gets the technical skill.
	 *
	 * @return the technical skill
	 */
	public String getTechnicalSkill() {
		return technicalSkill;
	}

	/**
	 * Sets the technical skill.
	 *
	 * @param technicalSkill the new technical skill
	 */
	public void setTechnicalSkill(String technicalSkill) {
		this.technicalSkill = technicalSkill;
	}

	/**
	 * Gets the education skill.
	 *
	 * @return the education skill
	 */
	public String getEducationSkill() {
		return educationSkill;
	}

	/**
	 * Sets the education skill.
	 *
	 * @param educationSkill the new education skill
	 */
	public void setEducationSkill(String educationSkill) {
		this.educationSkill = educationSkill;
	}

	/**
	 * Gets the experience.
	 *
	 * @return the experience
	 */
	public double getExperience() {
		return experience;
	}

	/**
	 * Sets the experience.
	 *
	 * @param experience the new experience
	 */
	public void setExperience(double experience) {
		this.experience = experience;
	}

	/**
	 * Gets the organizational skill.
	 *
	 * @return the organizational skill
	 */
	public String getOrganizationalSkill() {
		return organizationalSkill;
	}

	/**
	 * Sets the organizational skill.
	 *
	 * @param organizationalSkill the new organizational skill
	 */
	public void setOrganizationalSkill(String organizationalSkill) {
		this.organizationalSkill = organizationalSkill;
	}

	/**
	 * Gets the communication skill.
	 *
	 * @return the communication skill
	 */
	public String getCommunicationSkill() {
		return communicationSkill;
	}

	/**
	 * Sets the communication skill.
	 *
	 * @param communicationSkill the new communication skill
	 */
	public void setCommunicationSkill(String communicationSkill) {
		this.communicationSkill = communicationSkill;
	}

	/**
	 * Gets the overall rating.
	 *
	 * @return the overall rating
	 */
	public String getOverallRating() {
		return overallRating;
	}

	/**
	 * Sets the overall rating.
	 *
	 * @param overallRating the new overall rating
	 */
	public void setOverallRating(String overallRating) {
		this.overallRating = overallRating;
	}

	/**
	 * Gets the review.
	 *
	 * @return the review
	 */
	public String getReview() {
		return review;
	}

	/**
	 * Sets the review.
	 *
	 * @param review the new review
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * Gets the round.
	 *
	 * @return the round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * Sets the round.
	 *
	 * @param round the new round
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/**
	 * Gets the interview result.
	 *
	 * @return the interview result
	 */
	public String getInterviewResult() {
		return interviewResult;
	}

	/**
	 * Sets the interview result.
	 *
	 * @param interviewResult the new interview result
	 */
	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}

	/**
	 * Gets the next round.
	 *
	 * @return the next round
	 */
	public String getNextRound() {
		return nextRound;
	}

	/**
	 * Sets the next round.
	 *
	 * @param nextRound the new next round
	 */
	public void setNextRound(String nextRound) {
		this.nextRound = nextRound;
	}

	/**
	 * Gets the hrleader ability.
	 *
	 * @return the hrleader ability
	 */
	public String getHrleaderAbility() {
		return hrleaderAbility;
	}

	/**
	 * Sets the hrleader ability.
	 *
	 * @param hrleaderAbility the new hrleader ability
	 */
	public void setHrleaderAbility(String hrleaderAbility) {
		this.hrleaderAbility = hrleaderAbility;
	}

	/**
	 * Gets the candidate department.
	 *
	 * @return the candidate department
	 */
	public String getCandidateDepartment() {
		return candidateDepartment;
	}

	/**
	 * Sets the candidate department.
	 *
	 * @param candidateDepartment the new candidate department
	 */
	public void setCandidateDepartment(String candidateDepartment) {
		this.candidateDepartment = candidateDepartment;
	}

	/**
	 * Gets the candidate designation.
	 *
	 * @return the candidate designation
	 */
	public String getCandidateDesignation() {
		return candidateDesignation;
	}

	/**
	 * Sets the candidate designation.
	 *
	 * @param candidateDesignation the new candidate designation
	 */
	public void setCandidateDesignation(String candidateDesignation) {
		this.candidateDesignation = candidateDesignation;
	}

	/**
	 * Gets the candidate id.
	 *
	 * @return the candidate id
	 */
	public long getCandidateId() {
		return candidateId;
	}

	/**
	 * Sets the candidate id.
	 *
	 * @param candidateId the new candidate id
	 */
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FeedbackHistory [feedbackHistoryId=" + feedbackHistoryId + ", feedbackId=" + feedbackId
				+ ", candidateName=" + candidateName + ", interviewerName=" + interviewerName + ", dateOfInterview="
				+ dateOfInterview + ", jobRole=" + jobRole + ", technicalSkill=" + technicalSkill + ", educationSkill="
				+ educationSkill + ", experience=" + experience + ", organizationalSkill=" + organizationalSkill
				+ ", communicationSkill=" + communicationSkill + ", overallRating=" + overallRating + ", review="
				+ review + ", round=" + round + ", interviewResult=" + interviewResult + ", nextRound=" + nextRound
				+ ", hrleaderAbility=" + hrleaderAbility + ", candidateDepartment=" + candidateDepartment
				+ ", candidateDesignation=" + candidateDesignation + ", candidateId=" + candidateId + "]";
	}

}
