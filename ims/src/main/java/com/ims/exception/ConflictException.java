package com.ims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends Exception{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2399511608284611293L;

	/**
	 * Instantiates a new bad request exception.
	 */
	public  ConflictException() {
		super();
	}

	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param message the message
	 */
	public ConflictException(final String message) {
		super(message);

	}

}
