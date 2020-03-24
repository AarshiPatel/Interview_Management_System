package com.ims.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ims.dto.Resource;
import com.ims.dto.UserDetailsDTO;
import com.ims.dto.response.UserDetailsResponseDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.entity.UserDetails;
import com.ims.exception.ConflictException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.properties.ConstantProperties;
import com.ims.service.UserDetailsServiceInterface;


/**
 * The Class UserDetailsController.
 */

@CrossOrigin(origins = { "${settings.cors_origin}" })

@RestController
public class UserDetailsController {

	/** The Constant logger. */

	/** The adminserviceinterface. */
	@Autowired
	private UserDetailsServiceInterface userDetailsServiceInterface;

	/**
	 * Adds the user.
	 *
	 * @param userDetailsDTO the user details DTO
	 * @return the response entity
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/adduserdetails")
	public @ResponseBody ResponseEntity<Resource> addUser(@RequestBody UserDetailsDTO userDetailsDTO)
			throws ConflictException, UnprocessableEntity {

		Resource<UserDetails> resource = new Resource<>(userDetailsServiceInterface.addUser(userDetailsDTO), true);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);

	}

	/**
	 * Update user.
	 *
	 * @param userId         the user id
	 * @param userDetailsDTO the user details DTO
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@SuppressWarnings("rawtypes")
	@PutMapping(value = "/updateuserdetails/{userId}")
	public @ResponseBody ResponseEntity<Resource> updateUser(@PathVariable long userId,
			@RequestBody UserDetailsDTO userDetailsDTO) throws ResourceNotFoundException {

		Resource<UserDetails> resource = new Resource<>(userDetailsServiceInterface.updateUser(userId, userDetailsDTO),
				true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws NullPointerException      the null pointer exception
	 */
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/deleteuserdetails/{userId}")
	public @ResponseBody ResponseEntity<Resource> deleteUser(@PathVariable long userId)
			throws ResourceNotFoundException {

		Resource<UserDetails> resource = new Resource<>(userDetailsServiceInterface.deleteUser(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Update admin profile.
	 *
	 * @param userId     the user id
	 * @param updateUser the update user
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping(value = "/updateadminprofile/{userId}")
	public @ResponseBody ResponseEntity<Resource<UserDetailsResponseDTO>> updateAdminProfile(@PathVariable long userId,
			@RequestBody UserDetailsDTO updateUser) throws ResourceNotFoundException {

		Resource<UserDetailsResponseDTO> resource = new Resource<>(
				userDetailsServiceInterface.updateAdminProfile(userId, updateUser), ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Api for retrieving data of User table from database.
	 *
	 * @return all data of User table.
	 * @throws ResourceNotFoundException if data is not found.
	 */
	@GetMapping(value = "/viewalluser")
	public ResponseEntity<Resource<UserResponseDTO>> viewAllUser() throws ResourceNotFoundException {

		Resource<UserResponseDTO> resource = new Resource<>(userDetailsServiceInterface.viewAllUser(),
				ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Api for retrieving data of Admin from database.
	 *
	 * @return all data of Admin table.
	 * @throws ResourceNotFoundException if data is not found.
	 */
	@GetMapping(value = "/viewAllAdmin")
	public ResponseEntity<Resource<UserDetailsResponseDTO>> viewAllAdmin() throws ResourceNotFoundException {

		Resource<UserDetailsResponseDTO> resource = new Resource<>(userDetailsServiceInterface.viewAllAdmin(),
				ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Api for retrieving data of Interviewer from database.
	 *
	 * @return all data of Interviewer table.
	 * @throws ResourceNotFoundException if data is not found.
	 */
	@GetMapping(value = "/viewAllInterviewer")
	public ResponseEntity<Resource<UserDetailsResponseDTO>> viewAllInterviewer() throws ResourceNotFoundException {

		Resource<UserDetailsResponseDTO> resource = new Resource<>(userDetailsServiceInterface.viewAllInterviewer(),
				ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Api for retrieving data of HumanResource from database.
	 *
	 * @return all data of HumanResource table.
	 * @throws ResourceNotFoundException if data is not found.
	 */
	@GetMapping(value = "/viewAllHumanResource")
	public ResponseEntity<Resource<UserDetailsResponseDTO>> viewAllHumanResource() throws ResourceNotFoundException {

		Resource<UserDetailsResponseDTO> resource = new Resource<>(userDetailsServiceInterface.viewAllHumanResource(),
				ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Api for retrieving data of manager from database.
	 *
	 * @return all data of manager table.
	 * @throws ResourceNotFoundException if data is not found.
	 */
	@GetMapping(value = "/viewAllManager")
	public ResponseEntity<Resource<UserDetailsResponseDTO>> viewAllManager() throws ResourceNotFoundException {

		Resource<UserDetailsResponseDTO> resource = new Resource<>(userDetailsServiceInterface.viewAllManager(),
				ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Api for retrieving UserDetails from userId.
	 *
	 * @param userId the user id
	 * @return instance of UserDetails , if successful null , if user is not found
	 *         from database.
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/viewuserdetails/{userId}")
	public ResponseEntity<UserDetailsResponseDTO> getUserDetailsFromUserId(@PathVariable long userId)
			throws ResourceNotFoundException {

		UserDetailsResponseDTO getUserDetails = userDetailsServiceInterface.getUserDetailsFromUserId(userId);

		if (!Objects.isNull(getUserDetails)) {
			return new ResponseEntity<>(getUserDetails, HttpStatus.OK);

		}
		throw new ResourceNotFoundException();

	}

}
