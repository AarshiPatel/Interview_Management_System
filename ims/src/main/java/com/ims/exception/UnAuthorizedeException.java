package com.ims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class UnAuthorizedeException.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedeException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2399511608284611293L;

	/**
	 * Instantiates a new un authorizede exception.
	 */
	public UnAuthorizedeException() {
		super();
	}

	/**
	 * Instantiates a new un authorizede exception.
	 *
	 * @param message the message
	 */
	public UnAuthorizedeException(final String message) {
		super(message);

	}
}
