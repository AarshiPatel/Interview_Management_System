package com.ims.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Class Candidate.
 */
@Entity
@Table(name = "candidate")
public class Candidate {

	/** The candidate id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long candidateId;

	/** The candidate name. */
	@Column(name = "candidateName", nullable = false)
	private String candidateName;

	/** The address. */
	@Column(name = "address")
	private String address;

	/** The contact number. */
	@Column(name = "contactNumber")
	private String contactNumber;

	/** The qualification. */
	@Column(name = "qualification")
	private String qualification;

	/** The date of birth. */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	/** The email id. */
	@Column(name = "emailId", unique = true)
	private String emailId;

	/** The username. */
	@Column(name = "username")
	private String username;

	/** The round. */
	@Column(name = "round")
	private int round;

	/** The experience. */
	@Column(name = "experience")
	private float experience;

	/** The gender. */
	@Column(name = "gender")
	private String gender;

	/** The candidate department. */
	@Column(name = "candidateDepartment")
	private String candidateDepartment;

	/** The candidate designation. */
	@Column(name = "candidateDesignation")
	private String candidateDesignation;

	/** The interview result. */
	@Column(name = "interviewResult")
	private String interviewResult;

	/** The interview. */
	@OneToOne
	@JoinColumn(name = "interviewId")
	private Interview interview;

	/** The human resource. */
	@OneToOne
	@JoinColumn(name = "userDetailsId")
	private UserDetails humanResource;

	@Column(name="candidateGroup")
	private String candidateGroup;
	
	@Column(name="candidateSkill")
	private String candidateSkill;
	
	
	public String getCandidateGroup() {
		return candidateGroup;
	}

	public void setCandidateGroup(String candidateGroup) {
		this.candidateGroup = candidateGroup;
	}

	public String getCandidateSkill() {
		return candidateSkill;
	}

	public void setCandidateSkill(String candidateSkill) {
		this.candidateSkill = candidateSkill;
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

	/**
	 * Gets the interview result.
	 *
	 * @return the interview result
	 */
	public String getInterviewResult() {
		return interviewResult;
	}

	/** The flag. */
	@Column(name = "flag")
	private int flag;

	/**
	 * Gets the flag.
	 *
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag the new flag
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * Sets the interview result.
	 *
	 * @param interviewResult the new interview result
	 */
	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}

	/** The resume. */
	@Column(name = "resume")
	@Lob
	private byte[] resume;

	/** The filepath. */
	private String filepath;

	/**
	 * Gets the filepath.
	 *
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * Sets the filepath.
	 *
	 * @param filepath the new filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * Gets the resume.
	 *
	 * @return the resume
	 */
	public byte[] getResume() {
		return resume;
	}

	/**
	 * Sets the resume.
	 *
	 * @param resume the new resume
	 */
	public void setResume(byte[] resume) {
		this.resume = resume;
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
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
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
	 * Sets the contact number.
	 *
	 * @param contactNumber the new contact number
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * Gets the contact number.
	 *
	 * @return the contact number
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * Gets the qualification.
	 *
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * Sets the qualification.
	 *
	 * @param qualification the new qualification
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the experience.
	 *
	 * @return the experience
	 */
	public float getExperience() {
		return experience;
	}

	/**
	 * Sets the experience.
	 *
	 * @param experience the new experience
	 */
	public void setExperience(float experience) {
		this.experience = experience;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserDetails getHumanResource() {
		return humanResource;
	}

	public void setHumanResource(UserDetails humanResource) {
		this.humanResource = humanResource;
	}

}
