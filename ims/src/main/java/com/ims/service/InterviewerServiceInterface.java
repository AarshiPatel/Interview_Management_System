package com.ims.service;

import java.util.List;

import com.ims.dto.CandidateDTO;
import com.ims.dto.FeedbackDTO;
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

/**
 * The Interface InterviewerServiceInterface.
 */
public interface InterviewerServiceInterface {

	/**
	 * Gets the all interviewer.
	 *
	 * @return the all interviewer
	 */
	List<ShowInterviwerDTO> getAllInterviewer();

	/**
	 * Accept interview.
	 *
	 * @param interviewId as interview id
	 * @return true, if successful false, if accept interview fails to give status
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	Interview acceptInterview(long interviewId) throws UnAuthorizedeException, ResourceNotFoundException;

	/**
	 * Reject interview.
	 *
	 * @param interviewId as interview id
	 * @param candidateId as candidate id
	 * @return true, if successful false, if reject interview fails to give status
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	Interview rejectInterview(long interviewId, long candidateId)
			throws UnAuthorizedeException, ResourceNotFoundException;

	/**
	 * Generate feedback.
	 *
	 * @param feedback as feedback
	 * @return true, if successful false, if feedback can't Fetch the data or
	 *         invalid data
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	FeedbackOFCandidate interviewerFeedback(FeedbackDTO feedback) throws UnprocessableEntity;

	/**
	 * Scheduled list.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<ShowCandidateDTO> scheduledList(long userId) throws ResourceNotFoundException;

	/**
	 * Gets the list of accepted candidates.
	 *
	 * @param userId the user id
	 * @return the list of accepted candidates
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<FeedbackResponseDTO> getListOfAcceptedCandidates(long userId) throws ResourceNotFoundException;

	/**
	 * Gets the list of hired candidates.
	 *
	 * @param userId the user id
	 * @return the list of hired candidates
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<ShowCandidateDTO> getListOfHiredCandidates(long userId) throws ResourceNotFoundException;

	/**
	 * Gets the list of rejected candidates.
	 *
	 * @param userId the user id
	 * @return the list of rejected candidates
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<ShowCandidateDTO> getListOfRejectedCandidates(long userId) throws ResourceNotFoundException;

	/**
	 * Human resource feedback.
	 *
	 * @param feedback the feedback
	 * @return true, if successful
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	FeedbackOFCandidate humanResourceFeedback(FeedbackDTO feedback) throws UnprocessableEntity;

	/**
	 * View feedback by id. This method gives last round's feedback of candidate by
	 * candidateId.
	 *
	 * @param candidate the candidate
	 * @return the feedback OF candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	public FeedbackOFCandidate viewFeedbackById(Candidate candidate) throws ResourceNotFoundException;

	/**
	 * Find candidate round.
	 *
	 * @param candidateId the candidate id
	 * @param roundNumber the round number
	 * @return the feedback history
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	public FeedbackHistory findCandidateByRound(long candidateId, long roundNumber) throws ResourceNotFoundException;

	/**
	 * Interviewof today.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<CandidateDTO> interviewofToday(long userId);

	/**
	 * Interviewof tomorrow.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<CandidateDTO> interviewofTomorrow(long userId);

	/**
	 * Interviewof yesterday.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<CandidateDTO> interviewofYesterday(long userId);

	/**
	 * Interviewof nextweek.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<CandidateDTO> interviewofNextweek(long userId);

	/**
	 * Interviewof lastweek.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<CandidateDTO> interviewofLastweek(long userId);

	/**
	 * Feedback of today.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<FeedbackResponseDTO> feedbackOfToday(long userId);

	/**
	 * Feedback of yesterday.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<FeedbackResponseDTO> feedbackOfYesterday(long userId);

	/**
	 * Feedback of lastweek.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<FeedbackResponseDTO> feedbackOfLastweek(long userId);

	/**
	 * View all feedback.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<FeedbackResponseDTO> viewAllFeedback(long userId) throws ResourceNotFoundException;

	/**
	 * List of interview.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<InterviewResponseDTO> listOfInterview(long userId) throws ResourceNotFoundException;

	/**
	 * Pending interview approval list.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	List<ShowCandidateDTO> pendingInterviewApprovalList(long userId) throws ResourceNotFoundException;

}
