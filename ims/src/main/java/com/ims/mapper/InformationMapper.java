package com.ims.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.ims.dto.response.InformationResponseDTO;
import com.ims.entity.Information;

public class InformationMapper {

	private InformationMapper() {
		
	}
	public static InformationResponseDTO mapToInformationResponseDTO(Information information) {
		InformationResponseDTO informationResponseDTO = new InformationResponseDTO();
		BeanUtils.copyProperties(information, informationResponseDTO);

		return informationResponseDTO;

	}
	
	

	public static List<InformationResponseDTO> mapToCandidateResponseDTO(List<Information> candidates) {

		return Optional.ofNullable(candidates).orElse(Collections.emptyList()).stream()
				.map(InformationMapper::mapToInformationResponseDTO).collect(Collectors.toList());

	}
}
