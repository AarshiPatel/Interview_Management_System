package com.ims.dto.request;

import java.time.LocalDate;

public class JobRequirmentRequestDTO {
	private int jobOpening;
	private String exprianceRange;
	private String requirementGroup;
	private String requirementDepartment;
	private String requirementDesignation;
	private String requirmentName;
	private LocalDate requirementDate;
	private long userId;
	private long jobPostId;
	public long getJobPostId() {
		return jobPostId;
	}
	public void setJobPostId(long jobPostId) {
		this.jobPostId = jobPostId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public LocalDate getRequirementDate() {
		return requirementDate;
	}
	public void setRequirementDate(LocalDate requirementDate) {
		this.requirementDate = requirementDate;
	}
}
