package com.ims.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ims.dto.response.UserDetailsResponseDTO;
import com.ims.entity.UserDetails;

/**
 * The Class UserDetailsMapper.
 */
public class UserDetailsMapper {

	/**
	 * Instantiates a new user details mapper.
	 */
	private UserDetailsMapper() {

	}

	/**
	 * Map to user response DTO.
	 *
	 * @param userDetails the user details
	 * @return the user details response DTO
	 */
	public static UserDetailsResponseDTO mapToUserResponseDTO(UserDetails userDetails) {

		UserDetailsResponseDTO userDetailsResponseDTO = new UserDetailsResponseDTO();
		userDetailsResponseDTO.setName(userDetails.getName());
		userDetailsResponseDTO.setUser_type(userDetails.getUser_type());
		userDetailsResponseDTO.setDepartment(userDetails.getDepartment());
		userDetailsResponseDTO.setDesignation(userDetails.getDesignation());
		userDetailsResponseDTO.setDateOfBirth(userDetails.getDateOfBirth());
		userDetailsResponseDTO.setExperience(userDetails.getExperience());
		userDetailsResponseDTO.setContactNumber(userDetails.getContactNumber());
		userDetailsResponseDTO.setAvailableDate(userDetails.getAvailableDate());
		userDetailsResponseDTO.setAvailableTimeFrom(userDetails.getAvailableTimeFrom());
		userDetailsResponseDTO.setAvailableTimeTo(userDetails.getAvailableTimeTo());
		userDetailsResponseDTO.setUser(userDetails.getUser());
		userDetailsResponseDTO.setUserGroup(userDetails.getUserGroup());

		return userDetailsResponseDTO;

	}

	/**
	 * Map to user details response DTO.
	 *
	 * @param userDetails the user details
	 * @return the list
	 */
	public static List<UserDetailsResponseDTO> mapToUserDetailsResponseDTO(List<UserDetails> userDetails) {

		return Optional.ofNullable(userDetails).orElse(Collections.emptyList()).stream()
				.map(UserDetailsMapper::mapToUserResponseDTO).collect(Collectors.toList());

	}

}
