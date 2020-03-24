package com.ims.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.ims.dto.response.InterviewResponseDTO;
import com.ims.entity.Interview;

/**
 * The Class InterviewMapper.
 */
public class InterviewMapper {
	
	/**
	 * Instantiates a new interview mapper.
	 */
	private InterviewMapper() {

	}

	/**
	 * Map to interview response DTO.
	 *
	 * @param interview the interview
	 * @return the interview response DTO
	 */
	public static InterviewResponseDTO mapToInterviewResponseDTO(Interview interview) {
		InterviewResponseDTO interviewResponseDTO = new InterviewResponseDTO();
		BeanUtils.copyProperties(interview, interviewResponseDTO);
		return interviewResponseDTO;

	}

	/**
	 * Map to user response DTO.
	 *
	 * @param interviewList the interview list
	 * @return the list
	 */
	public static List<InterviewResponseDTO> mapToInterviewResponseDTO(List<Interview> interviewList) {

		return Optional.ofNullable(interviewList).orElse(Collections.emptyList()).stream()
				.map(InterviewMapper::mapToInterviewResponseDTO).collect(Collectors.toList());

	}

}
