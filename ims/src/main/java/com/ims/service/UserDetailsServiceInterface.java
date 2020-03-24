package com.ims.service;

import java.util.List;

import com.ims.dto.UserDetailsDTO;
import com.ims.dto.response.UserDetailsResponseDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.entity.UserDetails;
import com.ims.exception.ConflictException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;


/**
 * The Interface UserDetailsServiceInterface.
 */
public interface UserDetailsServiceInterface {

	/**
	 * Adds the user.
	 *
	 * @param userDetailsDTO the user details DTO
	 * @return true, if successful
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	UserDetails addUser(UserDetailsDTO userDetailsDTO) throws ConflictException, UnprocessableEntity;

	/**
	 * Update user.
	 *
	 * @param userId         the user id
	 * @param userDetailsDTO the user details DTO
	 * @return true, if successful
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	UserDetails updateUser(long userId, UserDetailsDTO userDetailsDTO) throws ResourceNotFoundException;

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	UserDetails deleteUser(long userId) throws ResourceNotFoundException;

	/**
	 * Method for retrieving data of User table from database.
	 *
	 * @return all data of User table.
	 */

	List<UserResponseDTO> viewAllUser();

	/**
	 * View all admin.
	 *
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<UserDetailsResponseDTO> viewAllAdmin() throws ResourceNotFoundException;

	/**
	 * View all interviewer.
	 *
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<UserDetailsResponseDTO> viewAllInterviewer() throws ResourceNotFoundException;

	/**
	 * View all human resource.
	 *
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<UserDetailsResponseDTO> viewAllHumanResource() throws ResourceNotFoundException;

	/**
	 * View all manager.
	 *
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<UserDetailsResponseDTO> viewAllManager() throws ResourceNotFoundException;

	/**
	 * Gets the user details from user id.
	 *
	 * @param userId the user id
	 * @return the user details from user id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	UserDetailsResponseDTO getUserDetailsFromUserId(long userId) throws ResourceNotFoundException;

	/**
	 * Update admin profile.
	 *
	 * @param userId     the user id
	 * @param updateUser the update user
	 * @return the user details response DTO
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	UserDetailsResponseDTO updateAdminProfile(long userId, UserDetailsDTO updateUser) throws ResourceNotFoundException;

}
