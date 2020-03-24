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

@Entity
@Table(name = "jobrequirement")
public class JobRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long requirementId;

	@Column(name = "jobOpening", nullable = false)
	private int jobOpening;

	@Column(name = "exprianceRange")
	private String exprianceRange;

	@Column(name = "requirementGroup")
	private String requirementGroup;

	@Column(name = "requirementDepartment")
	private String requirementDepartment;

	@Column(name = "requirementDesignation")
	private String requirementDesignation;

	@Column(name = "requirmentName")
	private String requirmentName;
	
	@OneToOne
	@JoinColumn(name="userDetailsId")
	private UserDetails manager;
	
	@Column(name="jobPostedDate")
	private LocalDate jobPostedDate;

	
	@Column(name="jobOpeningIsApprove")
	private int jobOpeningIsApprove;
	
	public int getJobOpeningIsApprove() {
		return jobOpeningIsApprove;
	}

	public void setJobOpeningIsApprove(int jobOpeningIsApprove) {
		this.jobOpeningIsApprove = jobOpeningIsApprove;
	}

	public LocalDate getJobPostedDate() {
		return jobPostedDate;
	}

	public void setJobPostedDate(LocalDate jobPostedDate) {
		this.jobPostedDate = jobPostedDate;
	}

	public UserDetails getManager() {
		return manager;
	}

	public void setManager(UserDetails manager) {
		this.manager = manager;
	}

	public long getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(long requirementId) {
		this.requirementId = requirementId;
	}

	public int getJobOpening() {
		return jobOpening;
	}

	public void setJobOpening(int jobOpening) {
		this.jobOpening = jobOpening;
	}

	public String getExprianceRange() {
		return exprianceRange;
	}

	public void setExprianceRange(String exprianceRange) {
		this.exprianceRange = exprianceRange;
	}

	public String getRequirementGroup() {
		return requirementGroup;
	}

	public void setRequirementGroup(String requirementGroup) {
		this.requirementGroup = requirementGroup;
	}

	public String getRequirementDepartment() {
		return requirementDepartment;
	}

	public void setRequirementDepartment(String requirementDepartment) {
		this.requirementDepartment = requirementDepartment;
	}

	public String getRequirementDesignation() {
		return requirementDesignation;
	}

	public void setRequirementDesignation(String requirementDesignation) {
		this.requirementDesignation = requirementDesignation;
	}

	public String getRequirmentName() {
		return requirmentName;
	}

	public void setRequirmentName(String requirmentName) {
		this.requirmentName = requirmentName;
	}

}
