package com.ims.service;

import java.util.List;

import com.ims.dto.UserTypeDTO;
import com.ims.entity.Admin;
import com.ims.entity.HumanResource;
import com.ims.entity.Interviewer;

/**
 * The Interface AdminServiceInterface.
 */
public interface AdminServiceInterface {

	/**
	 * Method for add user into database.
	 *
	 * @param UserTypeDTO
	 * @return true, if successful
	 * @return false , if username already exists in database or username is not
	 *         validated in isValid() method or userType is invalid .
	 */
	boolean addUser(UserTypeDTO user);

//	/**
//	 * Method for retrieving data of User table from database.
//	 *
//	 * @return all data of User table.
//	 */
//
//	List<UserResponseDTO> viewAllUser();

	/**
	 * Method for update user into database.
	 *
	 * @param userId,UserTypeDTO
	 * @return true, if successful
	 * @return false , if user is not found in database or userType is invalid .
	 */

	boolean updateUser(long userId, UserTypeDTO updateUser);

	/**
	 * Method for delete user into database.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @return false , if user is not found in database .
	 */

	boolean deleteUser(long userId);

	/**
	 * Method for retreiving Admin from userId.
	 *
	 * @param userId
	 * @return instance of Admin , if successful
	 * @return null , if user is not found from database or userType of user is not
	 *         Admin.
	 */

	Admin getAdminFromUserId(long id);

	/**
	 * Method for retreiving Interviewer from userId.
	 *
	 * @param userId
	 * @return instance of Interviewer , if successful
	 * @return null , if user is not found from database or userType of user is not
	 *         Interviewer.
	 */
	Interviewer getInterviewerFromUserId(long id);

	/**
	 * Method for retreiving HumanResource from userId.
	 *
	 * @param userId
	 * @return instance of HumanResource , if successful
	 * @return null , if user is not found from database or userType of user is not
	 *         HumanResource.
	 */
	HumanResource getHumanResourceFromUserId(long id);

	/**
	 * Method for update profile for Admin.
	 *
	 * @param userId ,UserTypeDTO
	 * @return instance of Admin , if successful
	 * @return null , if user is not found from database or userType of user is not
	 *         Admin.
	 */
	Admin updateAdminProfile(long userId, UserTypeDTO userDTO);

	/**
	 * Method for retrieving data of Admin table from database.
	 *
	 * @return all data of Admin table.
	 */
	List<Admin> viewAllAdmin();
	
	/**
	 * Method for retrieving data of HumanResource table from database.
	 *
	 * @return all data of HumanResource table.
	 */
	List<HumanResource> viewAllHumanResource();
	
	/**
	 * Method for retrieving data of Interviewer table from database.
	 *
	 * @return all data of Interviewer table.
	 */
	List<Interviewer> viewAllInterviewer();


}
