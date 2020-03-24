package com.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.dto.Resource;
import com.ims.dto.request.JobRequirmentRequestDTO;
import com.ims.dto.response.JobRequirementCounterResponseDTO;
import com.ims.dto.response.JobRequirmentResponseDTO;
import com.ims.entity.JobRequirementCounter;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.properties.ConstantProperties;
import com.ims.service.JobRequirmentInterface;

@CrossOrigin(origins = { "${settings.cors_origin}" })

@RestController
public class JobRequirmentController {

	@Autowired
	private JobRequirmentInterface jobRequirmentService;

	/**
	 * Adds the job requirement.
	 *
	 * @param jobRequirment the job requirement
	 * @return the response entity
	 * @throws UnprocessableEntity  the unprocessable entity
	 * @throws ResourceNotFoundException
	 */
	@PostMapping(value = "/postjobrequirment")
	public ResponseEntity<Resource<JobRequirmentResponseDTO>> addJobRequirment(
			@RequestBody JobRequirmentRequestDTO jobRequirment) throws UnprocessableEntity, ResourceNotFoundException {
		Resource<JobRequirmentResponseDTO> resource = new Resource<>(jobRequirmentService.addJobRequirement(jobRequirment),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}

	/**
	 * Show requirements.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/getrequirements")
	public ResponseEntity<Resource<JobRequirementCounterResponseDTO>> showRequirements() {
		Resource<JobRequirementCounterResponseDTO> resource = new Resource<>(jobRequirmentService.getJobRequirement(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Gets the job requirement.
	 *
	 * @return the job requirement
	 */
	@GetMapping(value = "/getjobpostedrequirment")
	public ResponseEntity<Resource<JobRequirmentResponseDTO>> getJobRequirment() {
		Resource<JobRequirmentResponseDTO> resource = new Resource<>(jobRequirmentService.getJobRequirementList(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	/**
	 * Show specific requirements.
	 *
	 * @param requirementGroup      the requirement group
	 * @param requirementDepartment the requirement department
	 * @return the response entity
	 */
	@GetMapping(value = "/getspecificrequirements/{requirementGroup}/{requirementDepartment}")
	public ResponseEntity<Resource<JobRequirementCounterResponseDTO>> showspecificRequirements(
			@PathVariable String requirementGroup, @PathVariable String requirementDepartment) {

		Resource<JobRequirementCounterResponseDTO> resource = new Resource<>(
				jobRequirmentService.showJobRequirementCounter(requirementGroup, requirementDepartment),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	/**
	 * Show specific requirement switch id.
	 *
	 * @param jobRequirementCounterId the job requirement counter id
	 * @return the response entity
	 */
	@GetMapping(value = "/getspecificrequirementsbyid/{jobRequirementCounterId}")
	public ResponseEntity<Resource<JobRequirementCounterResponseDTO>> showspecificRequirementswithId(
			@PathVariable long jobRequirementCounterId) {

		Resource<JobRequirementCounterResponseDTO> resource = new Resource<>(
				jobRequirmentService.showRequirementWithId(jobRequirementCounterId), ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	//Aarshi Home
	@GetMapping(value="/getjobrequirmentpendingapprovalbyid/{managerId}")
	 ResponseEntity<Resource<JobRequirmentResponseDTO>> getJobRequirmentPendingApprovalByManagerId(@PathVariable long  managerId) throws ResourceNotFoundException {
		Resource<JobRequirmentResponseDTO> resource = new Resource<>(jobRequirmentService.getJobRequirementPendingApprovalListByManageId(managerId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	
	}
	
	//Aarshi Home
	@GetMapping(value="/getapprovejobrequirmentbyid/{managerId}")
	 ResponseEntity<Resource<JobRequirmentResponseDTO>> getApprovedJobRequirmentByManagerId(@PathVariable long  managerId) throws ResourceNotFoundException {
		Resource<JobRequirmentResponseDTO> resource = new Resource<>(jobRequirmentService.getApprovedJobRequirmentByManagerId(managerId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	
	}
	
	//Aarshi Home
	@GetMapping(value = "/getonejobrequirementbyid/{requrimentId}")
	 ResponseEntity<Resource<JobRequirmentResponseDTO>> getJobRequirmentById(@PathVariable long  requrimentId) throws ResourceNotFoundException {
		Resource<JobRequirmentResponseDTO> resource = new Resource<>(jobRequirmentService.getJobRequirementById(requrimentId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	//Aarshi Home
	@PostMapping(value="/updatejobrequirment")
	public ResponseEntity<Resource<JobRequirementCounter>> updateJobRequirment(
			@RequestBody JobRequirmentRequestDTO jobRequirment) throws UnprocessableEntity, ResourceNotFoundException {
		Resource<JobRequirementCounter> resource = new Resource<>(jobRequirmentService.updateJobRequirement(jobRequirment),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
}
