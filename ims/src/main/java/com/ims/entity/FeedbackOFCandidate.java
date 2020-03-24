package com.ims.entity;

import java.time.LocalDate;

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
 * The Class FeedbackOFCandidate.
 */
@Entity
@Table(name ="feedbackOfCandidate")
public class FeedbackOFCandidate {
	
	/** The feedback id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long feedbackId;
	
	/** The candidate name. */
	@Column(name="candidate_name")
	private String candidateName;
	
	/** The interviewer name. */
	@Column(name="interviewer_name")
	private String interviewerName;
	
	/** The date of interview. */
	@Column(name="dateOfInterview")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfInterview;
	
	/** The round. */
	@Column(name="round")
	private int round;
	
	/** The job role. */
	@Column(name="job_role")
	private String jobRole;
	
	/** The technical skill. */
	@Column(name="technical_skill")
	private String technicalSkill;
	
	/** The education skill. */
	@Column(name="education_skill")
	private String educationSkill;
	
	/** The organizational skill. */
	@Column(name="organizational_skill")
	private String organizationalSkill;
	
	/** The communication skill. */
	@Column(name="communication_skill")
	private String communicationSkill;
	
	/** The overall rating. */
	@Column(name="overall_rating")
	private String overallRating;
	
	/** The review. */
	@Column(name="review")
	private String review;

	/** The interview result. */
	@Column(name="interview_result")
	private String interviewResult;
	
	/** The next round. */
	@Column(name="next_round")
	private String nextRound;

	/** The hrleader ability. */
	@Column(name="hrleader_ability")
	private String hrleaderAbility;
	
	/** The experience. */
	@Column(name="experience")
	private double experience;
	
	/** The candidate. */
	@OneToOne
	@JoinColumn(name="candidateId")
	private Candidate candidate;
	
	/** The interview. */
	@OneToOne
	@JoinColumn(name="interviewId")
	private Interview interview;
	
	/** The interviewer. */
	@OneToOne
	@JoinColumn(name="userDetailsId")
	private UserDetails userDetails;

	

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
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	public Candidate getCandidate() {
		return candidate;
	}



	/**
	 * Sets the candidate.
	 *
	 * @param candidate the new candidate
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}



	/**
	 * Gets the interview.
	 *
	 * @return the interview
	 */
	public Interview getInterview() {
		return interview;
	}



	/**
	 * Sets the interview.
	 *
	 * @param interview the new interview
	 */
	public void setInterview(Interview interview) {
		this.interview = interview;
	}



	



	public UserDetails getUserDetails() {
		return userDetails;
	}



	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}



	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FeedbackOFCandidate [feedbackId=" + feedbackId + ", candidateName=" + candidateName
				+ ", interviewerName=" + interviewerName + ", dateOfInterview=" + dateOfInterview + ", round=" + round
				+ ", jobRole=" + jobRole + ", technicalSkill=" + technicalSkill + ", educationSkill=" + educationSkill
				+ ", organizationalSkill=" + organizationalSkill + ", communicationSkill=" + communicationSkill
				+ ", overallRating=" + overallRating + ", review=" + review + ", interviewResult=" + interviewResult
				+ ", nextRound=" + nextRound + ", hrleaderAbility=" + hrleaderAbility + ", experience=" + experience
				+ ", candidate=" + candidate + ", interview=" + interview + ", userDetails=" + userDetails + "]";
	} 
	
	
	
	
	
	

}
