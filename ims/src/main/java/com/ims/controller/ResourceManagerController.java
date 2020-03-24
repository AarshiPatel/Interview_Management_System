package com.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ims.dto.Resource;
import com.ims.dto.response.JobRequirmentResponseDTO;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnAuthorizedeException;
import com.ims.properties.ConstantProperties;
import com.ims.service.ResourceManagerService;

@CrossOrigin(origins = { "${settings.cors_origin}" })
@RestController
public class ResourceManagerController {

	@Autowired 
	ResourceManagerService resourceManagerService;
	//Aarshi Home
	
	@GetMapping(value="/acceptjobopeningrequirment/{requrimentId}")
	public ResponseEntity<Resource<JobRequirmentResponseDTO>> acceptJobOpeningRequirment(@PathVariable long requrimentId) throws ResourceNotFoundException, UnAuthorizedeException{
		Resource<JobRequirmentResponseDTO> resource = new Resource<>(resourceManagerService.acceptJobOpeningRequirment(requrimentId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
		
	}
	//Aarshi Home

	//@DeleteMapping
	@GetMapping(value = "/rejectjobopeningrequirment/{requrimentId}")
	public ResponseEntity<Resource<JobRequirmentResponseDTO>> rejectJobOpeningRequirment(@PathVariable long requrimentId) throws ResourceNotFoundException, UnAuthorizedeException{
		Resource<JobRequirmentResponseDTO> resource = new Resource<>(resourceManagerService.rejectJobOpeningRequirment(requrimentId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
		
	}
	
	//Aarshi Home
	@GetMapping(value="/getjoboepningpendingapproval")
	 ResponseEntity<Resource<JobRequirmentResponseDTO>> getJobOpeningPendingApprovalList() throws ResourceNotFoundException {
		Resource<JobRequirmentResponseDTO> resource = new Resource<>(resourceManagerService.getJobOpeningPendingApproval(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	
	}

	//Aarshi Home
		@GetMapping(value="/getapprovedjobopening")
		 ResponseEntity<Resource<JobRequirmentResponseDTO>> getApprovedJobOpeningList() throws ResourceNotFoundException {
			Resource<JobRequirmentResponseDTO> resource = new Resource<>(resourceManagerService.getApprovedJobOpeningList(),
					ConstantProperties.TRUE_VALUE);

			return new ResponseEntity<>(resource, HttpStatus.OK);
		
		}	
	
	

	
}
