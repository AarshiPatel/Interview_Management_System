package com.ims.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ims.dao.CandidateRepository;
import com.ims.dao.FeedbackHistoryRepository;
import com.ims.dao.FeedbackOfCandidateRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.JobRequirementCounterRepository;
import com.ims.dao.UserDetailsRepository;
import com.ims.dto.CandidateDTO;
import com.ims.dto.FeedbackDTO;
import com.ims.dto.ShowCandidateDTO;
import com.ims.dto.response.FeedbackResponseDTO;
import com.ims.dto.response.InterviewResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.FeedbackHistory;
import com.ims.entity.FeedbackOFCandidate;
import com.ims.entity.Interview;
import com.ims.entity.JobRequirementCounter;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnAuthorizedeException;
import com.ims.exception.UnprocessableEntity;

/**
 * The Class LoginServiceTests.
 */
@SpringBootTest
public class InterviewerServiceTest {

	/** The interviewer service. */
	@InjectMocks
	private InterviewerServiceImpl interviewerService;

	/** The interview repository. */
	@Mock
	private InterviewRepository interviewRepository;

	/** The candidate repository. */
	@Mock
	private CandidateRepository candidateRepository;

	/** The feedback repository. */
	@Mock
	private FeedbackOfCandidateRepository feedbackRepository;

	/** The feedback history repository. */
	@Mock
	private FeedbackHistoryRepository feedbackHistoryRepository;

	/** The user details repository. */
	@Mock
	private UserDetailsRepository userDetailsRepository;

	/** The job requirement counter repository. */
	@Mock
	private JobRequirementCounterRepository jobRequirementCounterRepository;

	/** The Constant interviewerId. */
	private final static long INTERVIEWER_ID = Long.parseLong("1");

	/** The Constant interviewId. */
	private final static long INTERVIEW_ID = Long.parseLong("1");

	/** The Constant candidateId. */
	private final static long CANDIDATE_ID = Long.parseLong("1");

	/** The Constant CANDIDATE_NAME. */
	private final static String CANDIDATE_NAME = "abcd";

	/** The Constant INTERVIEWER_NAME. */
	private final static String INTERVIEWER_NAME = "abcd";

	/** The Constant FEEDBACK_ID. */
	private final static long FEEDBACK_ID = Long.parseLong("1");

	/** The Constant FEEDBACKSECOND_ID. */
	private final static long FEEDBACKSECOND_ID = Long.parseLong("2");

	/** The Constant ROUND. */
	private final static int ROUND = 6;

	/** The Constant ROUND_1. */
	private final static int ROUND_1 = 1;

	/** The Constant ROUND_2. */
	private final static int ROUND_2 = 2;

	/** The Constant EXPERIENCE. */
	private final static double EXPERIENCE = 2.2;

	/** The Constant JOB_ROLE. */
	private final static String JOB_ROLE = "Engineer";

	/** The Constant DATE_OF_INTERVIEW. */
	private final static LocalDate DATE_OF_INTERVIEW = LocalDate.of(2020, 3, 1);

	/** The Constant TECHNICAL_SKILL. */
	private final static String TECHNICAL_SKILL = "5";

	/** The Constant ORGANIZATIONAL_SKILL. */
	private final static String ORGANIZATIONAL_SKILL = "5";

	/** The Constant EDUCATIONAL_SKILL. */
	private final static String EDUCATIONAL_SKILL = "5";

	/** The Constant COMMUNICATION_SKILL. */
	private final static String COMMUNICATION_SKILL = "5";

	/** The Constant HR_ABILITY_SKILL. */
	private final static String HR_ABILITY_SKILL = "5";

	/** The Constant OVERALL_RATING. */
	private final static String OVERALL_RATING = "5";

	/** The Constant INTERVIEW_RESULT_ACCEPT. */
	private final static String INTERVIEW_RESULT_ACCEPT = "Accept";

	/** The Constant INTERVIEW_STATUS_SCHEDULED. */
	private final static String INTERVIEW_STATUS_SCHEDULED = "Scheduled";

	/** The Constant INTERVIEW_RESULT_REJECT. */
	private final static String INTERVIEW_RESULT_REJECT = "Reject";

	/** The Constant INTERVIEW_RESULT_HIRE. */
	private final static String INTERVIEW_RESULT_HIRE = "Hire";

	/** The Constant NEXT_ROUND. */
	private final static String NEXT_ROUND = "Yes";

	/** The Constant FEEDBACK_HISTORY_ID. */
	private final static long FEEDBACK_HISTORY_ID = Long.parseLong("1");

	/** The Constant CANDIDATE_DESIGNATION. */
	private final static String CANDIDATE_DESIGNATION = "Engineer";

	/** The Constant CANDIDATE_DEPARTMENT. */
	private final static String CANDIDATE_DEPARTMENT = "PES";

	/** The Constant REQUIRED. */
	private final static String REQUIRED = "Required";

	/** The Constant NOTREQUIRED. */
	private final static String NOTREQUIRED = "NotRequired"; // NOTHING

	/** The Constant NOTHING. */
	private final static String NOTHING = "nothing";

	/** The Constant TODAY. */
	private final static LocalDate TODAY = LocalDate.now();

	/** The Constant TOMORROW. */
	private final static LocalDate TOMORROW = TODAY.plusDays(1);

	/** The Constant YESTERDAY. */
	private final static LocalDate YESTERDAY = TODAY.minusDays(1);

	/** The Constant NEXTWEEK. */
	private final static LocalDate NEXTWEEK = TODAY.plusDays(5);

	/** The Constant LASTWEEK. */
	private final static LocalDate LASTWEEK = TODAY.minusDays(5);

	/** The Constant USERID. */
	private final static long USERID = Long.parseLong("1");

	/** The Constant emailId. */
	private final static String USERNAME = "vahak15@gmail.com";

	/** The Constant password. */
	private final static String PASSWORD = "AB@123";

	/** The Constant user_interviewer. */
	private final static String USER_INTERVIEWER = "interviewer";

	/** The Constant CANDIDATE_SKILL. */
	private final static String CANDIDATE_SKILL = "java";

	/** The Constant CANDIDATE_GROUP. */
	private final static String CANDIDATE_GROUP = "PES";

	/** The Constant FLAG_ONE. */
	private final static int FLAG_ONE = 1;

	/** The Constant REJECT_CANDIDATE. */
	private static final String REJECT_CANDIDATE = "Reject";

	/** The Constant ACCEPT_CANDIDATE. */
	private static final String ACCEPT_CANDIDATE = "NAN";

	/** The Constant HIRE_CANDIDATE. */
	private static final String HIRE_CANDIDATE = "Hire";

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test get all interviewer. Get All Active Interviewer
	 */
	@Test
	public void testGetAllInterviewer() {

		List<UserDetails> interviewerList = new ArrayList<>();
		interviewerList.add(getUserInterviewer());
		when(userDetailsRepository.findAll()).thenReturn(interviewerList);
		assertNotNull(interviewerService.getAllInterviewer());

	}

	/**
	 * Test accept interview. This method test accept interview method
	 *
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testAcceptInterview() throws UnAuthorizedeException, ResourceNotFoundException {
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(getInterview());
		assertNotNull(interviewerService.acceptInterview(anyLong()));
	}

	/**
	 * Test accept interview when interview not found. This method test accept
	 * interview method when interview not found
	 *
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testAcceptInterviewWhenInterviewNotFound() throws UnAuthorizedeException, ResourceNotFoundException {
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(null);
		interviewerService.acceptInterview(anyLong());
	}

	/**
	 * Test accept interview when interviewer already response.
	 * 
	 * This method test accept interview method when interviewer already given
	 * responded
	 *
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnAuthorizedeException.class)
	public void testAcceptInterviewWhenInterviwerAlreadyResponse()
			throws UnAuthorizedeException, ResourceNotFoundException {
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(getInterviewAlreadyResponsed());

		interviewerService.acceptInterview(INTERVIEW_ID);
	}

	/**
	 * Test reject interview.
	 * 
	 * This method test accept interview method invitation
	 *
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testRejectInterview() throws UnAuthorizedeException, ResourceNotFoundException {
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(getInterview());
		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(getCandidate());
		assertNotNull(interviewerService.rejectInterview(INTERVIEW_ID, CANDIDATE_ID));

	}

	/**
	 * Test reject interview when interview not found. This method test reject
	 * interview invitation when interview not found
	 *
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testRejectInterviewWhenInterviewNotFound() throws UnAuthorizedeException, ResourceNotFoundException {
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(null);
		interviewerService.rejectInterview(INTERVIEW_ID, CANDIDATE_ID);

	}

	/**
	 * Test reject interview when interviwer already response.
	 * 
	 * This method test reject interview method when interviewer already given
	 * responded
	 *
	 * @throws UnAuthorizedeException    the un authorizede exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnAuthorizedeException.class)
	public void testRejectInterviewWhenInterviwerAlreadyResponse()
			throws UnAuthorizedeException, ResourceNotFoundException {
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(getInterviewAlreadyResponsed());
		interviewerService.rejectInterview(INTERVIEW_ID, CANDIDATE_ID);

	}

	/**
	 * Test case of human resource Feedback when interview result hire.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void testHumanResourceFeedbackWhenInterviewResultHire()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();

		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();

		Candidate candidate = getCandidate();
		candidate.setInterviewResult(INTERVIEW_RESULT_HIRE);

		feedback.setCandidate(candidate);

		Interview interview = getInterview();

		UserDetails userDetails = getUserInterviewer();
		userDetails.setUser(getInterviewer());
		feedback.setUserDetails(userDetails);
		feedbackHistory.setUserId(getInterviewer().getUserId());

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(jobRequirementCounterRepository.checkJobRequirment(anyString(), anyString(), anyString(), anyString()))
				.thenReturn(getCounter());
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);

		assertNotNull(interviewerService.humanResourceFeedback(feedbackDTO));

	}

	/**
	 * Test human resource feedback when interview result hire avalible requriment
	 * zero.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void testHumanResourceFeedbackWhenInterviewResultHireAvalibleRequrimentZero()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();

		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();

		UserDetails userDetails = getUserInterviewer();
		userDetails.setUser(getInterviewer());

		feedback.setUserDetails(userDetails);

		feedbackHistory.setUserId(getInterviewer().getUserId());

		Candidate candidate = getCandidate();
		candidate.setInterviewResult(INTERVIEW_RESULT_HIRE);

		feedback.setCandidate(candidate);

		JobRequirementCounter jobRequirementCounter = getCounter();
		jobRequirementCounter.setRequirementCounter(FLAG_ONE);
		Interview interview = getInterview();

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(jobRequirementCounterRepository.checkJobRequirment(anyString(), anyString(), anyString(), anyString()))
				.thenReturn(jobRequirementCounter);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);

		assertNotNull(interviewerService.humanResourceFeedback(feedbackDTO));

	}

	/**
	 * Test case of human resource Feedback when interview result reject.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void testHumanResourceFeedbackWhenInterviewResultReject()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_REJECT);

		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();
		feedbackHistory.setUserId(getInterviewer().getUserId());

		Candidate candidate = getCandidate();
		Interview interview = getInterview();

		UserDetails userDetails = getUserInterviewer();
		userDetails.setUser(getInterviewer());
		feedback.setUserDetails(userDetails);
		feedback.setCandidate(candidate);
		feedbackHistory.setUserId(getInterviewer().getUserId());

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNotNull(interviewerService.humanResourceFeedback(feedbackDTO));

	}

	/**
	 * Test human resource feedback when interview result false.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void testHumanResourceFeedbackWhenInterviewResultFalse()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_ACCEPT);

		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();

		UserDetails userDetails = getUserInterviewer();
		userDetails.setUser(getInterviewer());

		feedback.setUserDetails(userDetails);

		feedbackHistory.setUserId(getInterviewer().getUserId());

		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		feedback.setCandidate(candidate);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNotNull(interviewerService.humanResourceFeedback(feedbackDTO));

	}

	/**
	 * Test case of human resource Feedback when get interview null.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void testHumanResourceFeedbackWhenGetInterviewNull() throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_REJECT);

		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();

		Candidate candidate = getCandidate();
		candidate.setInterview(null);

		Interview interview = getInterview();

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNotNull(interviewerService.humanResourceFeedback(feedbackDTO));

	}

	/**
	 * Test case of human resource Feedback when get candidate null.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void testHumanResourceFeedbackWhenGetCandidateNull() throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();

		FeedbackHistory feedbackHistory = getFeedbackHistory();

		getFeedback();

		Candidate candidate = getCandidate();

		Interview interview = getInterview();

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);

		when(feedbackRepository.findByCandidate(any())).thenReturn(null);

		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);

		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());

		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);

		assertNotNull(interviewerService.humanResourceFeedback(feedbackDTO));

	}

	/**
	 * Test case of human resource Feedback when get round not equals six.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testHumanResourceFeedbackWhenGetRoundNotEqualsSix()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_REJECT);

		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();

		UserDetails userDetails = getUserInterviewer();
		userDetails.setUser(getInterviewer());
		feedback.setUserDetails(userDetails);
		feedbackHistory.setUserId(getInterviewer().getUserId());
		Candidate candidate = getCandidate();
		candidate.setRound(ROUND_1);
		feedback.setCandidate(candidate);

		getInterview();

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());

		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNull(interviewerService.humanResourceFeedback(feedbackDTO));

	}

	/**
	 * Test schedule interview list.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testScheduleInterviewList() throws ResourceNotFoundException {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();

		Interview interview = getInterviewAlreadyResponsed();

		UserDetails interviewer = getUserInterviewer();

		interviewer.setName(INTERVIEWER_NAME);
		interview.setInterviewer(interviewer);

		candidate.setInterview(interview);
		candidateList.add(candidate);

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);
		assertNotNull(interviewerService.scheduledList(anyLong()));
	}

	/**
	 * Test pending interview list.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testPendingInterviewList() throws ResourceNotFoundException {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();
		Interview interview = getInterviewAlreadyResponsed();

		UserDetails interviewer = getUserInterviewer();

		interviewer.setName(INTERVIEWER_NAME);
		interview.setInterviewer(interviewer);

		candidate.setInterview(interview);

		candidateList.add(candidate);

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.candidatePendingInterviewApproval(anyLong())).thenReturn(candidateList);
		assertNotNull(interviewerService.pendingInterviewApprovalList(anyLong()));
	}

	/**
	 * Test interviewer feedback.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testInterviewerFeedback() throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_REJECT);

		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();

		Candidate candidate = getCandidate();

		UserDetails userDetails = getUserInterviewer();
		userDetails.setUser(getInterviewer());
		feedback.setUserDetails(userDetails);
		feedbackHistory.setUserId(getInterviewer().getUserId());

		Interview interview = getInterview();
		candidate.setInterview(interview);
		feedback.setCandidate(candidate);
		feedbackHistory.setCandidateId(CANDIDATE_ID);
		feedbackHistory.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		feedbackHistory.setCandidateDesignation(CANDIDATE_DESIGNATION);
		feedbackHistory.setQualification(getCandidate().getQualification());

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());

		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		interviewerService.interviewerFeedback(feedbackDTO);

	}

	/**
	 * Interviewer feedback return false.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test(expected = UnprocessableEntity.class)
	public void interviewerFeedbackReturnFalse() throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();

		Candidate candidate = getCandidate();
		getInterview();

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);

		interviewerService.interviewerFeedback(feedbackDTO);

	}

	/**
	 * Interviewer feedback rejected.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void interviewerFeedbackRejected() throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();
		candidate.setRound(ROUND_1);
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_REJECT);
		feedbackHistory.setCandidateId(CANDIDATE_ID);
		feedbackHistory.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		feedbackHistory.setCandidateDesignation(CANDIDATE_DESIGNATION);
		feedbackHistory.setQualification(getCandidate().getQualification());

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNotNull(interviewerService.interviewerFeedback(feedbackDTO));

	}

	/**
	 * Interviewer feedback interview is null.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test(expected = UnprocessableEntity.class)
	public void interviewerFeedbackInterviewIsNull() throws ResourceNotFoundException, UnprocessableEntity {
		FeedbackDTO feedbackDTO = getFeedbackDTO();
		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();
		candidate.setRound(ROUND);
		candidate.setInterview(null);
		feedbackHistory.setCandidateId(CANDIDATE_ID);
		feedbackHistory.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		feedbackHistory.setCandidateDesignation(CANDIDATE_DESIGNATION);
		feedbackHistory.setQualification(getCandidate().getQualification());

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		interviewerService.interviewerFeedback(feedbackDTO);

	}

	/**
	 * Interviewer feedback acceptedand required next round.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void interviewerFeedbackAcceptedandRequiredNextRound()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();
		candidate.setRound(ROUND_1);
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedbackDTO.setNextRound(REQUIRED);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNotNull(interviewerService.interviewerFeedback(feedbackDTO));

	}

	/**
	 * Interviewer feedback acceptedand required next round interview result isnot
	 * accept.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void interviewerFeedbackAcceptedandRequiredNextRoundInterviewResultIsnotAccept()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();
		candidate.setRound(ROUND_1);
		feedbackDTO.setInterviewResult(NOTHING);
		feedbackDTO.setNextRound(REQUIRED);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNotNull(interviewerService.interviewerFeedback(feedbackDTO));

	}

	/**
	 * Interviewer feedback acceptedand not required next round.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void interviewerFeedbackAcceptedandNotRequiredNextRound()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();
		candidate.setRound(ROUND_1);
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedbackDTO.setNextRound(NOTREQUIRED);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNotNull(interviewerService.interviewerFeedback(feedbackDTO));

	}

	/**
	 * Interviewer feedback acceptedand not required next roundis not notrequired.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws UnprocessableEntity       the unprocessable entity
	 */
	@Test
	public void interviewerFeedbackAcceptedandNotRequiredNextRoundisNotNotrequired()
			throws ResourceNotFoundException, UnprocessableEntity {

		FeedbackDTO feedbackDTO = getFeedbackDTO();
		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		FeedbackHistory feedbackHistory = getFeedbackHistory();
		FeedbackOFCandidate feedback = getFeedback();
		candidate.setRound(ROUND_1);
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedbackDTO.setNextRound(NOTHING);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(feedback);
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(interview);
		when(userDetailsRepository.findByuserDetailsId(anyLong())).thenReturn(getUserInterviewer());
		when(feedbackHistoryRepository.findByFeedbackHistoryId(anyLong())).thenReturn(feedbackHistory);
		assertNotNull(interviewerService.interviewerFeedback(feedbackDTO));

	}

	/**
	 * Test find candidate by round.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testFindCandidateByRound() throws ResourceNotFoundException {

		Candidate candidate = getCandidate();
		FeedbackOFCandidate candidateFeedback = getFeedback();
		List<FeedbackHistory> feedbackHistory = getFeedbackHistoryList();
		candidate.setRound(ROUND_2);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(candidateFeedback);
		when(feedbackHistoryRepository.findAll()).thenReturn(feedbackHistory);

		assertNotNull(interviewerService.findCandidateByRound(CANDIDATE_ID, ROUND_1));
	}

	/**
	 * Test find candidate by round exception.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testFindCandidateByRoundException() throws ResourceNotFoundException {

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(null);
		interviewerService.findCandidateByRound(CANDIDATE_ID, ROUND_1);
	}

	/**
	 * Test find candidate by round where round is not last.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testFindCandidateByRoundWhereRoundIsNotLast() throws ResourceNotFoundException { // feedback.getRound()
																									// == roundNumber &&
																									// canId ==
																									// feedback.getCandidateId()

		Candidate candidate = getCandidate();
		FeedbackOFCandidate candidateFeedback = getFeedback();
		List<FeedbackHistory> feedbackHistory = getFeedbackHistoryList();
		candidate.setRound(ROUND);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(candidateFeedback);
		when(feedbackHistoryRepository.findAll()).thenReturn(feedbackHistory);

		assertNotNull(interviewerService.findCandidateByRound(CANDIDATE_ID, ROUND));

	}

	/**
	 * Test find candidate by round where round is not lastfetchfrom feedback
	 * history.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testFindCandidateByRoundWhereRoundIsNotLastfetchfromFeedbackHistory() throws ResourceNotFoundException { // feedback.getRound()
																															// ==
																															// roundNumber
																															// &&
																															// canId
																															// ==
																															// feedback.getCandidateId()

		Candidate candidate = getCandidate();
		FeedbackOFCandidate candidateFeedback = getFeedback();
		List<FeedbackHistory> feedbackHistoryList = getFeedbackHistoryList();
		FeedbackHistory feedbackHistory = getFeedbackHistory();
		candidate.setRound(ROUND);
		feedbackHistory.setRound(ROUND);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(candidateFeedback);
		when(feedbackHistoryRepository.findAll()).thenReturn(feedbackHistoryList);

		assertNotNull(interviewerService.findCandidateByRound(CANDIDATE_ID, ROUND));

	}

	/**
	 * Test find candidate by round where round is not last ELS esecond.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testFindCandidateByRoundWhereRoundIsNotLastELSEsecond() throws ResourceNotFoundException { // feedback.getRound()
																											// ==
																											// roundNumber
																											// && canId
																											// ==
																											// feedback.getCandidateId()

		Candidate candidate = getCandidate();
		FeedbackOFCandidate candidateFeedback = getFeedback();
		List<FeedbackHistory> feedbackHistoryList = getFeedbackHistoryList();
		FeedbackHistory feedbackHistory = getFeedbackHistory();
		candidate.setRound(ROUND);
		feedbackHistory.setRound(ROUND);
		feedbackHistory.setCandidateId(CANDIDATE_ID);

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(candidate);
		when(feedbackRepository.findByCandidate(any())).thenReturn(candidateFeedback);
		when(feedbackHistoryRepository.findAll()).thenReturn(feedbackHistoryList);

		assertNotNull(interviewerService.findCandidateByRound(CANDIDATE_ID, ROUND));

	}

	/**
	 * Testinterviewof today.
	 */
	@Test
	public void testinterviewofToday() {
		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();

		Interview interview = getInterview();
		interview.setInterviewDate(TODAY);
		candidate.setInterview(interview);

		candidateList.add(candidate);
		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		candidateList.add(candidate);

		assertNotNull(interviewerService.interviewofToday(USERID));

	}

	/**
	 * Testinterviewof today when return null.
	 */
	@Test
	public void testinterviewofTodayWhenReturnNull() {

		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		interview.setInterviewDate(YESTERDAY);
		candidate.setInterview(interview);

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		assertNotNull(interviewerService.interviewofToday(USERID));

	}

	/**
	 * Testinterviewof tomorrow.
	 */
	@Test
	public void testinterviewofTomorrow() {

		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();

		Interview interview = getInterview();
		interview.setInterviewDate(TOMORROW);
		candidate.setInterview(interview);

		candidateList.add(candidate);
		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		candidateList.add(candidate);

		assertNotNull(interviewerService.interviewofTomorrow(USERID));

	}

	/**
	 * Testinterviewof tomorrow when return null.
	 */
	@Test
	public void testinterviewofTomorrowWhenReturnNull() {

		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		interview.setInterviewDate(YESTERDAY);
		candidate.setInterview(interview);

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		assertNotNull(interviewerService.interviewofTomorrow(USERID));

	}

	/**
	 * Testinterviewof yesterday.
	 */
	@Test
	public void testinterviewofYesterday() {

		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();

		Interview interview = getInterview();
		interview.setInterviewDate(YESTERDAY);
		candidate.setInterview(interview);

		candidateList.add(candidate);
		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		candidateList.add(candidate);

		assertNotNull(interviewerService.interviewofYesterday(USERID));

	}

	/**
	 * Testinterviewof yesterday when return null.
	 */
	@Test
	public void testinterviewofYesterdayWhenReturnNull() {

		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		interview.setInterviewDate(TOMORROW);
		candidate.setInterview(interview);

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		assertNotNull(interviewerService.interviewofYesterday(USERID));

	}

	/**
	 * Testinterviewof nextweek.
	 */
	@Test
	public void testinterviewofNextweek() {

		List<Candidate> candidateList = new ArrayList<>();

		Interview interview = getInterview();
		interview.setInterviewDate(NEXTWEEK);

		Candidate candidate = getCandidate();
		candidate.setInterview(interview);

		candidateList.add(candidate);
		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		assertNotNull(interviewerService.interviewofNextweek(USERID));

	}

	/**
	 * Testinterviewof nextweek when return null.
	 */
	@Test
	public void testinterviewofNextweekWhenReturnNull() {

		List<Candidate> candidateList = new ArrayList<>();

		Interview interview = getInterview();
		interview.setInterviewDate(YESTERDAY);

		Candidate candidate = getCandidate();
		candidate.setInterview(interview);

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		assertNotNull(interviewerService.interviewofLastweek(USERID));

	}

	/**
	 * Testinterviewof lastweek.
	 */
	@Test
	public void testinterviewofLastweek() {

		List<Candidate> candidateList = new ArrayList<>();

		Interview interview = getInterview();
		interview.setInterviewDate(LASTWEEK);

		Candidate candidate = getCandidate();

		candidate.setInterview(interview);

		candidateList.add(candidate);

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		assertNotNull(interviewerService.interviewofLastweek(USERID));

	}

	/**
	 * Testinterviewof lastweek when return null.
	 */
	@Test
	public void testinterviewofLastweekWhenReturnNull() {

		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();
		Interview interview = getInterview();
		interview.setInterviewDate(TOMORROW);
		candidate.setInterview(interview);

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);

		assertNotNull(interviewerService.interviewofLastweek(USERID));

	}

	/**
	 * Test get list of accepted candidates.
	 */
	@Test
	public void testGetListOfAcceptedCandidates() {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<FeedbackOFCandidate>();
		FeedbackOFCandidate feedback = getFeedback();

		List<FeedbackHistory> feedbackHistoryList = new ArrayList<FeedbackHistory>();
		FeedbackHistory feedbackHistory = getFeedbackHistory();

		UserDetails interviewer = getUserInterviewer();
		interviewer.setUser(getInterviewer());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		feedback.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedback.setInterview(interview);
		feedback.setInterviewResult(ACCEPT_CANDIDATE);

		feedbackHistory.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedbackHistory.setInterviewResult(ACCEPT_CANDIDATE);
		feedbackHistory.setUserId(USERID);

		feedbackList.add(feedback);

		feedbackHistoryList.add(feedbackHistory);

		List<FeedbackResponseDTO> feedbackResponseList = new ArrayList<FeedbackResponseDTO>();
		feedbackResponseList.add(getFeedbackResponseDTO());

		when(feedbackRepository.getAcceptedCandidates(anyLong())).thenReturn(feedbackList);
		when(feedbackHistoryRepository.getAcceptedCandidates(anyLong())).thenReturn(feedbackHistoryList);
		assertNotNull(interviewerService.getListOfAcceptedCandidates(USERID));
	}

	/**
	 * Test get list of accepted candidates return null.
	 */
	@Test
	public void testGetListOfAcceptedCandidatesReturnNull() {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<FeedbackOFCandidate>();
		FeedbackOFCandidate feedback = getFeedback();

		UserDetails interviewer = getUserInterviewer();
		interviewer.setUser(getInterviewer());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		feedback.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedback.setInterview(interview);
		feedback.setInterviewResult(ACCEPT_CANDIDATE);

//		List<FeedbackResponseDTO> feedbackResponseList = new ArrayList<FeedbackResponseDTO>();

		when(feedbackRepository.getAcceptedCandidates(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.getListOfAcceptedCandidates(USERID));
	}

	/**
	 * Test get list of rejected candidates.
	 */
	@Test
	public void testGetListOfRejectedCandidates() {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();

		UserDetails interviewer = getUserInterviewer();
		interviewer.setUser(getInterviewer());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		candidate.setInterview(interview);
		candidate.setInterviewResult(REJECT_CANDIDATE);

		candidateList.add(candidate);

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.getRejectedCandidates(anyLong())).thenReturn(candidateList);
		assertNotNull(interviewerService.getListOfRejectedCandidates(USERID));
	}

	/**
	 * Test get list of rejected candidates return null.
	 */
	@Test
	public void testGetListOfRejectedCandidatesReturnNull() {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();

		UserDetails interviewer = getUserInterviewer();
		interviewer.setUser(getInterviewer());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		candidate.setInterview(interview);
		candidate.setInterviewResult(REJECT_CANDIDATE);

//		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();

		when(candidateRepository.getRejectedCandidates(anyLong())).thenReturn(candidateList);
		assertNotNull(interviewerService.getListOfRejectedCandidates(USERID));
	}

	/**
	 * Test get list of hired candidates.
	 */
	@Test
	public void testGetListOfHiredCandidates() {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();

		UserDetails interviewer = getUserInterviewer();
		interviewer.setUser(getInterviewer());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		candidate.setInterview(interview);
		candidate.setInterviewResult(HIRE_CANDIDATE);

		candidateList.add(candidate);

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.getHiredCandidates(anyLong())).thenReturn(candidateList);
		assertNotNull(interviewerService.getListOfHiredCandidates(USERID));
	}

	/**
	 * Test get list of hired candidates return null.
	 */
	@Test
	public void testGetListOfHiredCandidatesReturnNull() {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();

		UserDetails interviewer = getUserInterviewer();
		interviewer.setUser(getInterviewer());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		candidate.setInterview(interview);
		candidate.setInterviewResult(HIRE_CANDIDATE);

//		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();

		when(candidateRepository.getHiredCandidates(anyLong())).thenReturn(candidateList);
		assertNotNull(interviewerService.getListOfHiredCandidates(USERID));
	}

	/**
	 * View all feedback.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void viewAllFeedback() throws ResourceNotFoundException {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<>();

		FeedbackOFCandidate feedback = getFeedback();

		Interview interview = getInterview();

		feedback.setInterview(interview);

		feedbackList.add(feedback);

		when(feedbackRepository.givenFeedbackList(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.viewAllFeedback(USERID));
	}

	/**
	 * View all feedback return null.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void viewAllFeedbackReturnNull() throws ResourceNotFoundException {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<>();

		FeedbackOFCandidate feedback = getFeedback();

		Interview interview = getInterview();

		feedback.setInterview(interview);

		when(feedbackRepository.givenFeedbackList(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.viewAllFeedback(USERID));
	}

	/**
	 * Feed back of today.
	 */
	@Test
	public void feedBackOfToday() {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<>();

		FeedbackOFCandidate feedback = getFeedback();

		Interview interview = getInterview();
		interview.setInterviewDate(TODAY);

		feedback.setInterview(interview);
		feedback.setDateOfInterview(TODAY);

		feedbackList.add(feedback);

		when(feedbackRepository.givenFeedbackList(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.feedbackOfToday(USERID));

	}

	/**
	 * Feed back of today when list is null.
	 */
	@Test
	public void feedBackOfTodayWhenListIsNull() {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<>();

		getFeedback();

		when(feedbackRepository.givenFeedbackList(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.feedbackOfToday(USERID));

	}

	/**
	 * Feed back of yesterday.
	 */
	@Test
	public void feedBackOfYesterday() {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<>();

		FeedbackOFCandidate feedback = getFeedback();

		Interview interview = getInterview();
		interview.setInterviewDate(YESTERDAY);

		feedback.setInterview(interview);
		feedback.setDateOfInterview(YESTERDAY);

		feedbackList.add(feedback);

		when(feedbackRepository.givenFeedbackList(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.feedbackOfYesterday(USERID));

	}

	/**
	 * Feed back of yesterday when list is null.
	 */
	@Test
	public void feedBackOfYesterdayWhenListIsNull() {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<>();

		getFeedback();

		when(feedbackRepository.givenFeedbackList(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.feedbackOfYesterday(USERID));

	}

	/**
	 * Feedback of lastweek.
	 */
	@Test
	public void feedbackOfLastweek() {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<>();

		FeedbackOFCandidate feedback = getFeedback();

		Interview interview = getInterview();
		interview.setInterviewDate(LASTWEEK);

		feedback.setInterview(interview);
		feedback.setDateOfInterview(LASTWEEK);

		feedbackList.add(feedback);

		when(feedbackRepository.givenFeedbackList(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.feedbackOfLastweek(USERID));
	}

	/**
	 * Feedback of lastweek when list is null.
	 */
	@Test
	public void feedbackOfLastweekWhenListIsNull() {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<>();

		getFeedback();

		when(feedbackRepository.givenFeedbackList(anyLong())).thenReturn(feedbackList);
		assertNotNull(interviewerService.feedbackOfLastweek(USERID));

	}

	/**
	 * View feedback by id not exist test.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void viewFeedbackByIdNotExistTest() throws ResourceNotFoundException {
		when(feedbackRepository.findByCandidate(any())).thenReturn(null);
		interviewerService.viewFeedbackById(getCandidate());

	}

	/**
	 * Scheduled list.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void scheduledList() throws ResourceNotFoundException {
		List<Candidate> candidateList = new ArrayList<>();

		UserDetails userDetails = getUserInterviewer();
		userDetails.setUser(getInterviewer());

		Interview interview = getInterviewAlreadyResponsed();
		interview.setInterviewer(userDetails);

		Candidate candidate = getCandidate();

		candidate.setInterview(interview);

		candidateList.add(candidate);
		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);
		assertNotNull(interviewerService.scheduledList(USERID));
	}

	/**
	 * View feedback by id test.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void viewFeedbackByIdTest() throws ResourceNotFoundException {
		Candidate candidate = getCandidate();
		when(feedbackRepository.findByCandidate(any())).thenReturn(getFeedback());
		assertNotNull(interviewerService.viewFeedbackById(candidate));

	}

	/**
	 * List of interview.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void listOfInterview() throws ResourceNotFoundException {

		List<Interview> interviewList = new ArrayList<>();

		Interview interview = getInterview();
		interview.setInterviewer(getUserInterviewer());

		interviewList.add(interview);

		when(interviewRepository.getParticularInterviewByInterviewerId(anyLong())).thenReturn(interviewList);
		assertNotNull(interviewerService.listOfInterview(USERID));
	}

	/**
	 * List of interview return null.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void listOfInterviewReturnNull() throws ResourceNotFoundException {

		List<Interview> interviewList = new ArrayList<>();

		Interview interview = getInterview();
		interview.setInterviewer(getUserInterviewer());

		when(interviewRepository.getParticularInterviewByInterviewerId(anyLong())).thenReturn(interviewList);
		assertNotNull(interviewerService.listOfInterview(USERID));
	}

	/**
	 * View feedback by id test exception.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void viewFeedbackByIdTestException() throws ResourceNotFoundException {
		Candidate candidate = getCandidate();
		when(feedbackRepository.findByCandidate(any())).thenReturn(null);
		assertNotNull(interviewerService.viewFeedbackById(candidate));

	}

	/**
	 * Gets the feedback DTO.
	 *
	 * @return the feedback DTO
	 */
	public FeedbackDTO getFeedbackDTO() {

		FeedbackDTO feedbackDTO = new FeedbackDTO();

		feedbackDTO.setFeedbackId(FEEDBACK_ID);
		feedbackDTO.setCandidateName(CANDIDATE_NAME);
		feedbackDTO.setInterviewerName(INTERVIEWER_NAME);
		feedbackDTO.setRound(ROUND);
		feedbackDTO.setExperience(EXPERIENCE);
		feedbackDTO.setJobRole(JOB_ROLE);
		feedbackDTO.setDateOfInterview(DATE_OF_INTERVIEW);
		feedbackDTO.setTechnicalSkill(TECHNICAL_SKILL);
		feedbackDTO.setOrganizationalSkill(ORGANIZATIONAL_SKILL);
		feedbackDTO.setEducationSkill(EDUCATIONAL_SKILL);
		feedbackDTO.setCommunicationSkill(COMMUNICATION_SKILL);
		feedbackDTO.setOverallRating(OVERALL_RATING);
		feedbackDTO.setCandidate(CANDIDATE_ID);
		feedbackDTO.setInterview(INTERVIEW_ID);
		feedbackDTO.setInterviewer(INTERVIEWER_ID);
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_HIRE);
		feedbackDTO.setNextRound(NEXT_ROUND);
		feedbackDTO.setHrLeaderAbility(HR_ABILITY_SKILL);
		return feedbackDTO;
	}

	/**
	 * Gets the feedback response DTO.
	 *
	 * @return the feedback response DTO
	 */
	public FeedbackResponseDTO getFeedbackResponseDTO() {

		FeedbackResponseDTO feedbackDTO = new FeedbackResponseDTO();

		feedbackDTO.setFeedbackId(FEEDBACK_ID);
		feedbackDTO.setCandidateName(CANDIDATE_NAME);
		feedbackDTO.setInterviewerName(INTERVIEWER_NAME);
		feedbackDTO.setRound(ROUND);
		feedbackDTO.setExperience(EXPERIENCE);
		feedbackDTO.setJobRole(JOB_ROLE);
		feedbackDTO.setDateOfInterview(DATE_OF_INTERVIEW);
		feedbackDTO.setTechnicalSkill(TECHNICAL_SKILL);
		feedbackDTO.setOrganizationalSkill(ORGANIZATIONAL_SKILL);
		feedbackDTO.setEducationSkill(EDUCATIONAL_SKILL);
		feedbackDTO.setCommunicationSkill(COMMUNICATION_SKILL);
		feedbackDTO.setOverallRating(OVERALL_RATING);
		feedbackDTO.setCandidate(CANDIDATE_ID);
		feedbackDTO.setInterview(INTERVIEW_ID);
		feedbackDTO.setInterviewer(INTERVIEWER_ID);
		feedbackDTO.setInterviewResult(INTERVIEW_RESULT_HIRE);
		feedbackDTO.setNextRound(NEXT_ROUND);
		feedbackDTO.setHrLeaderAbility(HR_ABILITY_SKILL);
		return feedbackDTO;
	}

	/**
	 * Gets the feedback history.
	 *
	 * @return the feedback history
	 */
	public FeedbackHistory getFeedbackHistory() {

		FeedbackHistory feedbackHistory = new FeedbackHistory();

		feedbackHistory.setCandidateId(CANDIDATE_ID);
		feedbackHistory.setFeedbackId(FEEDBACK_ID);
		feedbackHistory.setFeedbackHistoryId(FEEDBACK_HISTORY_ID);
		feedbackHistory.setCandidateName(CANDIDATE_NAME);
		feedbackHistory.setInterviewerName(INTERVIEWER_NAME);
		feedbackHistory.setRound(ROUND);
		feedbackHistory.setExperience(EXPERIENCE);
		feedbackHistory.setJobRole(JOB_ROLE);
		feedbackHistory.setDateOfInterview(DATE_OF_INTERVIEW);
		feedbackHistory.setTechnicalSkill(TECHNICAL_SKILL);
		feedbackHistory.setOrganizationalSkill(ORGANIZATIONAL_SKILL);
		feedbackHistory.setEducationSkill(EDUCATIONAL_SKILL);
		feedbackHistory.setCommunicationSkill(COMMUNICATION_SKILL);
		feedbackHistory.setOverallRating(OVERALL_RATING);
		feedbackHistory.setCandidateDesignation(CANDIDATE_DESIGNATION);
		feedbackHistory.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		feedbackHistory.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedbackHistory.setNextRound(NEXT_ROUND);
		feedbackHistory.setHrleaderAbility(HR_ABILITY_SKILL);
		return feedbackHistory;
	}

	/**
	 * Gets the feedback history list.
	 *
	 * @return the feedback history list
	 */
	public List<FeedbackHistory> getFeedbackHistoryList() {
		List<FeedbackHistory> feedbackHistoryList = new ArrayList<>();
		FeedbackHistory feedbackHistory = new FeedbackHistory();
		FeedbackHistory feedbackHistoryNextRound = new FeedbackHistory();

		feedbackHistory.setCandidateId(CANDIDATE_ID);
		feedbackHistory.setFeedbackId(FEEDBACK_ID);
		feedbackHistory.setFeedbackHistoryId(FEEDBACK_HISTORY_ID);
		feedbackHistory.setCandidateName(CANDIDATE_NAME);
		feedbackHistory.setInterviewerName(INTERVIEWER_NAME);
		feedbackHistory.setRound(ROUND);
		feedbackHistory.setExperience(EXPERIENCE);
		feedbackHistory.setJobRole(JOB_ROLE);
		feedbackHistory.setDateOfInterview(DATE_OF_INTERVIEW);
		feedbackHistory.setTechnicalSkill(TECHNICAL_SKILL);
		feedbackHistory.setOrganizationalSkill(ORGANIZATIONAL_SKILL);
		feedbackHistory.setEducationSkill(EDUCATIONAL_SKILL);
		feedbackHistory.setCommunicationSkill(COMMUNICATION_SKILL);
		feedbackHistory.setOverallRating(OVERALL_RATING);
		feedbackHistory.setCandidateDesignation(CANDIDATE_DESIGNATION);
		feedbackHistory.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		feedbackHistory.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedbackHistory.setNextRound(NEXT_ROUND);
		feedbackHistory.setHrleaderAbility(HR_ABILITY_SKILL);

		feedbackHistoryNextRound.setCandidateId(CANDIDATE_ID);
		feedbackHistoryNextRound.setFeedbackId(FEEDBACKSECOND_ID);
		feedbackHistoryNextRound.setFeedbackHistoryId(FEEDBACK_HISTORY_ID);
		feedbackHistoryNextRound.setCandidateName(CANDIDATE_NAME);
		feedbackHistoryNextRound.setInterviewerName(INTERVIEWER_NAME);
		feedbackHistoryNextRound.setRound(ROUND);
		feedbackHistoryNextRound.setExperience(EXPERIENCE);
		feedbackHistoryNextRound.setJobRole(JOB_ROLE);
		feedbackHistoryNextRound.setDateOfInterview(DATE_OF_INTERVIEW);
		feedbackHistoryNextRound.setTechnicalSkill(TECHNICAL_SKILL);
		feedbackHistoryNextRound.setOrganizationalSkill(ORGANIZATIONAL_SKILL);
		feedbackHistoryNextRound.setEducationSkill(EDUCATIONAL_SKILL);
		feedbackHistoryNextRound.setCommunicationSkill(COMMUNICATION_SKILL);
		feedbackHistoryNextRound.setOverallRating(OVERALL_RATING);
		feedbackHistoryNextRound.setCandidateDesignation(CANDIDATE_DESIGNATION);
		feedbackHistoryNextRound.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		feedbackHistoryNextRound.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedbackHistoryNextRound.setNextRound(NEXT_ROUND);
		feedbackHistoryNextRound.setHrleaderAbility(HR_ABILITY_SKILL);

		feedbackHistoryList.add(feedbackHistory);
		feedbackHistoryList.add(feedbackHistoryNextRound);
		return feedbackHistoryList;

	}

	/**
	 * Gets the feedback.
	 *
	 * @return the feedback
	 */
	public FeedbackOFCandidate getFeedback() {

		FeedbackOFCandidate feedback = new FeedbackOFCandidate();

		feedback.setFeedbackId(FEEDBACK_ID);
		feedback.setCandidateName(CANDIDATE_NAME);
		feedback.setInterviewerName(INTERVIEWER_NAME);
		feedback.setRound(ROUND);
		feedback.setExperience(EXPERIENCE);
		feedback.setJobRole(JOB_ROLE);
		feedback.setDateOfInterview(DATE_OF_INTERVIEW);
		feedback.setTechnicalSkill(TECHNICAL_SKILL);
		feedback.setOrganizationalSkill(ORGANIZATIONAL_SKILL);
		feedback.setEducationSkill(EDUCATIONAL_SKILL);
		feedback.setCommunicationSkill(COMMUNICATION_SKILL);
		feedback.setOverallRating(OVERALL_RATING);
		feedback.setCandidate(getCandidate());
		feedback.setInterview(getInterview());
		feedback.setUserDetails(getUserInterviewer());
		feedback.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedback.setNextRound(NEXT_ROUND);
		feedback.setHrleaderAbility(HR_ABILITY_SKILL);
		return feedback;
	}

	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	JobRequirementCounter getCounter() {
		JobRequirementCounter jobCounter = new JobRequirementCounter();
		jobCounter.setRequirementCounter(10);
		return jobCounter;
	}

	/**
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	Candidate getCandidate() {

		Candidate candidate = new Candidate();
		candidate.setCandidateId(CANDIDATE_ID);
		candidate.setRound(ROUND);
		candidate.setInterviewResult(INTERVIEW_RESULT_REJECT);
		candidate.setInterview(getInterview());
		candidate.setCandidateSkill(CANDIDATE_SKILL);
		candidate.setCandidateGroup(CANDIDATE_GROUP);
		candidate.setCandidateDesignation(CANDIDATE_DESIGNATION);
		candidate.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		return candidate;

	}

	/**
	 * Gets the candidate DTO.
	 *
	 * @return the candidate DTO
	 */
	CandidateDTO getCandidateDTO() {

		CandidateDTO candidateDTO = new CandidateDTO();
		candidateDTO.setCandidateId(CANDIDATE_ID);
		candidateDTO.setRound(ROUND);
		candidateDTO.setInterviewResult(INTERVIEW_RESULT_REJECT);
		candidateDTO.setInterview(getInterview());
		return candidateDTO;

	}

	/**
	 * Gets the interview.
	 *
	 * @return the interview
	 */
	Interview getInterview() {
		Interview interview = new Interview();
		interview.setInterviewId(INTERVIEW_ID);
		interview.setFlag(0);
		return interview;
	}

	/**
	 * Gets the interview response DTO.
	 *
	 * @return the interview response DTO
	 */
	InterviewResponseDTO getInterviewResponseDTO() {
		InterviewResponseDTO interview = new InterviewResponseDTO();
		interview.setInterviewId(INTERVIEW_ID);
		interview.setFlag(1);
		interview.setInterviewer(getUserInterviewer());
		interview.setInterviewStatus(INTERVIEW_RESULT_ACCEPT);
		return interview;
	}

	/**
	 * Gets the interview already responded.
	 *
	 * @return the interview already responded
	 */
	Interview getInterviewAlreadyResponsed() {
		Interview interview = new Interview();
		interview.setInterviewId(INTERVIEW_ID);
		interview.setFlag(1);
		return interview;
	}

	/**
	 * Gets the interview already responded.
	 *
	 * @return the interview already responded
	 */
	Interview getInterviewAlreadyResponded() {
		Interview interview = new Interview();
		interview.setInterviewId(INTERVIEW_ID);
		interview.setInterviewStatus(INTERVIEW_STATUS_SCHEDULED);
		interview.setFlag(1);
		return interview;
	}

	/**
	 * Gets the user interviewer.
	 *
	 * @return the user interviewer
	 */
	/**
	 * Gets the user interviewer.
	 *
	 * @return the user interviewer
	 */
	UserDetails getUserInterviewer() {
		UserDetails interviewer = new UserDetails();
		interviewer.setUserDetailsId(INTERVIEWER_ID);
		return interviewer;
	}

	/**
	 * Gets the show candidate DTO.
	 *
	 * @return the show candidate DTO
	 */
	ShowCandidateDTO getShowCandidateDTO() {
		ShowCandidateDTO candidate = new ShowCandidateDTO();

		candidate.setCandidateId(CANDIDATE_ID);

		return candidate;
	}

	/**
	 * Gets the interviewer user.
	 *
	 * @return the interviewer user
	 */
	public User getInterviewer() {
		User user = new User();
		user.setUserId(USERID);
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_INTERVIEWER);
		return user;
	}

}