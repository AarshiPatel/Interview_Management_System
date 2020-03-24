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

/**
 * The Class Interviewer.
 */
@Entity
@Table(name = "interviewer")
public class Interviewer {

	/** The interviewer id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long interviewerId;

	/** The interviewer name. */
	@Column(name = "interviewerName")
	private String interviewerName;

	/** The interviewer designation. */
	@Column(name = "interviewerDesignation")
	private String interviewerDesignation;

	/** The interviewer department. */
	@Column(name = "interviewerDepartment")
	private String interviewerDepartment;

	/** The interviewer dob. */
	@Column(name = "interviewerDob")
	private LocalDate interviewerDob;

	/** The interviewer experience. */
	@Column(name = "interviewerExperience")
	private Double interviewerExperience;

	/** The interviewer mobilenumber. */
	@Column(name = "interviewerMobilenumber")
	private long interviewerMobilenumber;

	/** The avail date. */
	@Column(name = "availableDate")
	private LocalDate availableDate;

	/** The avail time from. */
	@Column(name = "availableTimeFrom")
	private LocalTime availableTimeFrom;

	/** The avail time to. */
	@Column(name = "availableTimeTo")
	private LocalTime availableTimeTo;

	/** The flag. */
	@Column(name = "flag")
	private int flag;

	/** The user. */
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	/**
	 * Gets the interviewer id.
	 *
	 * @return the interviewer id
	 */
	public long getInterviewerId() {
		return interviewerId;
	}

	/**
	 * Sets the interviewer id.
	 *
	 * @param interviewerId the new interviewer id
	 */
	public void setInterviewerId(long interviewerId) {
		this.interviewerId = interviewerId;
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
	 * Gets the interviewer designation.
	 *
	 * @return the interviewer designation
	 */
	public String getInterviewerDesignation() {
		return interviewerDesignation;
	}

	/**
	 * Sets the interviewer designation.
	 *
	 * @param interviewerDesignation the new interviewer designation
	 */
	public void setInterviewerDesignation(String interviewerDesignation) {
		this.interviewerDesignation = interviewerDesignation;
	}

	/**
	 * Gets the interviewer department.
	 *
	 * @return the interviewer department
	 */
	public String getInterviewerDepartment() {
		return interviewerDepartment;
	}

	/**
	 * Sets the interviewer department.
	 *
	 * @param interviewerDepartment the new interviewer department
	 */
	public void setInterviewerDepartment(String interviewerDepartment) {
		this.interviewerDepartment = interviewerDepartment;
	}

	/**
	 * Gets the interviewer dob.
	 *
	 * @return the interviewer dob
	 */
	public LocalDate getInterviewerDob() {
		return interviewerDob;
	}

	/**
	 * Sets the interviewer dob.
	 *
	 * @param interviewerDob the new interviewer dob
	 */
	public void setInterviewerDob(LocalDate interviewerDob) {
		this.interviewerDob = interviewerDob;
	}

	/**
	 * Gets the interviewer experience.
	 *
	 * @return the interviewer experience
	 */
	public Double getInterviewerExperience() {
		return interviewerExperience;
	}

	/**
	 * Sets the interviewer experience.
	 *
	 * @param interviewerExperience the new interviewer experience
	 */
	public void setInterviewerExperience(Double interviewerExperience) {
		this.interviewerExperience = interviewerExperience;
	}

	/**
	 * Gets the interviewer mobilenumber.
	 *
	 * @return the interviewer mobilenumber
	 */
	public long getInterviewerMobilenumber() {
		return interviewerMobilenumber;
	}

	/**
	 * Sets the interviewer mobilenumber.
	 *
	 * @param interviewerMobilenumber the new interviewer mobilenumber
	 */
	public void setInterviewerMobilenumber(long interviewerMobilenumber) {
		this.interviewerMobilenumber = interviewerMobilenumber;
	}

	/**
	 * Gets the available date.
	 *
	 * @return the available date
	 */
	public LocalDate getAvailableDate() {
		return availableDate;
	}

	/**
	 * Sets the available date.
	 *
	 * @param availableDate the new available date
	 */
	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}

	/**
	 * Gets the available time from.
	 *
	 * @return the available time from
	 */
	public LocalTime getAvailableTimeFrom() {
		return availableTimeFrom;
	}

	/**
	 * Sets the available time from.
	 *
	 * @param availableTimeFrom the new available time from
	 */
	public void setAvailableTimeFrom(LocalTime availableTimeFrom) {
		this.availableTimeFrom = availableTimeFrom;
	}

	/**
	 * Gets the available time to.
	 *
	 * @return the available time to
	 */
	public LocalTime getAvailableTimeTo() {
		return availableTimeTo;
	}

	/**
	 * Sets the available time to.
	 *
	 * @param availableTimeTo the new available time to
	 */
	public void setAvailableTimeTo(LocalTime availableTimeTo) {
		this.availableTimeTo = availableTimeTo;
	}

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
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Interviewer [interviewerId=" + interviewerId + ", interviewerName=" + interviewerName
				+ ", interviewerDesignation=" + interviewerDesignation + ", interviewerDepartment="
				+ interviewerDepartment + ", interviewerDob=" + interviewerDob + ", interviewerExperience="
				+ interviewerExperience + ", interviewerMobilenumber=" + interviewerMobilenumber + ", availDate="
				+ availableDate + ", availTimeFrom=" + availableTimeFrom + ", availTimeTo=" + availableTimeTo
				+ ", flag=" + flag + ", user=" + user + "]";
	}

}
