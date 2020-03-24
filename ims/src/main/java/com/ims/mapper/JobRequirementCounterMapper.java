package com.ims.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.ims.dto.response.JobRequirementCounterResponseDTO;
import com.ims.entity.JobRequirementCounter;

public class JobRequirementCounterMapper {
	
	private JobRequirementCounterMapper() {}
	
	public static JobRequirementCounterResponseDTO mapToJobRequirementCounterResponseDTO(JobRequirementCounter jobRequirementCounter) {
		JobRequirementCounterResponseDTO jobRequirementCounterResponseDTO = new JobRequirementCounterResponseDTO();

	
		BeanUtils.copyProperties(jobRequirementCounter, jobRequirementCounterResponseDTO);

		return jobRequirementCounterResponseDTO;

	}

	public static List<JobRequirementCounterResponseDTO> mapToJobRequirementCounterResponseDTO(List<JobRequirementCounter> JobRequirement) {

		return Optional.ofNullable(JobRequirement).orElse(Collections.emptyList()).stream()
				.map(JobRequirementCounterMapper::mapToJobRequirementCounterResponseDTO).collect(Collectors.toList());

	}

}