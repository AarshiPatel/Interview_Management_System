package com.ims.dto.response;

import java.time.LocalDate;

public class JobRequirmentResponseDTO {
	private String requirementGroup;
	private String requirementDepartment;
	private String requirementDesignation;
	private String requirmentName;
	private String managerName;
	private LocalDate jobPostedDate;
	private int jobOpening;
	private String exprianceRange;
	private int totalNoOfOpeing;
	private long managerId;
	private long requirementId;
	
	public long getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(long requirementId) {
		this.requirementId = requirementId;
	}

	public int getTotalNoOfOpeing() {
		return totalNoOfOpeing;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public void setTotalNoOfOpeing(int totalNoOfOpeing) {
		this.totalNoOfOpeing = totalNoOfOpeing;
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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public LocalDate getJobPostedDate() {
		return jobPostedDate;
	}

	public void setJobPostedDate(LocalDate jobPostedDate) {
		this.jobPostedDate = jobPostedDate;
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
}
