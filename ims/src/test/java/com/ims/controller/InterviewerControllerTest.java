package com.ims.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ims.dao.CandidateRepository;
import com.ims.dao.FeedbackOfCandidateRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dto.CandidateDTO;
import com.ims.dto.FeedbackDTO;
import com.ims.dto.ShowCandidateDTO;
import com.ims.dto.ShowInterviwerDTO;
import com.ims.dto.response.FeedbackResponseDTO;
import com.ims.dto.response.InterviewResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.FeedbackOFCandidate;
import com.ims.entity.Interview;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.service.InterviewerServiceInterface;


/**
 * The Class InterviewerControllerTest.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InterviewerControllerTest {

	/** The interviewer controller. */
	@InjectMocks
	private InterviewerController interviewerController;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/** The interviewer repository. */

	/** The interview repository. */
	@Mock
	private InterviewRepository interviewRepository;

	/** The candidate repository. */
	@Mock
	private CandidateRepository candidateRepository;
	
	@Mock
	private FeedbackOfCandidateRepository feedbackRepository;


	/** The interviewer service. */
	@Mock
	private InterviewerServiceInterface interviewerService;

	/** The Constant interviewerId. */
	private final static long INTERVIEWER_ID = Long.parseLong("1");

	/** The Constant emailId. */
	private final static String USERNAME = "vahak15@gmail.com";

	/** The Constant password. */
	private final static String PASSWORD = "AB@123";

	/** The Constant INTERVIEW_RESULT_ACCEPT. */
	private final static String INTERVIEW_RESULT_ACCEPT = "Accept";
	/** The Constant user_interviewer. */
	private final static String USER_INTERVIEWER = "interviewer";

	/** The Constant interviewer_department. */
	private final static String INTERVIEWER_DEPARTMENT = "PES";

	/** The Constant interviewer_designation. */
	private final static String INTERVIWER_DESIGNATION = "Fresher";
	/** The Constant availableDate. */
	private final static LocalDate AVAILABLE_DATE = LocalDate.of(2020, 02, 24);

	/** The Constant availableTimeFrom. */
	private final static LocalTime AVAILABLE_TIME_FROM = LocalTime.of(12, 00);

	/** The Constant availableTimeTo. */
	private final static LocalTime AVAILABLE_TIME_TO = LocalTime.of(13, 00);
	/** The Constant flag. */
	private final static int FLAG = 1;

	/** The Constant mobilenumber. */
	private final static String MOBILE_NUMBER = "9876543210";
	/** The Constant date. */
	private final static LocalDate DOB = LocalDate.of(1996, 5, 15);

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

	/** The Constant ROUND. */
	private final static int ROUND = 1;

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

	/** The Constant INTERVIEW_RESULT. */
	private final static String INTERVIEW_RESULT = "Accept";

	/** The Constant NEXT_ROUND. */
	private final static String NEXT_ROUND = "Yes";

	/** The Constant INTERVIEW_RESULT_REJECT. */
	private static final String INTERVIEW_RESULT_REJECT = "Reject";

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
		mockMvc = MockMvcBuilders.standaloneSetup(interviewerController)
				.addPlaceholderValue("settings.cors_origin", "https://localhost:3000.").build();
	}

	/**
	 * Gets the feedback DTO.
	 *
	 * @return the feedback DTO
	 */
	public FeedbackDTO getFeedbackDTO() {

		FeedbackDTO feedbackdto = new FeedbackDTO();
		feedbackdto.setFeedbackId(FEEDBACK_ID);
		feedbackdto.setCandidateName(CANDIDATE_NAME);
		feedbackdto.setInterviewerName(INTERVIEWER_NAME);
		feedbackdto.setRound(ROUND);
		feedbackdto.setExperience(EXPERIENCE);
		feedbackdto.setJobRole(JOB_ROLE);
		feedbackdto.setDateOfInterview(DATE_OF_INTERVIEW);
		feedbackdto.setTechnicalSkill(TECHNICAL_SKILL);
		feedbackdto.setOrganizationalSkill(ORGANIZATIONAL_SKILL);
		feedbackdto.setEducationSkill(EDUCATIONAL_SKILL);
		feedbackdto.setCommunicationSkill(COMMUNICATION_SKILL);
		feedbackdto.setOverallRating(OVERALL_RATING);
		feedbackdto.setCandidate(CANDIDATE_ID);
		feedbackdto.setInterview(INTERVIEW_ID);
		feedbackdto.setInterviewer(INTERVIEWER_ID);
		feedbackdto.setInterviewResult(INTERVIEW_RESULT);
		feedbackdto.setNextRound(NEXT_ROUND);
		feedbackdto.setHrLeaderAbility(HR_ABILITY_SKILL);
		return feedbackdto;
	}

	/**
	 * Gets the interviewer user.
	 *
	 * @return the interviewer user
	 */
	public User getInterviewerUser() {
		User user = new User();
		user.setUserId(INTERVIEWER_ID);
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_INTERVIEWER);
		return user;
	}

	/**
	 * Gets the user details interviewer.
	 *
	 * @return the user details interviewer
	 */
	public UserDetails getUserDetailsInterviewer() {

		UserDetails interviewer = new UserDetails();
		interviewer.setUserDetailsId(INTERVIEWER_ID);
		interviewer.setDepartment(INTERVIEWER_DEPARTMENT);
		interviewer.setDesignation(INTERVIWER_DESIGNATION);
		interviewer.setDateOfBirth(DOB);
		interviewer.setExperience(EXPERIENCE);
		interviewer.setContactNumber(MOBILE_NUMBER);
		interviewer.setActiveFlag(FLAG);
		interviewer.setAvailableDate(AVAILABLE_DATE);
		interviewer.setAvailableTimeFrom(AVAILABLE_TIME_FROM);
		interviewer.setAvailableTimeTo(AVAILABLE_TIME_TO);
		interviewer.setUser(getInterviewerUser());
		return interviewer;

	}

	/**
	 * Gets the feedback object.
	 *
	 * @return the feedback object
	 */
	public FeedbackOFCandidate getFeedbackObject() {

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
		feedback.setCandidate(getCandidateObj());
		feedback.setInterview(getInterview());

		feedback.setUserDetails(getUserDetailsInterviewer());
		feedback.setInterviewResult(INTERVIEW_RESULT);
		feedback.setNextRound(NEXT_ROUND);
		feedback.setHrleaderAbility(HR_ABILITY_SKILL);
		return feedback;
	}

	/**
	 * Test show interviewer. Get List of Interviewers
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testShowInterviewer() throws Exception {

		List<ShowInterviwerDTO> interviewerList = new ArrayList<ShowInterviwerDTO>();
		interviewerList.add(getIntervieerDTO());
		when(interviewerService.getAllInterviewer()).thenReturn(interviewerList);
		mockMvc.perform(MockMvcRequestBuilders.get("/showinterviewer"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Test schedule interview list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testScheduleInterviewList() throws Exception {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidateObj();
		candidate.setInterview(getInterview());
		candidateList.add(candidate);

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);
		when(interviewerService.scheduledList(anyLong())).thenReturn(showCandidateList);

		mockMvc.perform(MockMvcRequestBuilders.get("/scheduledInterviewListforInterview/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test schedule interview list return null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testScheduleInterviewListReturnNull() throws Exception {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidateObj();
		candidate.setInterview(getInterview());

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();

		when(candidateRepository.scheduledList(anyLong())).thenReturn(candidateList);
		when(interviewerService.scheduledList(anyLong())).thenReturn(showCandidateList);

		mockMvc.perform(MockMvcRequestBuilders.get("/scheduledInterviewListforInterview/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test pending interview list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPendingInterviewList() throws Exception {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidateObj();
		candidate.setInterview(getInterview());
		candidateList.add(candidate);

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.candidatePendingInterviewApproval(anyLong())).thenReturn(candidateList);
		when(interviewerService.pendingInterviewApprovalList(anyLong())).thenReturn(showCandidateList);

		mockMvc.perform(MockMvcRequestBuilders.get("/getpenddingapproval/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test pending interview list return null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPendingInterviewListReturnNull() throws Exception {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidateObj();
		candidate.setInterview(getInterview());

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.candidatePendingInterviewApproval(anyLong())).thenReturn(candidateList);
		when(interviewerService.pendingInterviewApprovalList(anyLong())).thenReturn(showCandidateList);

		mockMvc.perform(MockMvcRequestBuilders.get("/getpenddingapproval/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test accept interview.
	 *
	 * This method test rest Api(/acceptinterview)
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testAcceptInterview() throws Exception {
		when(interviewerService.acceptInterview(anyLong())).thenReturn(getInterview());
		mockMvc.perform(MockMvcRequestBuilders.get("/acceptinterview/" + INTERVIEW_ID + ""))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Test reject interview.
	 *
	 * This method test rest Api(/rejectinterview)
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testRejectInterview() throws Exception {
		when(interviewerService.rejectInterview(anyLong(), anyLong())).thenReturn(getInterview());
		mockMvc.perform(MockMvcRequestBuilders.get("/rejectinterview/" + INTERVIEW_ID + "/" + CANDIDATE_ID + ""))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Gets the intervieer DTO.
	 *
	 * @return the intervieer DTO
	 */
	/**
	 * Gets the intervieer DTO.
	 *
	 * @return the intervieer DTO
	 */
	ShowInterviwerDTO getIntervieerDTO() {
		ShowInterviwerDTO interviwerDTO = new ShowInterviwerDTO();
		interviwerDTO.setUserDetailsId(INTERVIEWER_ID);
		return interviwerDTO;
	}

	/**
	 * Test human resource feedback.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testHumanResourceFeedback() throws Exception {

		FeedbackOFCandidate feedback = getFeedbackObject();
		FeedbackDTO feedbackDTO = getFeedbackDTO();

		when(interviewerService.humanResourceFeedback(any())).thenReturn(feedback);

		String inputInJson = this.mapToJson(feedbackDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/humanresourcefeedback").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	/**
	 * Gets the list of accepted candidates.
	 *
	 * @return the list of accepted candidates
	 * @throws Exception the exception
	 */
	@Test
	public void getListOfAcceptedCandidates() throws Exception {
		List<FeedbackOFCandidate> feedbackList = new ArrayList<FeedbackOFCandidate>();
		FeedbackOFCandidate feedback =getFeedbackObject();

		UserDetails interviewer = getUserDetailsInterviewer();
		interviewer.setUser(getInterviewerUser());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		feedback.setInterviewResult(INTERVIEW_RESULT_ACCEPT);
		feedback.setInterview(interview);
		feedback.setInterviewResult(ACCEPT_CANDIDATE);

		feedbackList.add(feedback);

		List<FeedbackResponseDTO> feedbackResponseList = new ArrayList<FeedbackResponseDTO>();
		feedbackResponseList.add(getFeedback());

		when(feedbackRepository.getAcceptedCandidates(anyLong())).thenReturn(feedbackList);
		when(interviewerService.getListOfAcceptedCandidates(anyLong())).thenReturn(feedbackResponseList);

		mockMvc.perform(MockMvcRequestBuilders.get("/listofacceptedcandidate/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Gets the list of rejected candidates.
	 *
	 * @return the list of rejected candidates
	 * @throws Exception the exception
	 */
	@Test
	public void getListOfRejectedCandidates() throws Exception {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidateObj();

		UserDetails interviewer = getUserDetailsInterviewer();
		interviewer.setUser(getInterviewerUser());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		candidate.setInterview(interview);
		candidate.setInterviewResult(REJECT_CANDIDATE);

		candidateList.add(candidate);

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.getRejectedCandidates(anyLong())).thenReturn(candidateList);
		when(interviewerService.getListOfRejectedCandidates(anyLong())).thenReturn(showCandidateList);

		mockMvc.perform(MockMvcRequestBuilders.get("/listofrejectedcandidate/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Gets the list of hired candidates.
	 *
	 * @return the list of hired candidates
	 * @throws Exception the exception
	 */
	@Test
	public void getListOfHiredCandidates() throws Exception {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		Candidate candidate = getCandidateObj();

		UserDetails interviewer = getUserDetailsInterviewer();
		interviewer.setUser(getInterviewerUser());

		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		candidate.setInterview(interview);
		candidate.setInterviewResult(HIRE_CANDIDATE);

		candidateList.add(candidate);

		List<ShowCandidateDTO> showCandidateList = new ArrayList<ShowCandidateDTO>();
		showCandidateList.add(getShowCandidateDTO());

		when(candidateRepository.getHiredCandidates(anyLong())).thenReturn(candidateList);
		when(interviewerService.getListOfHiredCandidates(anyLong())).thenReturn(showCandidateList);

		mockMvc.perform(MockMvcRequestBuilders.get("/listofhiredcandidate/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test interviewer feedback.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testInterviewerFeedback() throws Exception {

		FeedbackDTO feedbackdto = getFeedbackDTO();

		when(interviewerService.interviewerFeedback(any())).thenReturn(getFeedbackObject());

		String inputInJson = this.mapToJson(feedbackdto);

		mockMvc.perform(MockMvcRequestBuilders.post("/interviewerfeedback").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

//	@Test
//	public void testViewFeedbackByIdCandidateNotThere() throws Exception {
//		//Candidate candidate = getCandidate();
//		//candidate.setCandidateId(-1);
//		String inputInJson = this.mapToJson(getCandidate());
//		mockMvc.perform(MockMvcRequestBuilders.get("/viewfeedbackbyid/1").accept(MediaType.APPLICATION_JSON).content(inputInJson)
//				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
	/**
	 * Test view feedback by id.
	 *
	 * @throws Exception the exception
	 */
//	}
	@Test
	public void testViewFeedbackById() throws Exception {
		Candidate candidate = getCandidateObj();
		candidate.setCandidateId(1);
		String inputInJson = this.mapToJson(candidate);
		mockMvc.perform(MockMvcRequestBuilders.get("/viewfeedbackbyid/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test get round candidate feedback.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetRoundCandidateFeedback() throws Exception {
		Candidate candidate = getCandidateObj();
		String inputInJson = this.mapToJson(candidate);
		mockMvc.perform(MockMvcRequestBuilders.get("/getRoundfeedback/1/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Testinterviewoftoday.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testinterviewoftoday() throws Exception {

		List<CandidateDTO> candidateList = new ArrayList<CandidateDTO>();
		candidateList.add(getCandidate());

		when(interviewerService.interviewofToday(anyLong())).thenReturn(candidateList);
		mockMvc.perform(MockMvcRequestBuilders.get("/todaysinterview/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testinterviewoftomorrow.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testinterviewoftomorrow() throws Exception {

		List<CandidateDTO> candidateList = new ArrayList<CandidateDTO>();
		candidateList.add(getCandidate());

		when(interviewerService.interviewofTomorrow(anyLong())).thenReturn(candidateList);
		mockMvc.perform(MockMvcRequestBuilders.get("/tomorrowsinterview/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testinterviewofyesterday.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testinterviewofyesterday() throws Exception {

		List<CandidateDTO> candidateList = new ArrayList<CandidateDTO>();
		candidateList.add(getCandidate());

		when(interviewerService.interviewofYesterday(anyLong())).thenReturn(candidateList);
		mockMvc.perform(MockMvcRequestBuilders.get("/yesterdaysinterview/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testinterviewof nextweek.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testinterviewofNextweek() throws Exception {

		List<CandidateDTO> candidateList = new ArrayList<CandidateDTO>();
		candidateList.add(getCandidate());

		when(interviewerService.interviewofNextweek(anyLong())).thenReturn(candidateList);
		mockMvc.perform(MockMvcRequestBuilders.get("/nextweeksinterview/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testinterviewof lastweek.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testinterviewofLastweek() throws Exception {

		List<CandidateDTO> candidateList = new ArrayList<CandidateDTO>();
		candidateList.add(getCandidate());

		when(interviewerService.interviewofLastweek(anyLong())).thenReturn(candidateList);
		mockMvc.perform(MockMvcRequestBuilders.get("/lastweeksinterview/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testview all feedback.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testviewAllFeedback() throws Exception {

		List<FeedbackResponseDTO> feedbackList = new ArrayList<>();
		feedbackList.add(getFeedback());

		when(interviewerService.viewAllFeedback(anyLong())).thenReturn(feedbackList);
		mockMvc.perform(MockMvcRequestBuilders.get("/viewallfeedback/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testfeedback of today.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testfeedbackOfToday() throws Exception {

		List<FeedbackResponseDTO> feedbackList = new ArrayList<>();
		feedbackList.add(getFeedback());

		when(interviewerService.feedbackOfToday(anyLong())).thenReturn(feedbackList);
		mockMvc.perform(MockMvcRequestBuilders.get("/todaysfeedback/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testfeedback of yesterday.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testfeedbackOfYesterday() throws Exception {

		List<FeedbackResponseDTO> feedbackList = new ArrayList<>();
		feedbackList.add(getFeedback());

		when(interviewerService.feedbackOfYesterday(anyLong())).thenReturn(feedbackList);
		mockMvc.perform(MockMvcRequestBuilders.get("/yesterdayfeedback/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testfeedback of lastweek.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testfeedbackOfLastweek() throws Exception {

		List<FeedbackResponseDTO> feedbackList = new ArrayList<>();
		feedbackList.add(getFeedback());

		when(interviewerService.feedbackOfLastweek(anyLong())).thenReturn(feedbackList);
		mockMvc.perform(MockMvcRequestBuilders.get("/lastweeksfeedback/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * List of interview.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void listOfInterview() throws Exception {

		List<InterviewResponseDTO> feedbackList = new ArrayList<>();
		feedbackList.add(getInterviewResponseDTO());

		when(interviewerService.listOfInterview(anyLong())).thenReturn(feedbackList);
		mockMvc.perform(MockMvcRequestBuilders.get("/getInterviews/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Display candidates scheduled 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void displayCandidatesScheduled1() throws Exception {

		List<ShowCandidateDTO> candidateList = new ArrayList<>();
		candidateList.add(getShowCandidateDTO());

		when(interviewerService.scheduledList(anyLong())).thenReturn(candidateList);
		mockMvc.perform(MockMvcRequestBuilders.get("/scheduledInterviewListforInterview/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Map to json.
	 *
	 * @param obj the obj
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * Gets the interview.
	 *
	 * @return the interview
	 */
	public Interview getInterview() {
		Interview interview = new Interview();
		interview.setInterviewId(INTERVIEW_ID);
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
		interview.setInterviewer(getUserDetailsInterviewer());
		interview.setInterviewStatus(INTERVIEW_RESULT_ACCEPT);
		return interview;
	}

	/**
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	CandidateDTO getCandidate() {

		CandidateDTO candidate = new CandidateDTO();
		candidate.setCandidateId(CANDIDATE_ID);
		candidate.setRound(ROUND);
		candidate.setInterviewResult(INTERVIEW_RESULT_REJECT);
		candidate.setInterview(getInterview());
		return candidate;

	}

	/**
	 * Gets the candidate obj.
	 *
	 * @return the candidate obj
	 */
	Candidate getCandidateObj() {

		Candidate candidate = new Candidate();
		candidate.setCandidateId(CANDIDATE_ID);
		candidate.setRound(ROUND);
		candidate.setInterviewResult(INTERVIEW_RESULT_REJECT);
		candidate.setInterview(getInterview());
		return candidate;

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
	 * Gets the feedback.
	 *
	 * @return the feedback
	 */
	FeedbackResponseDTO getFeedback() {
		FeedbackResponseDTO feedback = new FeedbackResponseDTO();
		feedback.setFeedbackId(FEEDBACK_ID);
		return feedback;
	}

}
