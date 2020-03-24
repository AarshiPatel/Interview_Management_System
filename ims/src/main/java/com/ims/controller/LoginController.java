package com.ims.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.dto.Resource;
import com.ims.dto.UserDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.properties.ConstantProperties;
import com.ims.service.LoginServiceInterface;

/**
 * The Class LoginController.
 */

@CrossOrigin(origins = { "${settings.cors_origin}" })
@RestController

public class LoginController {

	/** The login service. */
	@Autowired
	private LoginServiceInterface loginService;

	/**
	 * User login.
	 *
	 * @param user     the user
	 * @param request  the request
	 * @param response the response
	 * @return the response entity
	 * @throws UnprocessableEntity
	 * @throws BadRequestException
	 */
	@PostMapping(value = "/login")
	public @ResponseBody ResponseEntity<Resource<UserResponseDTO>> userLogin(@RequestBody UserDTO user)
			throws ResourceNotFoundException, UnprocessableEntity, BadRequestException {

		Resource<UserResponseDTO> resource = new Resource<>(loginService.login(user), ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Forgot password.
	 *
	 * @param userName the user name
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@GetMapping(value = "/forgotpassword/{userName}")
	public @ResponseBody ResponseEntity<Resource<UserResponseDTO>> forgotPassword(@PathVariable String userName)
			throws Exception {
		Resource<UserResponseDTO> resource = new Resource<>(loginService.forgotPasswordMail(userName),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Check auth code.
	 *
	 * @param authCode the auth code
	 * @param emailId  the email id
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GetMapping(value = "/user/authcode")
	public void checkAuthCode(@RequestParam String authCode, @RequestParam String emailId, HttpServletResponse response)
			throws IOException {
		long result = loginService.authCodeVerification(authCode, emailId);
		if (result != 0) {
			response.sendRedirect("http://localhost:3000/restpassword/" + result + "");
		} else {
			response.sendRedirect("http://localhost:3000/InValidURL");
		}
	}

	/**
	 * Change password.
	 *
	 * @param user the user
	 * @return the response entity
	 * @throws UnprocessableEntity
	 */
	@PostMapping(value = "/changepassword")
	public @ResponseBody ResponseEntity<Resource<UserResponseDTO>> changePassword(@RequestBody UserDTO user)
			throws ResourceNotFoundException, UnprocessableEntity {
		Resource<UserResponseDTO> resource = new Resource<>(loginService.restPassword(user),
				ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

}
