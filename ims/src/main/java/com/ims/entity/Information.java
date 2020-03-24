package com.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "information")
public class Information {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long informationId;
	
	@Column(name="informationName")
	private String informationName;
	
	@Column(name="informationType")
	private String informationType;

	
	@Column(name="groupName")
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
