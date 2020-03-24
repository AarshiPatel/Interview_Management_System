package com.ims.controller;

import java.util.List;
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

import com.ims.dto.UserTypeDTO;
import com.ims.entity.Admin;
import com.ims.entity.HumanResource;
import com.ims.entity.Interviewer;
import com.ims.exception.ResourceNotFoundException;
import com.ims.service.AdminServiceInterface;

/**
 * The Class AdminController.
 */


@CrossOrigin(origins = {"${settings.cors_origin}"})

@RestController
public class AdminController {

	/** The Constant logger. */

	/** The adminserviceinterface. */
	@Autowired
	private AdminServiceInterface adminServiceInterface;

	/**
	 * Api for add user into database.
	 *
	 * @param UserTypeDTO the  user
	 * @return true, if successful. 
	 * @return false , if username already exists in database or
	 *         username is not validated in isValid() method or userType is invalid
	 */
	@PostMapping(value = "/adduser")
	public @ResponseBody ResponseEntity<Boolean> addUser(@RequestBody UserTypeDTO user)
			throws ResourceNotFoundException {

		boolean newUser = adminServiceInterface.addUser(user);

		if (newUser) {

			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		}
		throw new ResourceNotFoundException("user not found");

	}

	/**
	 * Api for update user into database.
	 *
	 * @param userId     the user id
	 * @param UserTypeDTO the update user
	 * @return true, if successful.
	 * @return false , if user is not found in database or
	 *         userType is invalid .
	 */
	@PutMapping(value = "/updateuser/{userId}")
	public @ResponseBody ResponseEntity<Boolean> updateUser(@PathVariable long userId,
			@RequestBody UserTypeDTO updateUser) throws ResourceNotFoundException {

		boolean updatedUser = adminServiceInterface.updateUser(userId, updateUser);

		if (updatedUser) {

			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Api for delete user from database.
	 *
	 * @param userId the user id
	 * @return true, if successful 
	 * @return false , if user is not found in database .
	 */
	@DeleteMapping(value = "/deleteuser/{userId}")
	public @ResponseBody ResponseEntity<Boolean> deleteUser(@PathVariable long userId)
			throws ResourceNotFoundException {
		boolean deletedUser = adminServiceInterface.deleteUser(userId);

		if (deletedUser) {

			return new ResponseEntity<>(deletedUser, HttpStatus.OK);
		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Api for retreiving Admin from userId.
	 *
	 * @param userId the user id
	 * @return instance of Admin , if successful 
	 * @return null , if user is not found from
	 *         database or userType of user is not Admin.
	 */
	@GetMapping(value = "/viewadmin/{userId}")
	public ResponseEntity<Admin> getAdminFromUserId(@PathVariable long userId) throws ResourceNotFoundException {

		Admin getAdmin = adminServiceInterface.getAdminFromUserId(userId);

		if (!Objects.isNull(getAdmin)) {
			return new ResponseEntity<>(getAdmin, HttpStatus.OK);

		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Api for retreiving HumanResource from userId.
	 *
	 * @param userId the user id
	 * @return instance of HumanResource , if successful 
	 * @return null , if user is not found
	 *         from database or userType of user is not HumanResource.
	 */
	@GetMapping(value = "/viewhumanresource/{userId}")
	public ResponseEntity<HumanResource> getHumanResourceFromUserId(@PathVariable long userId)
			throws ResourceNotFoundException {

		HumanResource getHr = adminServiceInterface.getHumanResourceFromUserId(userId);

		if (!Objects.isNull(getHr)) {
			return new ResponseEntity<>(getHr, HttpStatus.OK);

		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Api for retreiving Interviewer from userId.
	 *
	 * @param userId the user id
	 * @return instance of Interviewer , if successful
	 * @return null , if user is not found
	 *         from database or userType of user is not Interviewer.
	 */
	@GetMapping(value = "/viewinterviewer/{userId}")
	public ResponseEntity<Interviewer> getInterviewerFromUserId(@PathVariable long userId)
			throws ResourceNotFoundException {

		Interviewer getInterviewer = adminServiceInterface.getInterviewerFromUserId(userId);

		if (!Objects.isNull(getInterviewer)) {
			return new ResponseEntity<>(getInterviewer, HttpStatus.OK);

		}
		throw new ResourceNotFoundException();
	}

	/**
	 * Api for update profile for Admin.
	 *
	 * @param userId  
	 * @param userDTO the user DTO
	 * @return instance of Admin , if successful
	 * @return null , if user is not found from
	 *         database or userType of user is not Admin.
	 */

	/**
	 * Api for retrieving data of Admin table from database.
	 *
	 * @return all data of Admin table.
	 * @throws ResourceNotFoundException if data is not found.
	 */
	@GetMapping(value = "/viewalladmin")
	public ResponseEntity<List<Admin>> viewAllAdmin() throws ResourceNotFoundException {

		List<Admin> listOfAdmin = adminServiceInterface.viewAllAdmin();
		if (!listOfAdmin.isEmpty()) {
			return new ResponseEntity<>(listOfAdmin, HttpStatus.OK);
		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Api for retrieving data of HumanResource table from database.
	 *
	 * @return all data of HumanResource table.
	 * @throws ResourceNotFoundException if data is not found.

	 */
	@GetMapping(value = "/viewallhumanresource")
	public ResponseEntity<List<HumanResource>> viewAllHumanResource() throws ResourceNotFoundException {

		List<HumanResource> listOfHumanResource = adminServiceInterface.viewAllHumanResource();
		if (!listOfHumanResource.isEmpty()) {
			return new ResponseEntity<>(listOfHumanResource, HttpStatus.OK);
		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Api for retrieving data of Interviewer table from database.
	 *
	 * @return all data of Interviewer table.
	 * @throws ResourceNotFoundException if data is not found.
	 */
	@GetMapping(value = "/viewallinterviewer")
	public ResponseEntity<List<Interviewer>> viewAllInterviewer() throws ResourceNotFoundException {

		List<Interviewer> listOfInterviewer = adminServiceInterface.viewAllInterviewer();
		if (!listOfInterviewer.isEmpty()) {
			return new ResponseEntity<>(listOfInterviewer, HttpStatus.OK);
		}
		throw new ResourceNotFoundException();

	}
}
