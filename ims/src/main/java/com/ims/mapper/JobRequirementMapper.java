package com.ims.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ims.dao.JobRequirementCounterRepository;
import com.ims.dto.ShowInterviwerDTO;
import com.ims.dto.response.JobRequirmentResponseDTO;
import com.ims.entity.JobRequirement;
import com.ims.entity.JobRequirementCounter;

public class JobRequirementMapper {

	
	private JobRequirementMapper() {
		
	}
	public static JobRequirmentResponseDTO mapToJobRequirementResponseDTO(JobRequirement jobRequirement) {
		JobRequirmentResponseDTO jobRequirmentResponseDTO = new JobRequirmentResponseDTO();
		
		BeanUtils.copyProperties(jobRequirement, jobRequirmentResponseDTO);
		jobRequirmentResponseDTO.setManagerName(jobRequirement.getManager().getName());
		jobRequirmentResponseDTO.setManagerId(jobRequirement.getManager().getUserDetailsId());
		return jobRequirmentResponseDTO;

	}

	public static List<JobRequirmentResponseDTO> mapToJobRequirementDTO(List<JobRequirement> requirement) {

		return Optional.ofNullable(requirement).orElse(Collections.emptyList()).stream()
				.map(JobRequirementMapper::mapToJobRequirementResponseDTO).collect(Collectors.toList());

	}
}
