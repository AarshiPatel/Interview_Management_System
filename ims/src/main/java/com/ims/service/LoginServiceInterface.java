package com.ims.service;

import com.ims.dto.UserDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;

/**
 * The Interface LoginServiceInterface.
 */
public interface LoginServiceInterface {

	/**
	 * Login.
	 *
	 * @param user the user
	 * @return the user
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 * @throws BadRequestException
	 */
	UserResponseDTO login(UserDTO user) throws UnprocessableEntity, ResourceNotFoundException, BadRequestException;

	/**
	 * Forgot password mail.
	 *
	 * @param userName the user name
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	UserResponseDTO forgotPasswordMail(String userName) throws Exception;

	/**
	 * Auth code verification.
	 *
	 * @param authCode the auth code
	 * @param emailId  the email id
	 * @return the long
	 */
	long authCodeVerification(String authCode, String emailId);

	/**
	 * Rest password.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	UserResponseDTO restPassword(UserDTO user) throws UnprocessableEntity, ResourceNotFoundException;
}
