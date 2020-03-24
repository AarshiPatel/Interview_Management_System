package com.ims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class BadRequestException.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2399511608284611293L;

	/**
	 * Instantiates a new bad request exception.
	 */
	public BadRequestException() {
		super();
	}

	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param message the message
	 */
	public BadRequestException(final String message) {
		super(message);

	}

}
