package com.ims.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.JobRequirementCounterRepository;
import com.ims.dao.JobRequirementRepository;
import com.ims.dto.response.JobRequirmentResponseDTO;
import com.ims.entity.JobRequirement;
import com.ims.entity.JobRequirementCounter;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnAuthorizedeException;
import com.ims.mapper.JobRequirementMapper;
import com.ims.properties.ConstantProperties;
import com.ims.service.ResourceManagerService;

@Service
public class ResourceManagerServiceImpl implements ResourceManagerService {

	@Autowired
	JobRequirementRepository jobRequirementRepository;

	@Autowired
	JobRequirementCounterRepository jobRequirementCounterRepository;
	@Override
	public JobRequirmentResponseDTO acceptJobOpeningRequirment(long requrimentId) throws ResourceNotFoundException, UnAuthorizedeException {
		JobRequirement jobRequirement=jobRequirementRepository.findByRequirementId(requrimentId);
		if(Objects.nonNull(jobRequirement)) {
			if(jobRequirement.getJobOpeningIsApprove()!=ConstantProperties.FLAG_ONE) {
				jobRequirement.setJobOpeningIsApprove(ConstantProperties.FLAG_ONE);
				jobRequirementRepository.save(jobRequirement);
				JobRequirementCounter jobRequirementCounter = jobRequirementCounterRepository.findByIsRequirementAvalible(
						jobRequirement.getRequirementDepartment(), jobRequirement.getRequirementDesignation(),
						jobRequirement.getRequirementGroup(), jobRequirement.getRequirmentName(),
						jobRequirement.getExprianceRange());
				if (Objects.isNull(jobRequirementCounter)) {
					JobRequirementCounter jobCounter = new JobRequirementCounter();
					BeanUtils.copyProperties(jobRequirement, jobCounter);
					jobCounter.setRequirementCounter(jobRequirement.getJobOpening());
					jobCounter.setActiveRequirement(ConstantProperties.FLAG_ONE);
					jobRequirementCounterRepository.save(jobCounter);
					return JobRequirementMapper.mapToJobRequirementResponseDTO(jobRequirement);
				} else {
					jobRequirementCounter.setRequirementCounter(
							jobRequirement.getJobOpening() +  jobRequirementCounter.getRequirementCounter());
					jobRequirementCounter.setActiveRequirement(ConstantProperties.FLAG_ONE);
					jobRequirementCounterRepository.save(jobRequirementCounter);
					return JobRequirementMapper.mapToJobRequirementResponseDTO(jobRequirement);
				}

				
			}
			throw new UnAuthorizedeException(ConstantProperties.UNAUTHORIZED_USER); 
		}
		throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
	}
	@Override
	public JobRequirmentResponseDTO rejectJobOpeningRequirment(long requrimentId) throws ResourceNotFoundException, UnAuthorizedeException {
		JobRequirement jobRequirement=jobRequirementRepository.findByRequirementId(requrimentId);
		if(Objects.nonNull(jobRequirement)) {
			if(jobRequirement.getJobOpeningIsApprove()!=ConstantProperties.FLAG_ONE) {
				
			JobRequirmentResponseDTO jobRequirmentResponseDTO= JobRequirementMapper.mapToJobRequirementResponseDTO(jobRequirement);
			jobRequirementRepository.deleteById(requrimentId);
			return jobRequirmentResponseDTO;
			}
			throw new UnAuthorizedeException(ConstantProperties.UNAUTHORIZED_USER);
		}
		throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
	}
	@Override
	public List<JobRequirmentResponseDTO> getJobOpeningPendingApproval() {
		return JobRequirementMapper.mapToJobRequirementDTO(
				jobRequirementRepository.getJobOpeningPendingApprovalList());
	}
	@Override
	public List<JobRequirmentResponseDTO> getApprovedJobOpeningList() {
		return JobRequirementMapper.mapToJobRequirementDTO(
				jobRequirementRepository.getApprovedJobOpeningList());
	}

}
