package com.ims.dto.response;

public class InformationResponseDTO {
	private String informationName;
	private String informationType;
	private long informationId;
	private String groupName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public long getInformationId() {
		return informationId;
	}
	public void setInformationId(long informationId) {
		this.informationId = informationId;
	}
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
