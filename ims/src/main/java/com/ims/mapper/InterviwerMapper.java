package com.ims.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.ims.dto.ShowInterviwerDTO;
import com.ims.entity.UserDetails;

public class InterviwerMapper {
	private InterviwerMapper() {
		
	}
	public static ShowInterviwerDTO mapToInterviewerResponseDTO(UserDetails user) {
		ShowInterviwerDTO interviewerResponseDTO = new ShowInterviwerDTO();
		BeanUtils.copyProperties(user, interviewerResponseDTO);
		return interviewerResponseDTO;

	}

	public static List<ShowInterviwerDTO> mapToInterviewerResponseDTO(List<UserDetails> users) {

		return Optional.ofNullable(users).orElse(Collections.emptyList()).stream()
				.map(InterviwerMapper::mapToInterviewerResponseDTO).collect(Collectors.toList());

	}
}
