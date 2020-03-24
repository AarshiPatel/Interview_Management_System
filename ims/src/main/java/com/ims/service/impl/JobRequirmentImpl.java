package com.ims.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.JobRequirementCounterRepository;
import com.ims.dao.JobRequirementRepository;
import com.ims.dao.UserDetailsRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.request.JobRequirmentRequestDTO;
import com.ims.dto.response.JobRequirementCounterResponseDTO;
import com.ims.dto.response.JobRequirmentResponseDTO;
import com.ims.entity.JobRequirement;
import com.ims.entity.JobRequirementCounter;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.mapper.JobRequirementCounterMapper;
import com.ims.mapper.JobRequirementMapper;
import com.ims.properties.ConstantProperties;
import com.ims.service.JobRequirmentInterface;

@Service
public class JobRequirmentImpl implements JobRequirmentInterface {

	@Autowired
	JobRequirementRepository jobRequirementRepository;

	@Autowired
	JobRequirementCounterRepository jobRequirementCounterRepository;

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailsRepository userDetailsRepository;

	/**
	 * Adds the job requirement.
	 *
	 * @param jobRequirmentDTO the job requirement DTO
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */

	// Aarshi Home 23-03-2020
	@Override
	public JobRequirmentResponseDTO addJobRequirement(JobRequirmentRequestDTO jobRequirmentDTO)
			throws UnprocessableEntity, ResourceNotFoundException {

		if (jobRequirmentDTO.getExprianceRange() != null && jobRequirmentDTO.getRequirementDepartment() != null
				&& jobRequirmentDTO.getRequirementDesignation() != null
				&& jobRequirmentDTO.getRequirementGroup() != null && jobRequirmentDTO.getRequirmentName() != null
				&& jobRequirmentDTO.getJobOpening() != ConstantProperties.FLAG_ZERO
				&& jobRequirmentDTO.getUserId() != ConstantProperties.FLAG_ZERO) {
			User user = userRepository.findByUserId(jobRequirmentDTO.getUserId());

			UserDetails manager = userDetailsRepository.findByUser(user);
			if (Objects.nonNull(manager)) {

				JobRequirement oldJobRequriment = jobRequirementRepository.findJobRequirement(
						jobRequirmentDTO.getRequirementDepartment(), jobRequirmentDTO.getRequirementDesignation(),
						jobRequirmentDTO.getRequirementGroup(), jobRequirmentDTO.getRequirmentName(),
						jobRequirmentDTO.getExprianceRange(), manager.getUserDetailsId());

				if (Objects.nonNull(oldJobRequriment)) {
					
					JobRequirementCounter jobRequirement = jobRequirementCounterRepository.findByIsRequirementAvalible(
							jobRequirmentDTO.getRequirementDepartment(), jobRequirmentDTO.getRequirementDesignation(),
							jobRequirmentDTO.getRequirementGroup(), jobRequirmentDTO.getRequirmentName(),
							jobRequirmentDTO.getExprianceRange());
					if (Objects.nonNull(jobRequirement)) {
						jobRequirement.setRequirementCounter(jobRequirement.getRequirementCounter()-oldJobRequriment.getJobOpening());	
						jobRequirementCounterRepository.save(jobRequirement);
					}
					oldJobRequriment.setJobOpening(oldJobRequriment.getJobOpening() + jobRequirmentDTO.getJobOpening());
					oldJobRequriment.setJobPostedDate(LocalDate.now());
					oldJobRequriment.setJobOpeningIsApprove(ConstantProperties.FLAG_ZERO);
					
					jobRequirementRepository.save(oldJobRequriment);
					return JobRequirementMapper.mapToJobRequirementResponseDTO(oldJobRequriment);
				} else {
					JobRequirement newJobRequirement = new JobRequirement();
					BeanUtils.copyProperties(jobRequirmentDTO, newJobRequirement);
					newJobRequirement.setManager(manager);
					newJobRequirement.setJobPostedDate(LocalDate.now());
					newJobRequirement.setJobOpeningIsApprove(ConstantProperties.FLAG_ZERO);
					jobRequirementRepository.save(newJobRequirement);
					return JobRequirementMapper.mapToJobRequirementResponseDTO(newJobRequirement);

				}

//
//				JobRequirementCounter jobRequirement = jobRequirementCounterRepository.findByIsRequirementAvalible(
//						jobRequirmentDTO.getRequirementDepartment(), jobRequirmentDTO.getRequirementDesignation(),
//						jobRequirmentDTO.getRequirementGroup(), jobRequirmentDTO.getRequirmentName(),
//						jobRequirmentDTO.getExprianceRange());
//				if (Objects.isNull(jobRequirement)) {
//					JobRequirementCounter jobCounter = new JobRequirementCounter();
//					BeanUtils.copyProperties(jobRequirmentDTO, jobCounter);
//					jobCounter.setRequirementCounter(jobRequirmentDTO.getJobOpening());
//					jobCounter.setActiveRequirement(ConstantProperties.FLAG_ONE);
//					jobRequirementCounterRepository.save(jobCounter);
//					return jobCounter;
//				} else {
//					jobRequirement.setRequirementCounter(
//							jobRequirmentDTO.getJobOpening() + jobRequirement.getRequirementCounter());
//					jobRequirement.setActiveRequirement(ConstantProperties.FLAG_ONE);
//					jobRequirementCounterRepository.save(jobRequirement);
//					return jobRequirement;
//				}

			}
			throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
		}
		throw new UnprocessableEntity(ConstantProperties.FEILDS_MANDATORY_ERROR);

	}

	@Override
	public List<JobRequirementCounterResponseDTO> getJobRequirement() {
		return JobRequirementCounterMapper
				.mapToJobRequirementCounterResponseDTO(jobRequirementCounterRepository.findAllRequiremnet());

	}

	@Override
	public List<JobRequirmentResponseDTO> getJobRequirementList() {

		List<JobRequirementCounter> jobRequrimentCounter = jobRequirementCounterRepository.findAllRequiremnet();

		List<JobRequirmentResponseDTO> responseDTO = JobRequirementMapper
				.mapToJobRequirementDTO(jobRequirementRepository.getRequrirementList());
		for (JobRequirmentResponseDTO response : responseDTO) {
			for (JobRequirementCounter counter : jobRequrimentCounter) {
				if (counter.getRequirementDepartment().equals(response.getRequirementDepartment())
						&& counter.getRequirementDesignation().equals(response.getRequirementDesignation())
						&& counter.getExprianceRange().equals(response.getExprianceRange())
						&& counter.getRequirementGroup().equals(response.getRequirementGroup())) {
					response.setTotalNoOfOpeing(counter.getRequirementCounter());
				}
			}
		}
		return responseDTO;

	}

	@Override
	public List<JobRequirementCounterResponseDTO> showJobRequirementCounter(String requirementGroup,
			String requirementDepartment) {
		return JobRequirementCounterMapper.mapToJobRequirementCounterResponseDTO(
				jobRequirementCounterRepository.findSpecifiedRequirements(requirementGroup, requirementDepartment));
	}

	@Override
	public JobRequirementCounterResponseDTO showRequirementWithId(long jobRequirementCounterId) {
		return JobRequirementCounterMapper.mapToJobRequirementCounterResponseDTO(
				jobRequirementCounterRepository.findSpecifiedRequirementswithId(jobRequirementCounterId));

	}

	@Override
	public List<JobRequirmentResponseDTO> getJobRequirementPendingApprovalListByManageId(long userId)
			throws ResourceNotFoundException {
		User user = userRepository.findByUserId(userId);
		if (Objects.nonNull(user)) {
			UserDetails userDetails = userDetailsRepository.findByUser(user);
			if (Objects.nonNull(userDetails) && user.getUser_type().equals(ConstantProperties.USER_TYPE_MANAGER)) {
				return JobRequirementMapper.mapToJobRequirementDTO(
						jobRequirementRepository.getRequrirementPendingApprovalListByManagerId(userDetails.getUserDetailsId()));

			}

			//throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
		}

		throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);

	}

	@Override
	public JobRequirmentResponseDTO getJobRequirementById(long requrimentId) throws ResourceNotFoundException {
		JobRequirement jobRequirement = jobRequirementRepository.getRequriement(requrimentId);
		if (Objects.nonNull(jobRequirement)) {
			return JobRequirementMapper.mapToJobRequirementResponseDTO(jobRequirement);
		}
		throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
	}

	@Override
	public JobRequirementCounter updateJobRequirement(JobRequirmentRequestDTO jobRequirmentDTO)
			throws ResourceNotFoundException, UnprocessableEntity {
		if (jobRequirmentDTO.getExprianceRange() != null && jobRequirmentDTO.getRequirementDepartment() != null
				&& jobRequirmentDTO.getRequirementDesignation() != null
				&& jobRequirmentDTO.getRequirementGroup() != null && jobRequirmentDTO.getRequirmentName() != null
				&& jobRequirmentDTO.getJobOpening() != ConstantProperties.FLAG_ZERO
				&& jobRequirmentDTO.getUserId() != ConstantProperties.FLAG_ZERO) {

			JobRequirement jobRequirement = jobRequirementRepository
					.findByRequirementId(jobRequirmentDTO.getJobPostId());
			if (Objects.nonNull(jobRequirement)) {
				JobRequirementCounter jobRequirementConter = jobRequirementCounterRepository
						.findByIsRequirementAvalible(jobRequirmentDTO.getRequirementDepartment(),
								jobRequirmentDTO.getRequirementDesignation(), jobRequirmentDTO.getRequirementGroup(),
								jobRequirmentDTO.getRequirmentName(), jobRequirmentDTO.getExprianceRange());
				if (Objects.nonNull(jobRequirementConter)) {
					jobRequirementConter.setRequirementCounter(
							(jobRequirementConter.getRequirementCounter() - jobRequirement.getJobOpening()));

//					jobRequirementConter.setRequirementCounter(
//							(jobRequirementConter.getRequirementCounter() - jobRequirement.getJobOpening())
//									+ jobRequirmentDTO.getJobOpening());
					jobRequirementCounterRepository.save(jobRequirementConter);
					jobRequirement.setJobOpening(jobRequirmentDTO.getJobOpening());
					jobRequirement.setJobOpeningIsApprove(ConstantProperties.FLAG_ZERO);
					jobRequirement.setJobPostedDate(LocalDate.now());
					jobRequirementRepository.save(jobRequirement);
					return jobRequirementConter;
				}

				throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
			}
			throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
		}

		throw new UnprocessableEntity(ConstantProperties.FEILDS_MANDATORY_ERROR);
	}

	@Override
	public List<JobRequirmentResponseDTO> getApprovedJobRequirmentByManagerId(long userId) throws ResourceNotFoundException {
		User user = userRepository.findByUserId(userId);
		if (Objects.nonNull(user)) {
			UserDetails userDetails = userDetailsRepository.findByUser(user);
			if (Objects.nonNull(userDetails) && user.getUser_type().equals(ConstantProperties.USER_TYPE_MANAGER)) {
				return JobRequirementMapper.mapToJobRequirementDTO(
						jobRequirementRepository.getApprovedJobRequirmentByManagerId(userDetails.getUserDetailsId()));

			}

			//throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
		}

		throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
	}

}
