package com.ims.service;

import java.util.List;

import com.ims.dto.request.JobRequirmentRequestDTO;
import com.ims.dto.response.JobRequirementCounterResponseDTO;
import com.ims.dto.response.JobRequirmentResponseDTO;
import com.ims.entity.JobRequirementCounter;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;

public interface JobRequirmentInterface {

	/**
	 * Adds the job requirement.
	 *
	 * @param jobRequirment the job requirment
	 * @return the job requirement counter
	 * @throws UnprocessableEntity the unprocessable entity
	 * @throws ResourceNotFoundException 
	 */
	JobRequirmentResponseDTO addJobRequirement(JobRequirmentRequestDTO jobRequirment) throws UnprocessableEntity, ResourceNotFoundException;
	
	List<JobRequirmentResponseDTO> getJobRequirementList();
	
	/*
	 *  * Gets the job requirement.
	 *
	 * @return the job requirement
	 */
	List<JobRequirementCounterResponseDTO> getJobRequirement();
	
	
	List<JobRequirementCounterResponseDTO> showJobRequirementCounter(String requirementGroup, String requirementDepartment);
	
	JobRequirementCounterResponseDTO showRequirementWithId(long jobRequirementCounterId);

	List<JobRequirmentResponseDTO>  getJobRequirementPendingApprovalListByManageId(long managerId) throws ResourceNotFoundException;

	JobRequirmentResponseDTO getJobRequirementById(long requrimentId) throws ResourceNotFoundException;

	JobRequirementCounter updateJobRequirement(JobRequirmentRequestDTO jobRequirment) throws ResourceNotFoundException, UnprocessableEntity;

	List<JobRequirmentResponseDTO> getApprovedJobRequirmentByManagerId(long managerId) throws ResourceNotFoundException;
}
