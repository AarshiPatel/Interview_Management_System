package com.ims.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowInterviwerDTO {
	private long userDetailsId;
	private String designation;
	private String department;
	private LocalDate availableDate;
	private LocalTime availableTimeFrom;
	private LocalTime availableTimeTo;
	private String name;
	public long getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public LocalDate getAvailableDate() {
		return availableDate;
	}
	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}
	public LocalTime getAvailableTimeFrom() {
		return availableTimeFrom;
	}
	public void setAvailableTimeFrom(LocalTime availableTimeFrom) {
		this.availableTimeFrom = availableTimeFrom;
	}
	public LocalTime getAvailableTimeTo() {
		return availableTimeTo;
	}
	public void setAvailableTimeTo(LocalTime availableTimeTo) {
		this.availableTimeTo = availableTimeTo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
