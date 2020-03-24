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
 * The Class HumanResource.
 */
@Entity
@Table(name = "humanresource")
public class HumanResource {
	
	/** The hr id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long hrId;
	
	/** The hr name. */
	@Column(name="hrName")
	private String hrName;
	
	/** The hr designation. */
	@Column(name="hrDesignation")
	private String hrDesignation;
	
	/** The hr department. */
	@Column(name="hrDepartment")
	private String hrDepartment;
	
	/** The hr dob. */
	@Column(name="hrDob")
	private LocalDate hrDob;
	
	/** The hr experience. */
	@Column(name="hrExperience")
	private Double hrExperience;
	
	/** The hr mobilenumber. */
	@Column(name="hrMobilenumber")
	private long hrMobilenumber;
	
	/** The user. */
	@OneToOne
	@JoinColumn(name="userId")
    private User user;
	

	/**
	 * Gets the hr id.
	 *
	 * @return the hr id
	 */
	public long getHrId() {
		return hrId;
	}


	/**
	 * Sets the hr id.
	 *
	 * @param hrId the new hr id
	 */
	public void setHrId(long hrId) {
		this.hrId = hrId;
	}


	/**
	 * Gets the hr name.
	 *
	 * @return the hr name
	 */
	public String getHrName() {
		return hrName;
	}


	/**
	 * Sets the hr name.
	 *
	 * @param hrName the new hr name
	 */
	public void setHrName(String hrName) {
		this.hrName = hrName;
	}


	/**
	 * Gets the hr designation.
	 *
	 * @return the hr designation
	 */
	public String getHrDesignation() {
		return hrDesignation;
	}


	/**
	 * Sets the hr designation.
	 *
	 * @param hrDesignation the new hr designation
	 */
	public void setHrDesignation(String hrDesignation) {
		this.hrDesignation = hrDesignation;
	}


	/**
	 * Gets the hr department.
	 *
	 * @return the hr department
	 */
	public String getHrDepartment() {
		return hrDepartment;
	}


	/**
	 * Sets the hr department.
	 *
	 * @param hrDepartment the new hr department
	 */
	public void setHrDepartment(String hrDepartment) {
		this.hrDepartment = hrDepartment;
	}


	/**
	 * Gets the hr dob.
	 *
	 * @return the hr dob
	 */
	public LocalDate getHrDob() {
		return hrDob;
	}


	/**
	 * Sets the hr dob.
	 *
	 * @param hrDob the new hr dob
	 */
	public void setHrDob(LocalDate hrDob) {
		this.hrDob = hrDob;
	}


	/**
	 * Gets the hr experience.
	 *
	 * @return the hr experience
	 */
	public Double getHrExperience() {
		return hrExperience;
	}


	/**
	 * Sets the hr experience.
	 *
	 * @param hrExperience the new hr experience
	 */
	public void setHrExperience(Double hrExperience) {
		this.hrExperience = hrExperience;
	}


	/**
	 * Gets the hr mobilenumber.
	 *
	 * @return the hr mobilenumber
	 */
	public long getHrMobilenumber() {
		return hrMobilenumber;
	}


	/**
	 * Sets the hr mobilenumber.
	 *
	 * @param hrMobilenumber the new hr mobilenumber
	 */
	public void setHrMobilenumber(long hrMobilenumber) {
		this.hrMobilenumber = hrMobilenumber;
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
		return "HumanResource [hrId=" + hrId + ", hrName=" + hrName + ", hrDesignation=" + hrDesignation
				+ ", hrDepartment=" + hrDepartment + ", hrDob=" + hrDob + ", hrExperience=" + hrExperience
				+ ", hrMobilenumber=" + hrMobilenumber + ", user=" + user + "]";
	}

	
}
