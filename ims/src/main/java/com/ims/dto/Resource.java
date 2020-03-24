package com.ims.dto;

import java.util.List;

public class Resource<T> {
	
	ErrorDTO errorDTO;

	private List<T> dataList;
	private T data;
	private boolean success = false;

	public Resource() {

	}

	public ErrorDTO getErrorDTO() {
		return errorDTO;
	}

	public void setErrorDTO(ErrorDTO errorDTO) {
		this.errorDTO = errorDTO;
	}

	public Resource(List<T> data, boolean success) {
		this.dataList = data;
		this.success = success;
	}
	
	public Resource(T data, boolean success) {
		this.data = data;
		this.success = success;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void setError() {
		errorDTO.getMessage();
	}

}
