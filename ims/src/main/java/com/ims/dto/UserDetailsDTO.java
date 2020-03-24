package com.ims.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Class UserDetailsDTO.
 */
public class UserDetailsDTO {

	/** The username. */
	private String username;

	/** The name. */
	private String name;

	/** The password. */
	private String password;

	/** The user type. */
	private String user_type;

	/** The dob. */
	private LocalDate dateOfBirth;

	/** The experience. */
	private Double experience;

	/** The mobilenumber. */
	private String contactNumber;

	/** The designation. */
	private String designation;

	/** The department. */
	private String department;

	/** The user id. */
	private long userId;

	/** The new password. */
	private String newPassword;

	/** The available date. */
	private LocalDate availableDate;

	/** The available time from. */
	private LocalTime availableTimeFrom;

	/** The available time to. */
	private LocalTime availableTimeTo;

	/** The user group. */
	private String userGroup;

	/** The active flag. */
	private int activeFlag;

	/**
	 * Gets the user group.
	 *
	 * @return the user group
	 */
	public String getUserGroup() {
		return userGroup;
	}

	/**
	 * Sets the user group.
	 *
	 * @param userGroup the new user group
	 */
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public String getUser_type() {
		return user_type;
	}

	/**
	 * Sets the user type.
	 *
	 * @param user_type the new user type
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
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
	 * Gets the experience.
	 *
	 * @return the experience
	 */
	public Double getExperience() {
		return experience;
	}

	/**
	 * Sets the experience.
	 *
	 * @param experience the new experience
	 */
	public void setExperience(Double experience) {
		this.experience = experience;
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
	 * Sets the contact number.
	 *
	 * @param contactNumber the new contact number
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Sets the department.
	 *
	 * @param department the new department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

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

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param newPassword the new new password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
	 * Gets the active flag.
	 *
	 * @return the active flag
	 */
	public int getActiveFlag() {
		return activeFlag;
	}

	/**
	 * Sets the active flag.
	 *
	 * @param activeFlag the new active flag
	 */
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

}
