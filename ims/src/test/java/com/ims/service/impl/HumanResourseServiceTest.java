package com.ims.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ims.dao.CandidateRepository;
import com.ims.dao.FeedbackOfCandidateRepository;
import com.ims.dao.InformationRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.UserDetailsRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.CandidateDTO;
import com.ims.dto.ScheduleInterviewDTO;
import com.ims.dto.request.InformationRequestDTO;
import com.ims.entity.Candidate;
import com.ims.entity.FeedbackOFCandidate;
import com.ims.entity.Information;
import com.ims.entity.Interview;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.excel.ExcelFile;
import com.ims.exception.ConflictException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.properties.ConstantProperties;
import com.ims.util.DateOfBirthUtil;
import com.ims.util.NameUtil;

/**
 * The Class HumanResourseServiceTest.
 */
@SpringBootTest
public class HumanResourseServiceTest {

	/** The human resourse service. */
	@InjectMocks
	private HumanResourseServiceImpl humanResourseService;

	/** The candidate repository. */
	@Mock
	private CandidateRepository candidateRepository;

	/** The response. */
	@Mock
	private HttpServletResponse response;

	/** The feedback OF candidate. */
	@Mock
	private FeedbackOFCandidate feedbackOFCandidate;

	/** The feedback of candidate repository. */
	@Mock
	private FeedbackOfCandidateRepository feedbackOfCandidateRepository;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	@Mock
	private InterviewRepository interviewRepository;

	@Mock
	private ExcelFile genrateExcel;

	@Mock
	private NameUtil nameUtil;

	@Mock
	private DateOfBirthUtil dateOfBirthUtil;

	@Mock
	private UserDetailsRepository userDetailsRepository;

	@Mock
	private InformationRepository informationRepository;
	/** The Constant emailId. */
	private final static String EMAIL_ID = "vahak@gmail.com";

	/** The Constant address. */
	private final static String ADDRESS = "Ahmedabad";

	/** The Constant date. */
	private final static LocalDate DATE = LocalDate.of(1996, 5, 15);

	private final static long INFORMATION_ID = 2;
	
	/** The Constant number. */
	private final static String NUMBER = "9898850516";

	/** The Constant gender. */
	private final static String GENDER = "female";

	/** The Constant name. */
	private final static String NAME = "abcd";

	/** The Constant department. */
	private final static String DEPARTMENT = "PES";

	/** The Constant designation. */
	private final static String DESIGNATION = "Fresher";

	/** The Constant qualification. */
	private static final String QUALIFIATION = "BE";

	/** The Constant candidateId. */
	private static final long CANDIDATE_ID = 1;

	/** The Constant experience. */
	private static final int EXPERIENCE = 2;

	/** The Constant filePath. */
	private static final String FILE_PATH = "E:\\SpringBoot_and_Junit5.pdf";
	// "D:\\java.pdf";
	// Path filePath = Paths.get( "java.pdf" );

	// Path file =

	/** The Constant convertToByte. */
	private static final String CONVERT_TO_BYTE = "Dummy";

	/** The Constant interviewRound. */
	private static final String INTERVIEW_ROUND = "1";

	/** The interview date. */
	private static final LocalDate INTERVIEW_DATE = LocalDate.now();

	/** The interview time. */
	private static final LocalTime INTERVIEW_TIME = LocalTime.now();

	/** The venue. */
	private static final String VENUE = "MCG";

	/** The interviewer department. */
	private static final String INTERVIEWER_DEPARTMENT = "PES";

	/** The interviewer job designation. */
	private static final String INTERVIEWER_DESIGNATION = "Engineer";

	/** The interviewer id. */
	private static final long INTERVIEWER_ID = 23;

	/** The user id. */
	private static final long USER_ID = 1;

	private static final long INVALID_USER_ID = 0;

	private static final String HIRE_CANDIDATE = "Hire";

	private static final String REJECTED_CANDIDATE = "Reject";

	private static final long INTERVIEW_ID = 11;
	
	/** The Constant FEEDBACK_ID. */
	private final static long FEEDBACK_ID = Long.parseLong("1");

	
	/** The Constant today. */
	private final static LocalDate TODAY = LocalDate.now();

	
	/** The Constant YESTERDAY. */
	private final static LocalDate YESTERDAY = TODAY.minusDays(1);
	/** The Constant CANDIDATE_NAME. */
	private final static String CANDIDATE_NAME = "abcd";
	
	/** The Constant INTERVIEWER_NAME. */
	private final static String INTERVIEWER_NAME = "abcd";
	
	/** The Constant ROUND. */
	private final static int ROUND = 6;
	
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

	/** The Constant INTERVIEW_RESULT_REJECT. */
	private final static String INTERVIEW_RESULT_REJECT = "Reject";

	/** The Constant INTERVIEW_RESULT_HIRE. */
	private final static String INTERVIEW_RESULT_HIRE = "Hire";

	/** The Constant NEXT_ROUND. */
	private final static String NEXT_ROUND = "Yes";

	/** The Constant FEEDBACK_HISTORY_ID. */
	private final static long FEEDBACK_HISTORY_ID = Long.parseLong("1");

	private static final String GROUP_NAME = "PES";

	private static final String INFORMATION_TYPE = "PES";

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Gets the candidate DTO.
	 *
	 * @return the candidate DTO
	 */
	CandidateDTO getCandidateDTO() {

		CandidateDTO candidateDTO = new CandidateDTO();
		candidateDTO.setAddress(ADDRESS);
		candidateDTO.setDateOfBirth(DATE);
		candidateDTO.setContactNumber(NUMBER);
		candidateDTO.setEmailId(EMAIL_ID);
		candidateDTO.setExperience(2);
		candidateDTO.setGender(GENDER);
		candidateDTO.setCandidateName(NAME);
		candidateDTO.setQualification(QUALIFIATION);
		candidateDTO.setCandidateId(1);
		candidateDTO.setFilepath(FILE_PATH);
		candidateDTO.setCandidateDepartment(DEPARTMENT);
		candidateDTO.setCandidateDesignation(DESIGNATION);

//		byte[] resume = convertToByte.getBytes();
//		candidateDTO.setResume(resume);

		return candidateDTO;
	}

	/**
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	Candidate getCandidate() {
		CandidateDTO candidateDTO = getCandidateDTO();
		Candidate candidate = new Candidate();
		candidate.setAddress(candidateDTO.getAddress());
		candidate.setDateOfBirth(candidateDTO.getDateOfBirth());
		candidate.setContactNumber(candidateDTO.getContactNumber());
		candidate.setEmailId(candidateDTO.getEmailId());
		candidate.setExperience(candidateDTO.getExperience());
		candidate.setGender(candidateDTO.getGender());
		candidate.setCandidateName(candidateDTO.getCandidateName());
		candidate.setQualification(candidateDTO.getQualification());
		candidate.setCandidateId(candidateDTO.getCandidateId());
		candidateDTO.setCandidateDepartment(DEPARTMENT);
		candidateDTO.setCandidateDesignation(DESIGNATION);
		// byte[] resume = convertToByte.getBytes();
		// candidate.setResume(candidateDTO.getResume());
		candidate.setFilepath(candidateDTO.getFilepath());
		candidate.setInterview(getInterview());
		return candidate;

	}

	/**
	 * Adds the candidate test.
	 *
	 * @throws IOException         Signals that an I/O exception has occurred.
	 * @throws ConflictException
	 * @throws UnprocessableEntity
	 */
	@Test
	public void addCandidateTest() throws IOException, ConflictException, UnprocessableEntity {
		CandidateDTO candidateDTO = getCandidateDTO();
		 getCandidate();

		when(candidateRepository.findByEmailId(anyString())).thenReturn(null);

		when(dateOfBirthUtil.isValidAge(candidateDTO.getDateOfBirth())).thenReturn(true);
		when(nameUtil.isValidName(candidateDTO.getEmailId())).thenReturn(true);

		assertNotNull(humanResourseService.addCandidate(getCandidateDTO()));
		// assertEquals(candidateDTO, candidateDTO);

	}

	@Test(expected = UnprocessableEntity.class)
	public void addCandidateTestException() throws IOException, ConflictException, UnprocessableEntity {

		when(candidateRepository.findByEmailId(anyString())).thenReturn(getCandidate());

		getCandidateDTO();
		humanResourseService.addCandidate(getCandidateDTO());

	}

	/**
	 * Adds the candidate test candidate exist exception.
	 *
	 * @throws IOException         Signals that an I/O exception has occurred.
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity
	 */
	@Test(expected = ConflictException.class)
	public void addCandidateTestCandidateExistException() throws IOException, ConflictException, UnprocessableEntity {

		CandidateDTO candidateDTO = getCandidateDTO();
		Candidate candidate = getCandidate();

		when(candidateRepository.findByEmailId(anyString())).thenReturn(candidate);

		when(dateOfBirthUtil.isValidAge(candidateDTO.getDateOfBirth())).thenReturn(true);
		when(nameUtil.isValidName(candidateDTO.getEmailId())).thenReturn(true);

		humanResourseService.addCandidate(candidateDTO);

	}

	@Test(expected = ConflictException.class)
	public void addCandidateTestCandidateExistandEmailException()
			throws IOException, ConflictException, UnprocessableEntity {

		CandidateDTO candidateDTO = getCandidateDTO();
		Candidate candidate = getCandidate();

		when(candidateRepository.findByEmailId(anyString())).thenReturn(candidate);

		when(dateOfBirthUtil.isValidAge(candidateDTO.getDateOfBirth())).thenReturn(true);
		when(nameUtil.isValidName(candidateDTO.getEmailId())).thenReturn(true);

		humanResourseService.addCandidate(candidateDTO);

	}

	/**
	 * Adds the candidate test age not valid.
	 *
	 * @throws IOException         Signals that an I/O exception has occurred.
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity
	 */
	@Test(expected = UnprocessableEntity.class)
	public void addCandidateTestAgeNotValid() throws IOException, ConflictException, UnprocessableEntity {

		CandidateDTO candidateDTO = getCandidateDTO();
		Candidate candidate = getCandidate();

		when(candidateRepository.findByEmailId(anyString())).thenReturn(candidate);

		when(dateOfBirthUtil.isValidAge(candidateDTO.getDateOfBirth())).thenReturn(false);
		when(nameUtil.isValidName(candidateDTO.getEmailId())).thenReturn(true);

		humanResourseService.addCandidate(candidateDTO);

	}

	@Test(expected = UnprocessableEntity.class)
	public void addCandidateTestAgeandEmailNotValid() throws IOException, ConflictException, UnprocessableEntity {

		CandidateDTO candidateDTO = getCandidateDTO();
		 getCandidate();

		when(candidateRepository.findByEmailId(anyString())).thenReturn(null);

		when(dateOfBirthUtil.isValidAge(candidateDTO.getDateOfBirth())).thenReturn(false);
		when(nameUtil.isValidName(candidateDTO.getEmailId())).thenReturn(false);

		humanResourseService.addCandidate(candidateDTO);

	}

	@Test(expected = UnprocessableEntity.class)
	public void addCandidateTestAllFieldNotValid() throws IOException, ConflictException, UnprocessableEntity {

		CandidateDTO candidateDTO = getCandidateDTO();
		Candidate candidate = getCandidate();

		when(candidateRepository.findByEmailId(anyString())).thenReturn(candidate);

		when(dateOfBirthUtil.isValidAge(candidateDTO.getDateOfBirth())).thenReturn(false);
		when(nameUtil.isValidName(candidateDTO.getEmailId())).thenReturn(false);

		humanResourseService.addCandidate(candidateDTO);

	}


	/**
	 * Update candidate not exist.
	 *
	 * @throws IOException               Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void updateCandidate() throws IOException, ResourceNotFoundException {
		Candidate candidate = getCandidate();
		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(candidate);

		CandidateDTO candidateDTO = getCandidateDTO();

		humanResourseService.updateCandidate(CANDIDATE_ID, candidateDTO);
		assertEquals(candidateDTO, candidateDTO);
	}

	/**
	 * Update candidate.
	 *
	 * @throws IOException               Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void updateCandidateNotExist() throws IOException, ResourceNotFoundException {

		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(null);
		CandidateDTO candidateDTO = getCandidateDTO();
		
		humanResourseService.updateCandidate(CANDIDATE_ID, candidateDTO);
	}
	/**
	 * Delete candidate.
	 * 
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void deleteCandidateWhenFeedbackNotGiven() throws ResourceNotFoundException {
		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(getCandidate());
		when(feedbackOfCandidateRepository.findByCandidate(getCandidate())).thenReturn(null);
		assertNotNull(humanResourseService.deleteCandidate(CANDIDATE_ID));
		
	}
	
	@Test
	public void deleteCandidateWhenFeedbackGiven() throws ResourceNotFoundException {
		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(getCandidate());
		when(feedbackOfCandidateRepository.findByCandidate(any())).thenReturn(getFeedback());
		assertNotNull(humanResourseService.deleteCandidate(CANDIDATE_ID));
		

	}
	

	/**
	 * Delete candidate when candidate not exist.
	 * 
	 * @throws ResourceNotFoundException
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void deleteCandidateWhenCandidateNotExist() throws ResourceNotFoundException {
		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(null);
		when(feedbackOfCandidateRepository.findByCandidate(getCandidate())).thenReturn(feedbackOFCandidate);

		humanResourseService.deleteCandidate(CANDIDATE_ID);
	}

	/**
	 * Gets the candidates not exist candidate test.
	 *
	 * @return the candidates not exist candidate test
	 */
	@Test
	public void getCandidatesNotExistCandidateTest() {
		when(candidateRepository.findAll()).thenReturn(null);
		assertTrue(humanResourseService.getCandidates().isEmpty());

	}

	/**
	 * Gets the candidates test.
	 *
	 * @return the candidates test
	 */
	@Test
	public void getCandidatesTest() {
		List<Candidate> candidateList = new ArrayList<Candidate>();
		candidateList.add(getCandidate());
		when(candidateRepository.findAll()).thenReturn(candidateList);
		assertNotNull(humanResourseService.getCandidates());

	}

	/**
	 * Test get resume.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetResume() throws IOException {

		ServletOutputStream outputStream = new ServletOutputStream() {

			@Override
			public void write(int b) throws IOException {
			}

			@Override
			public void setWriteListener(WriteListener listener) {
			}

			@Override
			public boolean isReady() {
				return false;
			}
		};
		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(getCandidateObject());
		when(response.getOutputStream()).thenReturn(outputStream);
		assertNotNull(humanResourseService.getResume(CANDIDATE_ID, response));
	}

	/**
	 * Test get resume candidate not found.
	 */
	@Test
	public void testGetResumeCandidateNotFound() {
		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(null);
		assertFalse(humanResourseService.getResume(CANDIDATE_ID, response));
	}

	/**
	 * Test get list of unscheduled interview. This method is use to get list of
	 * unscheduled interview.
	 */
	@Test
	public void testGetListOfUnscheduledInterview() {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		listOfCandidate.add(getCandidate());
		when(candidateRepository.getCandidateUnscheduledInterview()).thenReturn(listOfCandidate);
		assertNotNull(humanResourseService.getListOfNotScheduleInterview());
	}

	/**
	 * Test get candidate. Test get one candidate
	 * 
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void testGetCandidate() throws ResourceNotFoundException {
		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(getCandidateObject());
		assertNotNull(humanResourseService.getCandidate(CANDIDATE_ID));
	}

	/**
	 * Test get candidate not found. Test get one candidate when candidate not found
	 * 
	 * @throws ResourceNotFoundException
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testGetCandidateNotFound() throws ResourceNotFoundException {
		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(null);
		humanResourseService.getCandidate(CANDIDATE_ID);
	}

	/**
	 * Test schedule interview. Test scheuled interview method
	 * 
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void testScheduleInterview() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(getCandidate());
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		assertNotNull(humanResourseService.scheduleInterview(scheduleInterview));
	}

	/**
	 * Test schedule interview user id not found. Test schedule interview when User
	 * id is 0
	 * 
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testScheduleInterviewUserIdNotFound() throws UnprocessableEntity, ResourceNotFoundException {
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setUserId(INVALID_USER_ID);
		humanResourseService.scheduleInterview(scheduleInterview);
	}

	/**
	 * Test schedule interview HumanResource not found.
	 * 
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 * 
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testScheduleInterviewHumanResourceNotFound() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(null);

		humanResourseService.scheduleInterview(scheduleInterview);
	}

	/**
	 * Test schedule interview human resource Department is null.
	 * 
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testScheduleInterviewHumanResourceDepartementIsNull()
			throws UnprocessableEntity, ResourceNotFoundException {
		 getUser();
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setInterviewerDepartment(null);

		humanResourseService.scheduleInterview(scheduleInterview);
	}

	/**
	 * Test schedule interview human resourceterviewer job designation null.
	 * 
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testScheduleInterviewHumanResourceterviewerJobDesignationNull()
			throws UnprocessableEntity, ResourceNotFoundException {

		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setInterviewerDesignation(null);

		humanResourseService.scheduleInterview(scheduleInterview);
	}

	/**
	 * Test schedule interview human resourceterviewer venue null.
	 * 
	 * @throws UnprocessableEntity
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testScheduleInterviewHumanResourceterviewerVenueNull()
			throws UnprocessableEntity, ResourceNotFoundException {

		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setInterviewVenue(null);

		humanResourseService.scheduleInterview(scheduleInterview);
	}

	/**
	 * Test schedule interview human resource interviewer id not found.
	 * 
	 * @throws UnprocessableEntity
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testScheduleInterviewHumanResourceInterviewerIdNotFound()
			throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setInterviewerId(0);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(null);

		humanResourseService.scheduleInterview(scheduleInterview);
	}

	/**
	 * Test get pendding interview approval. Test GetPendingApproval Method
	 */
	@Test
	public void testGetPenddingInterviewApproval() {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		listOfCandidate.add(getCandidateObject());
		when(candidateRepository.getCandidatePendingInterviewApproval()).thenReturn(listOfCandidate);
		assertNotNull(humanResourseService.getPenddingInterviewApproval());
	}

	/**
	 * Test get list of schedule interview.
	 */
	@Test
	public void testGetListOfScheduleInterview() {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		listOfCandidate.add(getCandidateObject());
		when(candidateRepository.getCandidatePendingInterviewApproval()).thenReturn(listOfCandidate);
		assertNotNull(humanResourseService.getListOfScheduleInterview());
	}

	/**
	 * Test get list OF accepted candidates.
	 */
	@Test
	public void testGetListOFAcceptedCandidates() {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();
		candidate.setInterviewResult(HIRE_CANDIDATE);
		listOfCandidate.add(candidate);
		when(candidateRepository.getListOfAcceptedCandidates()).thenReturn(listOfCandidate);
		assertNotNull(humanResourseService.getListOfHireCandidates());

	}

	/**
	 * Test get list of rejected candidate.
	 */
	@Test
	public void testGetListOfRejectedCandidate() {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();
		candidate.setInterviewResult(REJECTED_CANDIDATE);
		listOfCandidate.add(candidate);
		when(candidateRepository.getListOfRejectedCandidates()).thenReturn(listOfCandidate);
		assertNotNull(humanResourseService.getListOfRejectedCandidates());

	}

	/**
	 * Test genrate hire candidate excel.
	 *
	 * @throws IOException               Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void testGenrateHireCandidateExcel() throws IOException, ResourceNotFoundException {
//		ServletOutputStream outputStream = new ServletOutputStream() {
//
//			@Override
//			public void write(int b) throws IOException {
//			}
//
//			@Override
//			public void setWriteListener(WriteListener listener) {
//			}
//
//			@Override
//			public boolean isReady() {
//				return false;
//			}
//		};
//		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
//		Candidate candidate = getCandidate();
//		candidate.setInterviewResult(HIRE_CANDIDATE);
//		listOfCandidate.add(candidate);
//		when(humanResourseService.getListOfHireCandidates()).thenReturn(listOfCandidate);
//		when(response.getOutputStream()).thenReturn(outputStream);
		List<Candidate> listOfCandidate = new ArrayList<>();
		when(genrateExcel.downloadCandidateExcel(response, listOfCandidate)).thenReturn(true);
		assertNotNull(humanResourseService.downloadHireCandidateExcel());
	}

	/**
	 * Test genrate hire candidate excel error.
	 *
	 * @throws IOException               Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void testGenrateHireCandidateExcelError() throws IOException {
		List<Candidate> listOfCandidate = new ArrayList<>();
		when(genrateExcel.downloadCandidateExcel(response, listOfCandidate)).thenReturn(false);
		assertFalse(humanResourseService.downloadHireCandidateExcel());
	}

	/**
	 * Testdownload reject candidate excel.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testdownloadRejectCandidateExcel() throws IOException {
//		ServletOutputStream outputStream = new ServletOutputStream() {
//
//			@Override
//			public void write(int b) throws IOException {
//			}
//
//			@Override
//			public void setWriteListener(WriteListener listener) {
//			}
//
//			@Override
//			public boolean isReady() {
//				return false;
//			}
//		};
//		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
//		Candidate candidate = getCandidate();
//		candidate.setInterviewResult(REJECTED_CANDIDATE);
//		listOfCandidate.add(candidate);
//		when(humanResourseService.getListOfRejectedCandidates()).thenReturn(listOfCandidate);
//		when(response.getOutputStream()).thenReturn(outputStream);
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		when(genrateExcel.downloadCandidateExcel(response, listOfCandidate)).thenReturn(true);
		assertTrue(humanResourseService.downloadRejectCandidateExcel());
	}

	@Test
	public void testdownloadRejectCandidateExcelError() throws IOException {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		when(genrateExcel.downloadCandidateExcel(response, listOfCandidate)).thenReturn(false);
		assertFalse(humanResourseService.downloadRejectCandidateExcel());
	}

	/**
	 * Test reschedule interview user id invalid.
	 * 
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testRescheduleInterviewUserIdInvalid() throws UnprocessableEntity, ResourceNotFoundException {
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setUserId(INVALID_USER_ID);
		humanResourseService.rescheduleInterview(scheduleInterview);
//		User user = getUser();
//		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
//		scheduleInterview.setUserId(INVALID_USER_ID);
//		when(userRepository.findByUserId(anyLong())).thenReturn(user);
//		when(humanResourseRepository.findByUser(user)).thenReturn(getHumanResource());
//		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(getInterview());
//		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(getCandidate());
//		when(interviewerRepository.findByInterviewerId(INTERVIEWER_ID)).thenReturn(getInterviewer());
//		assertFalse(humanResourseService.rescheduleInterview(scheduleInterview));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testRescheduleInterviewHumanResourceNotFound() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(null);

		humanResourseService.rescheduleInterview(scheduleInterview);
	}

	@Test(expected = UnprocessableEntity.class)
	public void testRescheduleInterviewDepartementIsNull() throws UnprocessableEntity, ResourceNotFoundException {

		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setInterviewerDepartment(null);

		humanResourseService.rescheduleInterview(scheduleInterview);
	}

	@Test(expected = UnprocessableEntity.class)
	public void testReScheduleInterviewHumanResourceterviewerJobDesignationNull()
			throws UnprocessableEntity, ResourceNotFoundException {

		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setInterviewerDesignation(null);

		humanResourseService.rescheduleInterview(scheduleInterview);
	}
	
	@Test
	public void testgetCandidateList() {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		listOfCandidate.add(getCandidateObject());
		when(candidateRepository.getCandidateList()).thenReturn(listOfCandidate);
		assertNotNull(humanResourseService.getCandidateList());
	}
	
	@Test
	public void testgetInterviewOfToday() {
		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();

		Interview interview = getInterview();
		interview.setInterviewDate(TODAY);
		candidate.setInterview(interview);

		candidateList.add(candidate);
		when(candidateRepository.getScheduledIntervewList()).thenReturn(candidateList);

		candidateList.add(candidate);

		assertNotNull(humanResourseService.getInterviewOfToday());
	}
	
	@Test
	public void testgetInterviewOfTodayNoDateIsToday() {
		List<Candidate> candidateList = new ArrayList<>();

		Candidate candidate = getCandidate();

		Interview interview = getInterview();
		interview.setInterviewDate(YESTERDAY);
		candidate.setInterview(interview);

		candidateList.add(candidate);
		when(candidateRepository.getScheduledIntervewList()).thenReturn(candidateList);

		candidateList.add(candidate);

		assertNotNull(humanResourseService.getInterviewOfToday());
	}
	

	@Test(expected = UnprocessableEntity.class)
	public void testReScheduleInterviewHumanResourceterviewerVenueNull()
			throws UnprocessableEntity, ResourceNotFoundException {

		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setInterviewVenue(null);

		humanResourseService.rescheduleInterview(scheduleInterview);
	}

	@Test(expected = UnprocessableEntity.class)
	public void testReScheduleInterviewHumanResourceInterviewerIdNotFound()
			throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		scheduleInterview.setInterviewerId(ConstantProperties.FLAG_ZERO);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(null);

		humanResourseService.rescheduleInterview(scheduleInterview);
	}

	/**
	 * Test re schedule interview.
	 *
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	@Test
	public void testReScheduleInterview() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();
		ScheduleInterviewDTO scheduleInterview = getInterviewDTO();
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(candidateRepository.findBycandidateId(CANDIDATE_ID)).thenReturn(getCandidate());
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		when(interviewRepository.findByInterviewId(anyLong())).thenReturn(getInterview());
		assertNotNull(humanResourseService.rescheduleInterview(scheduleInterview));
	}

	/**
	 * Test get information.
	 */
	@Test
	public void testGetInformation() {

		List<Information> informationList = new ArrayList<>();
		informationList.add(getInformation());
		when(informationRepository.findAll()).thenReturn(informationList);
		assertNotNull(humanResourseService.getInformation());
	}

	/**
	 * Test get candidate list.
	 */
	@Test
	public void testGetCandidateList() {
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();

		listOfCandidate.add(candidate);
		when(candidateRepository.getCandidateList()).thenReturn(listOfCandidate);

		assertNotNull(humanResourseService.getCandidateList());
	}

	/**
	 * Test save information.
	 */
	@Test
	public void testSaveInfomation() {
		humanResourseService.saveInfomation(getInformationDTO());
	}

	InformationRequestDTO getInformationDTO() {
		InformationRequestDTO information = new InformationRequestDTO();
		information.setGroupName(GROUP_NAME);
		information.setInformationType(INFORMATION_TYPE);
		return information;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	User getUser() {
		User user = new User();
		user.setUserId(USER_ID);
		user.setUsername(EMAIL_ID);
		return user;
	}

	Information getInformation() {
		Information information = new Information();
		information.setInformationId(INFORMATION_ID);
		return information;
	}

	Interview getInterview() {
		Interview interview = new Interview();
		interview.setInterviewId(INTERVIEW_ID);
		interview.setInterviewer(getUserDetails());
		return interview;
	}

	UserDetails getUserDetails() {
		UserDetails userDetails = new UserDetails();
		userDetails.setUser(getUser());
		return userDetails;
	}
	
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
	 * Gets the candidate object.
	 *
	 * @return the candidate object
	 */
	Candidate getCandidateObject() {
		Candidate candidate = new Candidate();

		byte[] resume = CONVERT_TO_BYTE.getBytes();
		candidate.setAddress(ADDRESS);
		candidate.setDateOfBirth(DATE);
		candidate.setContactNumber(NUMBER);
		candidate.setEmailId(EMAIL_ID);
		candidate.setExperience(EXPERIENCE);
		candidate.setGender(GENDER);
		candidate.setCandidateName(NAME);
		candidate.setQualification(QUALIFIATION);
		candidate.setCandidateId(CANDIDATE_ID);
		candidate.setFilepath(FILE_PATH);
		candidate.setResume(resume);
		candidate.setInterview(getInterview());
		return candidate;
	}

	/**
	 * Gets the interview DTO.
	 *
	 * @return the interview DTO
	 */
	ScheduleInterviewDTO getInterviewDTO() {
		ScheduleInterviewDTO scheduleInterviewDTO = new ScheduleInterviewDTO();
		scheduleInterviewDTO.setCandidateId(CANDIDATE_ID);
		scheduleInterviewDTO.setRound(INTERVIEW_ROUND);
		scheduleInterviewDTO.setInterviewDate(INTERVIEW_DATE);
		scheduleInterviewDTO.setInterviewerDepartment(INTERVIEWER_DEPARTMENT);
		scheduleInterviewDTO.setInterviewerId(INTERVIEWER_ID);
		scheduleInterviewDTO.setInterviewDate(INTERVIEW_DATE);
		scheduleInterviewDTO.setInterviewTime(INTERVIEW_TIME);
		scheduleInterviewDTO.setInterviewerDesignation(INTERVIEWER_DESIGNATION);
		scheduleInterviewDTO.setInterviewVenue(VENUE);
		scheduleInterviewDTO.setUserId(USER_ID);

		return scheduleInterviewDTO;
	}

}
