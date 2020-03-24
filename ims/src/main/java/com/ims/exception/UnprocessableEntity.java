package com.ims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntity extends Exception {

	private static final long serialVersionUID = -470180507998010368L;
	public UnprocessableEntity() {
	
	}
	
	public UnprocessableEntity(String message) {
		super(message);
	}
}
