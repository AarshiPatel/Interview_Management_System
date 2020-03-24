package com.ims.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.dto.CandidateDTO;
import com.ims.dto.Resource;
import com.ims.dto.ScheduleInterviewDTO;
import com.ims.dto.ShowCandidateDTO;
import com.ims.dto.request.InformationRequestDTO;
import com.ims.dto.response.CandidateResponseDTO;
import com.ims.dto.response.InformationResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.Interview;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.properties.ConstantProperties;
import com.ims.service.HumanResourseServiceInterface;

/**
 * The Class HumanResourseController.
 */
@CrossOrigin(origins = { "${settings.cors_origin}" })
@RestController

public class HumanResourseController {

	/** The response. */
	@Autowired
	HttpServletResponse response;

	/** The candidate service. */
	@Autowired
	private HumanResourseServiceInterface candidateService;

	/**
	 * Adds the candidate.
	 * 
	 * @param candidate the candidate
	 * @return the rest response
	 * @throws Exception
	 */
	@PostMapping(value = "/addcandidate")
	public @ResponseBody ResponseEntity<Resource<Candidate>> addCandidate(@RequestBody CandidateDTO candidate)
			throws Exception {

		Resource<Candidate> resource = new Resource<>(candidateService.addCandidate(candidate),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Display candidates.
	 *
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */

	/**
	 * Update candidate. This method is used to update candidate.
	 * 
	 * @param candidateDTO the candidate DTO
	 * @param candidateId  the candidate id
	 * @return the response entity
	 * @throws IOException               Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping(value = "/updatecandidate/{candidateId}")
	public @ResponseBody ResponseEntity<Resource<Candidate>> updateCandidate(@RequestBody CandidateDTO candidateDTO,
			@PathVariable long candidateId) throws IOException, ResourceNotFoundException {

		Resource<Candidate> resource = new Resource<>(candidateService.updateCandidate(candidateId, candidateDTO),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	/**
	 * Show resume. This method is show candidate resume on screen
	 * 
	 * @param candidateId the candidate id
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/showresume/{candidateId}")
	public @ResponseBody ResponseEntity<Boolean> showResume(@PathVariable long candidateId)
			throws ResourceNotFoundException {

		boolean isResumeAvailable = candidateService.getResume(candidateId, response);
		if (isResumeAvailable) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Gets the unscheduled interview. This method is use to get list of unscheduled
	 * interview
	 * 
	 * @return the unscheduled interview
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/unscheduledinterview")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getUnscheduledInterview()
			throws ResourceNotFoundException {

		Resource<ShowCandidateDTO> resource = new Resource<>(candidateService.getListOfNotScheduleInterview(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Delete Candidate.
	 *
	 * @param candidateId the candidate id
	 * @return the candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@DeleteMapping(value = "/deletecandidate/{candidateId}")
	public @ResponseBody ResponseEntity<Resource<Candidate>> deleteCandidateRecord(@PathVariable long candidateId)
			throws ResourceNotFoundException {

	
		Resource<Candidate> resource = new Resource<>(candidateService.deleteCandidate(candidateId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Gets the candidate. This method use to get one candidate using candidateId
	 *
	 * @param id the id
	 * @return the candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "getcandidate/{candidateId}")
	public @ResponseBody ResponseEntity<Resource<CandidateResponseDTO>> getCandidate(@PathVariable long candidateId)
			throws ResourceNotFoundException {

		Resource<CandidateResponseDTO> resource = new Resource<>(candidateService.getCandidate(candidateId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Schedule interview.
	 *
	 * This method is use to schedule an interview
	 * 
	 * @param interview the interview
	 * @return the response entity
	 * @throws BadRequestException       the bad request exception
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@PostMapping(value = "/scheduleinterview")
	public @ResponseBody ResponseEntity<Resource<Interview>> scheduleInterview(
			@RequestBody ScheduleInterviewDTO interview)
			throws BadRequestException, UnprocessableEntity, ResourceNotFoundException {

		Resource<Interview> resource = new Resource<>(candidateService.scheduleInterview(interview),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);

	}

	@GetMapping(value = "/getpenddingapproval")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getPenddingApproval() {

		Resource<ShowCandidateDTO> resource = new Resource<>(candidateService.getPenddingInterviewApproval(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Gets the list of scheduled interview.
	 *
	 * @return the list of scheduled interview
	 * @throws ResourceNotFoundException
	 */
	@GetMapping(value = "/listofscheduleinterview")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getListOfScheduledInterview() {

		Resource<ShowCandidateDTO> resource = new Resource<>(candidateService.getListOfScheduleInterview(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Gets the list of accepted candidate.
	 *
	 * @return the list of accepted candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/listofhirecandidate")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getListOfHireCandidate()
			throws ResourceNotFoundException {

		Resource<ShowCandidateDTO> resource = new Resource<>(candidateService.getListOfHireCandidates(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Gets the list of rejected candidate.
	 *
	 * @return the list of rejected candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/listofrejectedcandidate")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getListOfRejecetedCandidate()
			throws ResourceNotFoundException {
		Resource<ShowCandidateDTO> resource = new Resource<>(candidateService.getListOfRejectedCandidates(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Download hire candidate excel.
	 *
	 * @param response the response
	 * @return the response entity
	 * @throws IOException               Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException
	 */
	@GetMapping(value = "/downloadhirecandidateexcel")
	public @ResponseBody ResponseEntity<Boolean> downloadHireCandidateExcel(HttpServletResponse response)
			throws IOException, ResourceNotFoundException {
		boolean isExcelGenrated = candidateService.downloadHireCandidateExcel();
		if (isExcelGenrated) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Download reject candidate excel.
	 *
	 * @param response the response
	 * @return the response entity
	 * @throws IOException               Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException
	 */
	@GetMapping(value = "/downloadrejectcandidateexcel")
	public @ResponseBody ResponseEntity<Boolean> downloadRejectCandidateExcel(HttpServletResponse response)
			throws IOException, ResourceNotFoundException {
		boolean isExcelGenrated = candidateService.downloadRejectCandidateExcel();
		if (isExcelGenrated) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		throw new ResourceNotFoundException();

	}

	/**
	 * Reschedule interview.
	 *
	 * @param interview the interview
	 * @return the response entity
	 * @throws UnprocessableEntity the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PostMapping(value = "/rescheduleinterview")
	public @ResponseBody ResponseEntity<Resource<Interview>> rescheduleInterview(
			@RequestBody ScheduleInterviewDTO interview) throws UnprocessableEntity, ResourceNotFoundException {
		Resource<Interview> resource = new Resource<>(candidateService.rescheduleInterview(interview),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);

	}

	/**
	 * Display candidates.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/showcandidate")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> displayCandidates() {

		Resource<ShowCandidateDTO> resource = new Resource<>(candidateService.getCandidates(), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}


	/**
	 * Gets the interview by date.
	 *
	 * @return the interview by date
	 * @throws Exception the exception
	 */
	@GetMapping(value = "/gettodaysinterview")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getInterviewBydate() throws Exception {

		Resource<ShowCandidateDTO> resource = new Resource<>(candidateService.getInterviewOfToday(), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}
	
	/**
	 * Gets the list of candidates.
	 *
	 * @return the list of candidates
	 */
	@GetMapping(value="/listofcandidate")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getListOfCandidates()
			 {
		Resource<ShowCandidateDTO> resource = new Resource<>(candidateService.getCandidateList(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PostMapping(value="/saveinfomation")
	public @ResponseBody ResponseEntity<Resource> saveInformation(@RequestBody InformationRequestDTO infomation){
		candidateService.saveInfomation(infomation);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/getinformation")
	public @ResponseBody ResponseEntity<Resource<InformationResponseDTO>> getInformation(){
		Resource<InformationResponseDTO> resource = new Resource<>(candidateService.getInformation(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
}
