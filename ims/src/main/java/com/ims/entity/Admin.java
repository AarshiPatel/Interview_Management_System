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

/**
 * The Class Admin.
 */
@Entity
@Table(name = "admin")
public class Admin {

	/** The admin id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;

	/** The admin name. */
	@Column(name = "adminName")
	private String adminName;

	/** The admin designation. */
	@Column(name = "adminDesignation")
	private String adminDesignation;

	/** The admin department. */
	@Column(name = "adminDepartment")
	private String adminDepartment;

	/** The admin dob. */
	@Column(name = "adminDob")
	private LocalDate adminDob;

	/** The admin experience. */
	@Column(name = "admin_experience")
	private Double adminExperience;

	/** The admin mobilenumber. */
	@Column(name = "admin_mobilenumber")
	private long adminMobilenumber;

	/** The user. */
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	/**
	 * Gets the admin id.
	 *
	 * @return the admin id
	 */
	public long getAdminId() {
		return adminId;
	}

	/**
	 * Sets the admin id.
	 *
	 * @param adminId the new admin id
	 */
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	/**
	 * Gets the admin name.
	 *
	 * @return the admin name
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * Sets the admin name.
	 *
	 * @param adminName the new admin name
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/**
	 * Gets the admin designation.
	 *
	 * @return the admin designation
	 */
	public String getAdminDesignation() {
		return adminDesignation;
	}

	/**
	 * Sets the admin designation.
	 *
	 * @param adminDesignation the new admin designation
	 */
	public void setAdminDesignation(String adminDesignation) {
		this.adminDesignation = adminDesignation;
	}

	/**
	 * Gets the admin department.
	 *
	 * @return the admin department
	 */
	public String getAdminDepartment() {
		return adminDepartment;
	}

	/**
	 * Sets the admin department.
	 *
	 * @param adminDepartment the new admin department
	 */
	public void setAdminDepartment(String adminDepartment) {
		this.adminDepartment = adminDepartment;
	}

	/**
	 * Gets the admin dob.
	 *
	 * @return the admin dob
	 */
	public LocalDate getAdminDob() {
		return adminDob;
	}

	/**
	 * Sets the admin dob.
	 *
	 * @param adminDob the new admin dob
	 */
	public void setAdminDob(LocalDate adminDob) {
		this.adminDob = adminDob;
	}

	/**
	 * Gets the admin experience.
	 *
	 * @return the admin experience
	 */
	public Double getAdminExperience() {
		return adminExperience;
	}

	/**
	 * Sets the admin experience.
	 *
	 * @param adminExperience the new admin experience
	 */
	public void setAdminExperience(Double adminExperience) {
		this.adminExperience = adminExperience;
	}

	/**
	 * Gets the admin mobilenumber.
	 *
	 * @return the admin mobilenumber
	 */
	public long getAdminMobilenumber() {
		return adminMobilenumber;
	}

	/**
	 * Sets the admin mobilenumber.
	 *
	 * @param adminMobilenumber the new admin mobilenumber
	 */
	public void setAdminMobilenumber(long adminMobilenumber) {
		this.adminMobilenumber = adminMobilenumber;
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
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminDesignation=" + adminDesignation
				+ ", adminDepartment=" + adminDepartment + ", adminDob=" + adminDob + ", adminExperience="
				+ adminExperience + ", adminMobilenumber=" + adminMobilenumber + ", user=" + user + "]";
	}

}
