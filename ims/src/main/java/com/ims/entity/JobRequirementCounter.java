package com.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobrequirementcounter")
public class JobRequirementCounter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobRequirementCounterId;

	@Column(name = "requirementGroup")
	private String requirementGroup;

	@Column(name = "requirementDepartment")
	private String requirementDepartment;

	@Column(name = "requirementDesignation")
	private String requirementDesignation;

	@Column(name = "requirmentName")
	private String requirmentName;

	@Column(name = "requirementCounter")
	private int requirementCounter;

	@Column(name = "activeRequirement")
	private int activeRequirement;

	public int getActiveRequirement() {
		return activeRequirement;
	}

	@Column(name = "exprianceRange")
	private String exprianceRange;

	public String getExprianceRange() {
		return exprianceRange;
	}

	public void setExprianceRange(String exprianceRange) {
		this.exprianceRange = exprianceRange;
	}

	public void setActiveRequirement(int activeRequirement) {
		this.activeRequirement = activeRequirement;
	}

	public long getJobRequirementCounterId() {
		return jobRequirementCounterId;
	}

	public void setJobRequirementCounterId(long jobRequirementCounterId) {
		this.jobRequirementCounterId = jobRequirementCounterId;
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

	public int getRequirementCounter() {
		return requirementCounter;
	}

	public void setRequirementCounter(int requirementCounter) {
		this.requirementCounter = requirementCounter;
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
