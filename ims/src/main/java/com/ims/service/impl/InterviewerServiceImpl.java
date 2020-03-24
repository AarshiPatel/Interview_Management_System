package com.ims.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.CandidateRepository;
import com.ims.dao.FeedbackHistoryRepository;
import com.ims.dao.FeedbackOfCandidateRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.JobRequirementCounterRepository;
import com.ims.dao.UserDetailsRepository;
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
import com.ims.entity.JobRequirementCounter;
import com.ims.entity.UserDetails;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnAuthorizedeException;
import com.ims.exception.UnprocessableEntity;
import com.ims.mapper.CandidateMapper;
import com.ims.mapper.FeedbackMapper;
import com.ims.mapper.InterviewMapper;
import com.ims.mapper.InterviwerMapper;
import com.ims.properties.ConstantProperties;
import com.ims.service.InterviewerServiceInterface;

/**
 * The Class InterviewerServiceImpl.
 */
@Service
public class InterviewerServiceImpl implements InterviewerServiceInterface {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(InterviewerServiceImpl.class);

	/** The interview repository. */
	@Autowired
	private InterviewRepository interviewRepository;

	/** The candidate repository. */
	@Autowired
	private CandidateRepository candidateRepository;

	/** The feedback repository. */
	@Autowired
	private FeedbackOfCandidateRepository feedbackRepository;

	/** The feedbackhistory repository. */
	@Autowired
	private FeedbackHistoryRepository feedbackhistoryRepository;

	/** The user details repository. */
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	/** The job requirement counter repository. */
	@Autowired
	private JobRequirementCounterRepository jobRequirementCounterRepository;

	/**
	 * Gets the all interviewer.
	 *
	 * This method use to get list of active interviewers
	 * 
	 * @return the all interviewer
	 */
	@Override
	public /* List<Interviewer> */ List<ShowInterviwerDTO> getAllInterviewer() {

		return InterviwerMapper.mapToInterviewerResponseDTO(userDetailsRepository.findAll());
	}

	/**
	 * Accept interview.
	 * 
	 * This method use to accept interview invitation
	 *
	 * @param interviewId the interview id
	 * @return true, if successful
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public Interview acceptInterview(long interviewId) throws UnAuthorizedeException, ResourceNotFoundException {
		Interview interview = interviewRepository.findByInterviewId(interviewId);
		if (Objects.nonNull(interview)) {
			if (interview.getFlag() != ConstantProperties.FLAG_ONE) {

				interview.setInterviewStatus(ConstantProperties.INTERVIEWACCEPT);
				interview.setFlag(ConstantProperties.INTERVIEWRESPONSEFLAG);
				interviewRepository.save(interview);
				return interview;

			} else {

				throw new UnAuthorizedeException(ConstantProperties.UNAUTHORIZED_USER);

			}
		} else {
			throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
		}

	}

	/**
	 * Reject interview.
	 * 
	 * This method use to reject interview invitation
	 *
	 * @param interviewId the interview id
	 * @param candidateId the candidate id
	 * @return true, if successful
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public Interview rejectInterview(long interviewId, long candidateId)
			throws UnAuthorizedeException, ResourceNotFoundException {
		Interview interview = interviewRepository.findByInterviewId(interviewId);

		if (Objects.nonNull(interview)) {
			if (interview.getFlag() != ConstantProperties.FLAG_ONE) {
				Candidate candidate = candidateRepository.findBycandidateId(candidateId);
				candidate.setInterview(null);
				candidateRepository.save(candidate);
				interview.setFlag(ConstantProperties.INTERVIEWRESPONSEFLAG);
				interview.setInterviewStatus(ConstantProperties.INTERVIEWREJECT);
				interviewRepository.save(interview);
				return interview;
			}
			throw new UnAuthorizedeException(ConstantProperties.UNAUTHORIZED_USER);

		}
		throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);

	}

	/**
	 * this method is for adding new Interviewer feedback.
	 *
	 * @param feedbackDTO as feedback DTO
	 * @return true, if successful false, when interviewer Feedback is not
	 *         generated.
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	@Override
	public FeedbackOFCandidate interviewerFeedback(FeedbackDTO feedbackDTO) throws UnprocessableEntity {
		Candidate candidate = candidateRepository.findBycandidateId(feedbackDTO.getCandidate());

		if (candidate.getRound() != ConstantProperties.FLAG_ONE) {

			FeedbackHistory feedbackhistory = new FeedbackHistory();
			if ((candidate.getInterview()) != null) {

				FeedbackOFCandidate feedback = feedbackRepository.findByCandidate(candidate);
				if (!Objects.isNull(feedback)) {

					BeanUtils.copyProperties(feedback, feedbackhistory);
					feedbackhistory.setUserId(feedback.getUserDetails().getUser().getUserId());
					feedbackhistory.setCandidateId(feedback.getCandidate().getCandidateId());
					feedbackhistory.setCandidateDepartment(feedback.getCandidate().getCandidateDepartment());
					feedbackhistory.setCandidateDesignation(feedback.getCandidate().getCandidateDesignation());
					feedbackhistory.setQualification(feedback.getCandidate().getQualification());

					feedbackhistoryRepository.save(feedbackhistory);

					feedbackRepository.deleteById(feedback.getFeedbackId());
				}
			}
		}

		FeedbackOFCandidate feedback = new FeedbackOFCandidate();
		Interview interview = interviewRepository.findByInterviewId(feedbackDTO.getInterview());
		UserDetails interviewer = userDetailsRepository.findByuserDetailsId(feedbackDTO.getInterviewer());
		if (candidate.getRound() < ConstantProperties.HR_ROUND) {

			BeanUtils.copyProperties(feedbackDTO, feedback);
			feedback.setHrleaderAbility(ConstantProperties.HR_ABILITY);

			if (feedbackDTO.getInterviewResult().equals(ConstantProperties.REJECT)) {

				LOG.info("Interview Result is{}", feedbackDTO.getInterviewResult());

				interview.setInterviewStatus(ConstantProperties.STATUS);
				candidate.setInterviewResult(ConstantProperties.REJECT);
				candidate.setInterview(interview);

			} else if (feedbackDTO.getInterviewResult().equals(ConstantProperties.ACCEPT)) {

				LOG.info("Interview Result is{}", feedbackDTO.getInterviewResult());

				if (feedbackDTO.getNextRound().equals(ConstantProperties.REQUIRED)) {

					LOG.info("Next Round is{}", feedbackDTO.getNextRound());

					candidate.setInterviewResult(ConstantProperties.INTERVIEW_RESULT);
					candidate.setRound(candidate.getRound() + 1);
					candidate.setInterview(null);

				} else if (feedbackDTO.getNextRound().equals(ConstantProperties.NOTREQUIRED)) {

					LOG.info("Next Round is{}", feedbackDTO.getNextRound());

					candidate.setInterviewResult(ConstantProperties.INTERVIEW_RESULT);
					candidate.setRound(ConstantProperties.HR_ROUND);
					candidate.setInterview(null);
				} else {
					LOG.info("Invalid interviewer result {}", feedbackDTO.getInterviewResult());
				}
			}

			feedback.setInterview(interview);
			feedback.setCandidate(candidate);
			feedback.setUserDetails(interviewer);
			feedbackRepository.save(feedback);

			candidateRepository.save(candidate);

			return feedback;
		}
		throw new UnprocessableEntity();
	}

	/**
	 * this method is for adding new Human resource feedback.
	 *
	 * @param feedbackDTO as feedback DTO
	 * @return true, if successful false, when humanresource Feedback is not
	 *         generated.
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	@Override
	public FeedbackOFCandidate humanResourceFeedback(FeedbackDTO feedbackDTO) throws UnprocessableEntity {
		Candidate candidate = candidateRepository.findBycandidateId(feedbackDTO.getCandidate());
		FeedbackHistory feedbackhistory = new FeedbackHistory();
		if (candidate.getInterview() != null) {

			FeedbackOFCandidate feedback = feedbackRepository.findByCandidate(candidate);
			if (!Objects.isNull(feedback)) {

				BeanUtils.copyProperties(feedback, feedbackhistory);
				feedbackhistory.setUserId(feedback.getUserDetails().getUser().getUserId());

				feedbackhistory.setCandidateId(feedback.getCandidate().getCandidateId());
				feedbackhistory.setCandidateDepartment(feedback.getCandidate().getCandidateDepartment());
				feedbackhistory.setCandidateDesignation(feedback.getCandidate().getCandidateDesignation());
				feedbackhistory.setQualification(feedback.getCandidate().getQualification());

				feedbackhistoryRepository.save(feedbackhistory);

				feedbackRepository.deleteById(feedback.getFeedbackId());
			}
		}

		FeedbackOFCandidate feedback = new FeedbackOFCandidate();
		Interview interview = interviewRepository.findByInterviewId(feedbackDTO.getInterview());
		UserDetails interviewer = userDetailsRepository.findByuserDetailsId(feedbackDTO.getInterviewer());

		if (candidate.getRound() == ConstantProperties.HR_ROUND) {

			BeanUtils.copyProperties(feedbackDTO, feedback);
			feedback.setTechnicalSkill(ConstantProperties.TECHNICAL_SKILL);

			if (feedbackDTO.getInterviewResult().equals(ConstantProperties.REJECT)) {

				LOG.info("Interview Result is{}", feedbackDTO.getInterviewResult());

				interview.setInterviewStatus(ConstantProperties.STATUS);
				candidate.setInterviewResult(ConstantProperties.REJECT);
				candidate.setInterview(interview);

			} else if (feedbackDTO.getInterviewResult().equals(ConstantProperties.HIRE)) {

				LOG.info("Interview Result is{}", feedbackDTO.getInterviewResult());

				candidate.setInterviewResult(ConstantProperties.HIRE);
				candidate.setInterview(interview);

				JobRequirementCounter checkJobRequirment = jobRequirementCounterRepository.checkJobRequirment(
						candidate.getCandidateDepartment(), candidate.getCandidateDesignation(),
						candidate.getCandidateGroup(), candidate.getCandidateSkill());
				if (Objects.nonNull(checkJobRequirment)) {
					checkJobRequirment.setRequirementCounter(
							checkJobRequirment.getRequirementCounter() - ConstantProperties.FLAG_ONE);
					if (checkJobRequirment.getRequirementCounter() == ConstantProperties.FLAG_ZERO) {
						checkJobRequirment.setActiveRequirement(ConstantProperties.FLAG_ZERO);
					}
					jobRequirementCounterRepository.save(checkJobRequirment);
				}
			} else {

				LOG.info("Invalid Interview Result {}", feedbackDTO.getInterviewResult());

			}
			feedback.setInterview(interview);
			feedback.setCandidate(candidate);
			feedback.setUserDetails(interviewer);
			feedbackRepository.save(feedback);

			candidateRepository.save(candidate);

			return feedback;
		}
		throw new UnprocessableEntity();
	}

	/**
	 * Interview of today.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CandidateDTO> interviewofToday(long userId) {

		List<CandidateDTO> candidateList = new ArrayList<>();
		LocalDate dateOfToday = LocalDate.now();
		for (CandidateDTO candidate : CandidateMapper.mapToCandidateDTO(candidateRepository.scheduledList(userId))) {

			LocalDate interviewdate = candidate.getInterview().getInterviewDate();
			if (dateOfToday.equals(interviewdate)) {
				candidateList.add(candidate);
			}

		}
		return candidateList;
	}

	/**
	 * Interview of tomorrow.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CandidateDTO> interviewofTomorrow(long userId) {

		List<CandidateDTO> candidateList = new ArrayList<>();
		LocalDate dateOfToday = LocalDate.now();
		LocalDate nextday = dateOfToday.plusDays(1);
		for (CandidateDTO candidate : CandidateMapper.mapToCandidateDTO(candidateRepository.scheduledList(userId))) {

			LocalDate interviewdate = candidate.getInterview().getInterviewDate();
			if (nextday.equals(interviewdate)) {
				candidateList.add(candidate);
			}

		}
		return candidateList;
	}

	/**
	 * Interview of yesterday.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CandidateDTO> interviewofYesterday(long userId) {

		List<CandidateDTO> candidateList = new ArrayList<>();
		LocalDate dateOfToday = LocalDate.now();
		LocalDate yesterday = dateOfToday.minusDays(1);
		for (CandidateDTO candidate : CandidateMapper.mapToCandidateDTO(candidateRepository.scheduledList(userId))) {

			LocalDate interviewdate = candidate.getInterview().getInterviewDate();
			if (yesterday.equals(interviewdate)) {
				candidateList.add(candidate);
			}
		}
		return candidateList;
	}

	/**
	 * Interview of nextweek.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CandidateDTO> interviewofNextweek(long userId) {

		List<CandidateDTO> candidateList = new ArrayList<>();
		LocalDate dateOfToday = LocalDate.now();
		LocalDate nextweek = dateOfToday.plusDays(6);
		for (CandidateDTO candidate : CandidateMapper.mapToCandidateDTO(candidateRepository.scheduledList(userId))) {

			LocalDate interviewdate = candidate.getInterview().getInterviewDate();
			if (nextweek.isAfter(interviewdate)) {
				candidateList.add(candidate);
			}
		}
		return candidateList;
	}

	/**
	 * Interview of lastweek.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CandidateDTO> interviewofLastweek(long userId) {

		List<CandidateDTO> candidateList = new ArrayList<>();
		LocalDate dateOfToday = LocalDate.now();
		LocalDate lastweek = dateOfToday.minusDays(6);
		for (CandidateDTO candidate : CandidateMapper.mapToCandidateDTO(candidateRepository.scheduledList(userId))) {

			LocalDate interviewdate = candidate.getInterview().getInterviewDate();
			if (lastweek.isBefore(interviewdate)) {
				candidateList.add(candidate);
			}
		}
		return candidateList;
	}

	/**
	 * Gets the list of accepted candidates.
	 *
	 * @param userId the user id
	 * @return the list of accepted candidates
	 */
	@Override
	public List<FeedbackResponseDTO> getListOfAcceptedCandidates(long userId) {
		List<FeedbackResponseDTO> feedbackResponseList = new ArrayList<>();
		for (FeedbackResponseDTO feedbackDTO : FeedbackMapper
				.mapToFeedbackResponseDTO(feedbackRepository.getAcceptedCandidates(userId))) {
			feedbackResponseList.add(feedbackDTO);
		}
		for (FeedbackResponseDTO feedbackDTO : FeedbackMapper
				.mapToFeedbackHistoryResponseDTO(feedbackhistoryRepository.getAcceptedCandidates(userId))) {
			feedbackResponseList.add(feedbackDTO);
		}
		return feedbackResponseList;
	}

	/**
	 * Gets the list of hired candidates.
	 *
	 * @param userId the user id
	 * @return the list of hired candidates
	 */
	@Override
	public List<ShowCandidateDTO> getListOfHiredCandidates(long userId) {
		return CandidateMapper.mapToCandidateResponseDTO(candidateRepository.getHiredCandidates(userId));

	}

	/**
	 * Gets the list of rejected candidates.
	 *
	 * @param userId the user id
	 * @return the list of rejected candidates
	 */
	@Override
	public List<ShowCandidateDTO> getListOfRejectedCandidates(long userId) {

		return CandidateMapper.mapToCandidateResponseDTO(candidateRepository.getRejectedCandidates(userId));
	}

	/**
	 * Feedback of today.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<FeedbackResponseDTO> feedbackOfToday(long userId) {

		List<FeedbackResponseDTO> feedbackList = new ArrayList<>();
		LocalDate dateOfToday = LocalDate.now();

		for (FeedbackResponseDTO feedback : FeedbackMapper
				.mapToFeedbackResponseDTO(feedbackRepository.givenFeedbackList(userId))) {

			LocalDate interviewdate = feedback.getDateOfInterview();
			if (dateOfToday.equals(interviewdate)) {
				feedbackList.add(feedback);
			}

		}

		return feedbackList;
	}

	/**
	 * Feedback of yesterday.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<FeedbackResponseDTO> feedbackOfYesterday(long userId) {

		List<FeedbackResponseDTO> feedbackList = new ArrayList<>();
		LocalDate dateOfToday = LocalDate.now();
		LocalDate yesterday = dateOfToday.minusDays(1);

		for (FeedbackResponseDTO feedback : FeedbackMapper
				.mapToFeedbackResponseDTO(feedbackRepository.givenFeedbackList(userId))) {

			LocalDate interviewdate = feedback.getDateOfInterview();
			if (yesterday.equals(interviewdate)) {
				feedbackList.add(feedback);
			}

		}
		return feedbackList;
	}

	/**
	 * Feedback of last week.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<FeedbackResponseDTO> feedbackOfLastweek(long userId) {

		List<FeedbackResponseDTO> feedbackList = new ArrayList<>();
		LocalDate dateOfToday = LocalDate.now();
		LocalDate lastweek = dateOfToday.minusDays(6);

		for (FeedbackResponseDTO feedback : FeedbackMapper
				.mapToFeedbackResponseDTO(feedbackRepository.givenFeedbackList(userId))) {

			LocalDate interviewdate = feedback.getDateOfInterview();
			if (lastweek.isBefore(interviewdate)) {
				feedbackList.add(feedback);
			}

		}
		return feedbackList;
	}

	/**
	 * View feedback by id.
	 *
	 * @param candidate the candidate
	 * @return the feedback OF candidate
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public FeedbackOFCandidate viewFeedbackById(Candidate candidate) throws ResourceNotFoundException {

		if (feedbackRepository.findByCandidate(candidate) != null) {
			return feedbackRepository.findByCandidate(candidate);
		}
		throw new ResourceNotFoundException(ConstantProperties.FEEDBACKOFCANDIDATE);
	}

	/**
	 * Find candidate by round.
	 *
	 * @param candidateId the candidate id
	 * @param roundNumber the round number
	 * @return the feedback history
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public FeedbackHistory findCandidateByRound(long candidateId, long roundNumber) throws ResourceNotFoundException {

		Candidate candidate = candidateRepository.findBycandidateId(candidateId);
		if (Objects.nonNull(candidate)) {
			int checkRound = candidate.getRound() - 1;

			if (checkRound == roundNumber) {
				FeedbackOFCandidate candidateFeedback = feedbackRepository.findByCandidate(candidate);
				FeedbackHistory feedbackHistory = new FeedbackHistory();

				BeanUtils.copyProperties(candidateFeedback, feedbackHistory);

				LOG.info("Candidate's department is{}", candidateFeedback.getCandidate().getCandidateDepartment());
				return feedbackHistory;
			} else {
				FeedbackHistory feedbackHistory = new FeedbackHistory();

				for (FeedbackHistory feedback : feedbackhistoryRepository.findAll()) {
					long canId = candidate.getCandidateId();

					if (feedback.getRound() == roundNumber && canId == feedback.getCandidateId()) {

						LOG.info("feedback is fetched from history table.");
						feedbackHistory = feedback;
					}
				}
				return feedbackHistory;
			}
		} else {

			throw new ResourceNotFoundException(ConstantProperties.FEEDBACKOFCANDIDATE);
		}

	}

	/**
	 * View all feedback.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public List<FeedbackResponseDTO> viewAllFeedback(long userId) throws ResourceNotFoundException {

		return FeedbackMapper.mapToFeedbackResponseDTO(feedbackRepository.givenFeedbackList(userId));

	}

	/**
	 * Scheduled list.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public List<ShowCandidateDTO> scheduledList(long userId) throws ResourceNotFoundException {
		return CandidateMapper.mapToCandidateResponseDTOInterview(candidateRepository.scheduledList(userId));
	}

	/**
	 * List of interview.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public List<InterviewResponseDTO> listOfInterview(long userId) throws ResourceNotFoundException {
		List<InterviewResponseDTO> interviewList = new ArrayList<>();
		for (InterviewResponseDTO interview : InterviewMapper
				.mapToInterviewResponseDTO(interviewRepository.getParticularInterviewByInterviewerId(userId))) {

			interviewList.add(interview);
		}
		return interviewList;

	}

	/**
	 * Pending interview approval list.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public List<ShowCandidateDTO> pendingInterviewApprovalList(long userId) throws ResourceNotFoundException {
		return CandidateMapper
				.mapToCandidateResponseDTOInterview(candidateRepository.candidatePendingInterviewApproval(userId));

	}

}
