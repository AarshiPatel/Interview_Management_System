package com.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.dto.CandidateDTO;
import com.ims.dto.FeedbackDTO;
import com.ims.dto.Resource;
import com.ims.dto.ShowCandidateDTO;
import com.ims.dto.ShowInterviwerDTO;
import com.ims.dto.response.FeedbackResponseDTO;
import com.ims.dto.response.InterviewResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.FeedbackHistory;
import com.ims.entity.FeedbackOFCandidate;
import com.ims.entity.Interview;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnAuthorizedeException;
import com.ims.exception.UnprocessableEntity;
import com.ims.properties.ConstantProperties;
import com.ims.service.InterviewerServiceInterface;

/**
 * The Class InterviewerController.
 */
/**
 * @author hardik.gohil
 *
 */
@CrossOrigin(origins = { "${settings.cors_origin}" })
@RestController

public class InterviewerController {

	/** The interviewer service. */
	@Autowired
	private InterviewerServiceInterface interviewerService;

	/**
	 * Gets the interviewer.
	 *
	 * This method get active list of interviewer
	 * 
	 * @return the interviewer
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/showinterviewer")
	public @ResponseBody ResponseEntity<Resource<ShowInterviwerDTO>> getInterviwer() throws ResourceNotFoundException {

		Resource<ShowInterviwerDTO> resource = new Resource<>(interviewerService.getAllInterviewer(),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Accept interview.
	 * 
	 * This method use to accept interview invitation
	 *
	 * @param interviewId the interview id
	 * @return the response entity
	 * @throws UnAuthorizedeException    the unauthorized exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/acceptinterview/{interviewId}")
	public @ResponseBody ResponseEntity<Resource<Interview>> acceptInterview(@PathVariable long interviewId)
			throws UnAuthorizedeException, ResourceNotFoundException {
		Interview isInterviewAccept = interviewerService.acceptInterview(interviewId);
		Resource<Interview> resource = new Resource<>();
		resource.setData(isInterviewAccept);
		resource.setSuccess(ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.CREATED);

	}

	/**
	 * Reject interview.
	 * 
	 * This method use to reject interview invitation
	 *
	 * @param interviewId the interview id
	 * @param candidateId the candidate id
	 * @return the response entity
	 * @throws UnAuthorizedeException    the unauthorized exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/rejectinterview/{interviewId}/{candidateId}")
	public @ResponseBody ResponseEntity<Resource<Interview>> rejectInterview(@PathVariable long interviewId,
			@PathVariable long candidateId) throws UnAuthorizedeException, ResourceNotFoundException {

		Interview isInterviewReject = interviewerService.rejectInterview(interviewId, candidateId);
		Resource<Interview> resource = new Resource<>();
		resource.setData(isInterviewReject);
		resource.setSuccess(ConstantProperties.TRUE_VALUE);
		return new ResponseEntity<>(resource, HttpStatus.CREATED);

	}

	/**
	 * Interviewer feedback.
	 *
	 * @param feedback as feedback
	 * @return the response entity if Feedback Created.
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	@PostMapping(value = "/interviewerfeedback")
	public @ResponseBody ResponseEntity<Resource<FeedbackOFCandidate>> interviewerFeedback(
			@RequestBody FeedbackDTO feedback) throws UnprocessableEntity {

		Resource<FeedbackOFCandidate> resource = new Resource<>(interviewerService.interviewerFeedback(feedback),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);

	}

	/**
	 * Human resource feedback.
	 *
	 * @param feedback the feedback
	 * @return the response entity
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	@PostMapping(value = "/humanresourcefeedback")
	public @ResponseBody ResponseEntity<Resource<FeedbackOFCandidate>> humanResourceFeedback(
			@RequestBody FeedbackDTO feedback) throws UnprocessableEntity {

		Resource<FeedbackOFCandidate> resource = new Resource<>(interviewerService.humanResourceFeedback(feedback),
				true);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);

	}

	/**
	 * Interview of today.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping(value = "/todaysinterview/{userId}")
	public @ResponseBody ResponseEntity<Resource<CandidateDTO>> interviewofToday(@PathVariable long userId) {

		Resource<CandidateDTO> resource = new Resource<>(interviewerService.interviewofToday(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	/**
	 * Interview of tomorrow.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping(value = "/tomorrowsinterview/{userId}")
	public @ResponseBody ResponseEntity<Resource<CandidateDTO>> interviewofTomorrow(@PathVariable long userId) {

		Resource<CandidateDTO> resource = new Resource<>(interviewerService.interviewofTomorrow(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Interview of yesterday.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping(value = "/yesterdaysinterview/{userId}")
	public ResponseEntity<Resource<CandidateDTO>> interviewofYesterday(@PathVariable long userId) {

		Resource<CandidateDTO> resource = new Resource<>(interviewerService.interviewofYesterday(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Interview of next week.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping(value = "/nextweeksinterview/{userId}")
	public ResponseEntity<Resource<CandidateDTO>> interviewofnextweek(@PathVariable long userId) {

		Resource<CandidateDTO> resource = new Resource<>(interviewerService.interviewofNextweek(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	/**
	 * Interview of last week.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping(value = "/lastweeksinterview/{userId}")
	public ResponseEntity<Resource<CandidateDTO>> interviewoflastweek(@PathVariable long userId) {

		Resource<CandidateDTO> resource = new Resource<>(interviewerService.interviewofLastweek(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}
	/**
	 * Gets the list of accepted candidate.
	 *
	 * @return the list of accepted candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/listofacceptedcandidate/{userId}")
	public @ResponseBody ResponseEntity<Resource<FeedbackResponseDTO>> getListOfAcceptedCandidate(@PathVariable long userId)
			throws ResourceNotFoundException {

		Resource<FeedbackResponseDTO> resource = new Resource<>(interviewerService.getListOfAcceptedCandidates(userId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}
	/**
	 * Gets the list of accepted candidate.
	 *
	 * @return the list of accepted candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/listofhiredcandidate/{userId}")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getListOfHiredCandidate(@PathVariable long userId)
			throws ResourceNotFoundException {

		Resource<ShowCandidateDTO> resource = new Resource<>(interviewerService.getListOfHiredCandidates(userId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Gets the list of rejected candidate.
	 *
	 * @return the list of rejected candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/listofrejectedcandidate/{userId}")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getListOfRejecetedCandidate(@PathVariable long userId)
			throws ResourceNotFoundException {
		Resource<ShowCandidateDTO> resource = new Resource<>(interviewerService.getListOfRejectedCandidates(userId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Feedback of today.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping(value = "/todaysfeedback/{userId}")
	public ResponseEntity<Resource<FeedbackResponseDTO>> feedbackOfToday(@PathVariable long userId) {

		Resource<FeedbackResponseDTO> resource = new Resource<>(interviewerService.feedbackOfToday(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Feedback of last week.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping(value = "/lastweeksfeedback/{userId}")
	public ResponseEntity<Resource<FeedbackResponseDTO>> feedbackOfLastweek(@PathVariable long userId) {

		Resource<FeedbackResponseDTO> resource = new Resource<>(interviewerService.feedbackOfLastweek(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Feedback of yesterday.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@GetMapping(value = "/yesterdayfeedback/{userId}")
	public ResponseEntity<Resource<FeedbackResponseDTO>> feedbackOfYesterday(@PathVariable long userId) {

		Resource<FeedbackResponseDTO> resource = new Resource<>(interviewerService.feedbackOfYesterday(userId), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * View feedback by CandidateId.
	 *
	 * @param candidateId the candidate id
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/viewfeedbackbyid/{candidateId}")
	public @ResponseBody ResponseEntity<Resource<FeedbackOFCandidate>> viewfeedbackbyid(@PathVariable long candidateId)
			throws ResourceNotFoundException {

		Candidate candidate = new Candidate();
		candidate.setCandidateId(candidateId);
		Resource<FeedbackOFCandidate> resource = new Resource<>(interviewerService.viewFeedbackById(candidate), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Gets the feedback of candidate by round.
	 *
	 * @param candidateId the candidate id
	 * @param roundnumber the round of interview
	 * @return the feed back by round
	 * @throws ResourceNotFoundException the resource not found exception
	 */

	@GetMapping(value = "/getRoundfeedback/{candidateId}/{roundnumber}")
	public @ResponseBody ResponseEntity<Resource<FeedbackHistory>> getFeedBackByRound(@PathVariable long candidateId,
			@PathVariable long roundnumber) throws ResourceNotFoundException {
		Resource<FeedbackHistory> resource = new Resource<>(
				interviewerService.findCandidateByRound(candidateId, roundnumber), true);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

	/**
	 * Display candidates scheduled 1.
	 *
	 * @param userId the userId
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/scheduledInterviewListforInterview/{userId}")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> displayCandidatesScheduled1(
			@PathVariable long userId) throws ResourceNotFoundException {

		Resource<ShowCandidateDTO> resource = new Resource<>(interviewerService.scheduledList(userId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}
	
	@GetMapping(value = "/getpenddingapproval/{userId}")
	public @ResponseBody ResponseEntity<Resource<ShowCandidateDTO>> getPenddingApproval(
			@PathVariable long userId) throws ResourceNotFoundException {

		Resource<ShowCandidateDTO> resource = new Resource<>(interviewerService.pendingInterviewApprovalList(userId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}


	/**
	 * View all feedback.
	 *
	 * @param userId the user id
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/viewallfeedback/{userId}")
	public @ResponseBody ResponseEntity<Resource<FeedbackResponseDTO>> viewAllFeedback(@PathVariable long userId)
			throws ResourceNotFoundException {

		Resource<FeedbackResponseDTO> resource = new Resource<>(interviewerService.viewAllFeedback(userId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}
	
	/**
	 * List of interview.
	 *
	 * @param userId the user id
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping(value = "/getInterviews/{userId}")
	public @ResponseBody ResponseEntity<Resource<InterviewResponseDTO>> listOfInterview(@PathVariable long userId)
			throws ResourceNotFoundException {

		Resource<InterviewResponseDTO> resource = new Resource<>(interviewerService.listOfInterview(userId),
				ConstantProperties.TRUE_VALUE);

		return new ResponseEntity<>(resource, HttpStatus.OK);

	}

}
