package com.ims.service;

import java.util.List;

import com.ims.dto.response.JobRequirmentResponseDTO;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnAuthorizedeException;

public interface ResourceManagerService {
  
	JobRequirmentResponseDTO acceptJobOpeningRequirment(long requrimentId) throws ResourceNotFoundException, UnAuthorizedeException;

	JobRequirmentResponseDTO rejectJobOpeningRequirment(long requrimentId) throws ResourceNotFoundException, UnAuthorizedeException;

	 List<JobRequirmentResponseDTO> getJobOpeningPendingApproval();

	 List<JobRequirmentResponseDTO>  getApprovedJobOpeningList();
}
