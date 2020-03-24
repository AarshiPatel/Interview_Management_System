package com.ims.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.ims.dto.response.UserResponseDTO;
import com.ims.entity.User;

public class UserMapper {
	private UserMapper() {

	}

	public static UserResponseDTO mapToUserResponseDTO(User user) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		BeanUtils.copyProperties(user, userResponseDTO);
		return userResponseDTO;

	}
	
	/**
	 * Map to user response DTO.
	 *
	 * @param users the users
	 * @return the list
	 */
	public static List<UserResponseDTO> mapToUserResponseDTO(List<User> users) {
		
		return Optional.ofNullable(users)
					   .orElse(Collections.emptyList())
					   .stream()
					   .map(UserMapper :: mapToUserResponseDTO)
					   .collect(Collectors.toList());
		
	}
	

}
