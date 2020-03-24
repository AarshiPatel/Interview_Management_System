package com.ims.dto.request;

public class InformationRequestDTO {
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	private String informationName;
	private String informationType;
	private String groupName;
	public String getInformationName() {
		return informationName;
	}
	public void setInformationName(String informationName) {
		this.informationName = informationName;
	}
	public String getInformationType() {
		return informationType;
	}
	public void setInformationType(String informationType) {
		this.informationType = informationType;
	}
	
}
