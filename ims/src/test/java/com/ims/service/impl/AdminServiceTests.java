	package com.ims.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.ims.dao.AdminRepository;
import com.ims.dao.CandidateRepository;
import com.ims.dao.HumanResourseRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.InterviewerRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.CandidateDTO;
import com.ims.dto.UserTypeDTO;
import com.ims.entity.Admin;
import com.ims.entity.Candidate;
import com.ims.entity.HumanResource;
import com.ims.entity.Interview;
import com.ims.entity.Interviewer;
import com.ims.entity.User;
import com.ims.util.NameUtil;
import com.ims.util.PasswordUtil;

/**
 * The Class LoginServiceTests.
 */
@SpringBootTest
public class AdminServiceTests {

	/** The loginservice. */
	@InjectMocks
	private AdminServiceImpl adminService;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/** The hr repository. */
	@Mock
	private HumanResourseRepository humanResourceRepository;

	/** The interviewer repository. */
	@Mock
	private InterviewerRepository interviewerRepository;

	/** The admin repository. */
	@Mock
	private AdminRepository adminRepository;

	/** The candidate repository. */
	@Mock
	private CandidateRepository candidateRepository;

	/** The interview repository. */
	@Mock
	private InterviewRepository interviewRepository;

	/** The password util. */
	@Mock
	private PasswordUtil passwordUtil;

	/** The name util. */
	@Mock
	private NameUtil nameUtil;

	/** The Constant username. */
	private final static String USER_NAME = "vahak@gmail.com";

	/** The Constant password. */
	private final static String PASSWORD = "AB@123";

	/** The Constant name. */
	private final static String NAME = "VAHAK";

	/** The Constant user_interviewer. */
	private final static String USER_INTERVIEWER = "interviewer";

	/** The Constant user_admin. */
	private final static String USER_ADMIN = "admin";

	/** The Constant user_humanResource. */
	private final static String USER_HUMANRESOURCE = "humanresource";

	/** The Constant interviewer_department. */
	private final static String INTERVIWER_DEPARTMENT = "PES";

	/** The Constant interviewer_designation. */
	private final static String INTERVIEWER_DESIGNATION = "Fresher";

	/** The Constant humanresource_department. */
	private final static String HUMANRESOURCE_DEPARTMENT = "humanresource";

	/** The Constant humanresource_designation. */
	private final static String HUMANRESOURCE_DESIGNATION = "Fresher";

	/** The Constant admin_department. */
	private final static String ADMIN_DEPARTMENT = "admin";

	/** The Constant admin_designation. */
	private final static String ADMIN_DESIGNATION = "Fresher";

	/** The Constant usertype_invalid. */
	private final static String USER_TYPE_INVALID = "Candidate";

	/** The Constant username_invalid. */
	private final static String USER_NAME_INVALID = "username";

	/** The Constant flag. */
	private final static int FLAG = 1;

	/** The Constant flagZero. */
	private final static int FLAG_ZERO = 0;

	/** The Constant experience. */
	private final static double EXPERIENCE = 1;

	/** The Constant mobilenumber. */
	private final static long MOBILE_NUMBER = Long.parseLong("9876543210");

	/** The Constant userId. */
	private final static long ID = Long.parseLong("1");

	/** The Constant date. */
	private final static LocalDate DOB = LocalDate.of(1996, 5, 15);

	/** The Constant availableDate. */
	private final static LocalDate AVAILABLE_DATE = LocalDate.of(2020, 02, 24);

	/** The Constant availableTimeFrom. */
	private final static LocalTime AVAILABLE_TIME_FROM = LocalTime.of(12, 00);

	/** The Constant availableTimeTo. */
	private final static LocalTime AVAILABLE_TIME_TO = LocalTime.of(13, 00);

	/** The Constant address. */
	private final static String ADDRESS = "Ahmedabad";

	/** The Constant gender. */
	private final static String GENDER = "female";

	/** The Constant department. */
	private final static String CANDIDATE_DEPARTMENT = "PES";

	/** The Constant designation. */
	private final static String CANDIDATE_DESIGNATION = "Fresher";

	/** The Constant qualification. */
	private static final String CANDIDATE_QUALIFICATION = "BE";

	/** The Constant filePath. */
	private static final String FILE_PATH = "E:\\SpringBoot_and_Junit5";

	/** The Constant interviewRound. */
	private static final String INTERVIEW_ROUND = "1";

	/** The Constant interviewScheduled. */
	private static final String INTERVIEW_SCHEDULE = "scheduled";

	/** The Constant rejectCandidate. */
	private static final String REJECT_CANDIDATE = "Reject";

	/** The Constant acceptCandidate. */
	private static final String ACCEPT_CANDIDATE = "Accept";

	/** The Constant interviewId. */
	private final static long INTERVIEW_ID = Long.parseLong("2");

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Method for creating instace of UserTypeDTO for Interviewer userType .
	 *
	 * @return UserTypeDTO of Interviewer usertype
	 */
	public UserTypeDTO getInterviewerDTO() {

		UserTypeDTO userDTO = new UserTypeDTO();
		userDTO.setUsername(USER_NAME);
		userDTO.setPassword(PASSWORD);
		userDTO.setUserType(USER_INTERVIEWER);
		userDTO.setDepartment(INTERVIWER_DEPARTMENT);
		userDTO.setDesignation(INTERVIEWER_DESIGNATION);
		userDTO.setDob(DOB);
		userDTO.setExperience(EXPERIENCE);
		userDTO.setMobilenumber(MOBILE_NUMBER);
		userDTO.setName(NAME);
		userDTO.setFlag(FLAG);
		userDTO.setAvailableDate(AVAILABLE_DATE);
		userDTO.setAvailableTimeFrom(AVAILABLE_TIME_FROM);
		userDTO.setAvailableTimeTo(AVAILABLE_TIME_TO);
		return userDTO;
	}

	/**
	 * Method for creating instace of UserTypeDTO for Admin userType .
	 *
	 * @return UserTypeDTO of admin usertype
	 */
	public UserTypeDTO getAdminDTO() {

		UserTypeDTO userDTO = new UserTypeDTO();
		userDTO.setUsername(USER_NAME);
		userDTO.setPassword(PASSWORD);
		userDTO.setUserType(USER_ADMIN);
		userDTO.setDepartment(ADMIN_DEPARTMENT);
		userDTO.setDesignation(ADMIN_DESIGNATION);
		userDTO.setDob(DOB);
		userDTO.setExperience(EXPERIENCE);
		userDTO.setMobilenumber(MOBILE_NUMBER);
		userDTO.setName(NAME);
		return userDTO;
	}

	/**
	 * Method for creating instace of UserTypeDTO for Human resource userType .
	 *
	 * @return UserTypeDTO of human resource usertype
	 */
	public UserTypeDTO getHumanResourceDTO() {

		UserTypeDTO userDTO = new UserTypeDTO();
		userDTO.setUsername(USER_NAME);
		userDTO.setPassword(PASSWORD);
		userDTO.setUserType(USER_HUMANRESOURCE);
		userDTO.setDepartment(HUMANRESOURCE_DEPARTMENT);
		userDTO.setDesignation(HUMANRESOURCE_DESIGNATION);
		userDTO.setDob(DOB);
		userDTO.setExperience(EXPERIENCE);
		userDTO.setMobilenumber(MOBILE_NUMBER);
		userDTO.setName(NAME);
		return userDTO;
	}

	/**
	 * Method for creating instace of User for Interviewer userType .
	 *
	 * @return User of usertype Interviewer
	 */
	public User getInterviewerUser() {
		User user = new User();
		user.setUserId(ID);
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_INTERVIEWER);
		return user;
	}

	/**
	 * Method for creating instace of User for Admin userType.
	 *
	 * @return User of usertype Admin
	 */
	public User getAdminUser() {
		User user = new User();
		user.setUserId(ID);
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_ADMIN);
		return user;
	}

	/**
	 * Method for creating instace of User for Human resource userType.
	 *
	 * @return User of usertype HumanResource
	 */
	public User getHumanResourceUser() {
		User user = new User();
		user.setUserId(ID);
		user.setUsername(USER_NAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_HUMANRESOURCE);
		return user;
	}

	/**
	 * Method for creating instace of Interviewer.
	 *
	 * @return Instance of Interviewer
	 */
	public Interviewer getInterviewer() {

		Interviewer interviewer = new Interviewer();
		interviewer.setInterviewerId(ID);
		interviewer.setInterviewerDepartment(INTERVIWER_DEPARTMENT);
		interviewer.setInterviewerDesignation(INTERVIEWER_DESIGNATION);
		interviewer.setInterviewerDob(DOB);
		interviewer.setInterviewerExperience(EXPERIENCE);
		interviewer.setInterviewerMobilenumber(MOBILE_NUMBER);
		interviewer.setAvailableDate(AVAILABLE_DATE);
		interviewer.setAvailableTimeFrom(AVAILABLE_TIME_FROM);
		interviewer.setAvailableTimeTo(AVAILABLE_TIME_TO);
		interviewer.setUser(getInterviewerUser());
		return interviewer;

	}

	/**
	 * Method for creating instace of Admin.
	 *
	 * @return Instance of Admin
	 */
	public Admin getAdmin() {

		Admin admin = new Admin();
		admin.setAdminId(ID);
		admin.setAdminDepartment(ADMIN_DEPARTMENT);
		admin.setAdminDesignation(ADMIN_DESIGNATION);
		admin.setAdminDob(DOB);
		admin.setAdminExperience(EXPERIENCE);
		admin.setAdminMobilenumber(MOBILE_NUMBER);
		admin.setUser(getAdminUser());

		return admin;
	}

	/**
	 * Method for creating instace of Human resource.
	 *
	 * @return Instance of HumanResource
	 */
	public HumanResource getHumanResource() {

		HumanResource humanResource = new HumanResource();
		humanResource.setHrId(ID);
		humanResource.setHrDepartment(HUMANRESOURCE_DEPARTMENT);
		humanResource.setHrDesignation(HUMANRESOURCE_DESIGNATION);
		humanResource.setHrDob(DOB);
		humanResource.setHrExperience(EXPERIENCE);
		humanResource.setHrMobilenumber(MOBILE_NUMBER);
		humanResource.setUser(getHumanResourceUser());
		return humanResource;
	}

	/**
	 * Method for creating instace of CandidateDTO.
	 *
	 * @return the candidate DTO
	 */
	CandidateDTO getCandidateDTO() {

		CandidateDTO candidateDTO = new CandidateDTO();
		candidateDTO.setAddress(ADDRESS);
		candidateDTO.setDateOfBirth(DOB);
		candidateDTO.setContactNumber(String.valueOf(MOBILE_NUMBER));
		candidateDTO.setEmailId(USER_NAME);
		candidateDTO.setExperience(2);
		candidateDTO.setGender(GENDER);
		candidateDTO.setCandidateName(NAME);
		candidateDTO.setQualification(CANDIDATE_QUALIFICATION);
		candidateDTO.setCandidateId(ID);
		candidateDTO.setFilepath(FILE_PATH);
		candidateDTO.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		candidateDTO.setCandidateDesignation(CANDIDATE_DESIGNATION);

		return candidateDTO;
	}

	/**
	 * Method for creating instace of Candidate.
	 *
	 * @return the candidate
	 */
	public Candidate getCandidate() {
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
		candidateDTO.setCandidateDepartment(CANDIDATE_DEPARTMENT);
		candidateDTO.setCandidateDesignation(CANDIDATE_DESIGNATION);
		candidate.setInterview(getInterview());
		candidate.setInterviewResult(REJECT_CANDIDATE);
		candidate.setFilepath(candidateDTO.getFilepath());
		return candidate;

	}

	/**
	 * Method for creating instace of Interview.
	 *
	 * @return the interview
	 */
	public Interview getInterview() {
		Interview interview = new Interview();
		interview.setInterviewer(null);
		interview.setInterviewId(ID);
		interview.setInterviewDate(AVAILABLE_DATE);
		interview.setInterviewerDepartment(getInterviewer().getInterviewerDepartment());
		interview.setInterviewerDesignation(getInterviewer().getInterviewerDesignation());
		interview.setInterviewRound(INTERVIEW_ROUND);
		interview.setInterviewStatus(INTERVIEW_SCHEDULE);
		interview.setInterviewTime(AVAILABLE_TIME_FROM);
		interview.setFlag(FLAG);

		return interview;

	}

	/**
	 * Testcase of adduser for Interviewer userType.
	 */
	@Test
	public void testaddUserWhenUserTypeIsInterviewer() {

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		assertTrue(adminService.addUser(getInterviewerDTO()));

	}

	/**
	 * Testcase of adduser for Human resource userType.
	 */
	@Test
	public void testaddUserWhenUserTypeIsHumanResource() {

		getHumanResourceUser();

		UserTypeDTO userDTO = getHumanResourceDTO();

		getHumanResource();
		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		when(userRepository.findByUsername(anyString())).thenReturn(null);
		assertTrue(adminService.addUser(userDTO));

	}

	/**
	 * Testcase of adduser for Admin userType.
	 */
	@Test
	public void testaddUserWhenUserTypeIsAdmin() {

		getAdminUser();

		UserTypeDTO userDTO = getAdminDTO();

		getAdmin();
		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		when(userRepository.findByUsername(anyString())).thenReturn(null);
		assertTrue(adminService.addUser(userDTO));

	}

	/**
	 * Testcase of adduser for invalid userType.
	 */
	@Test
	public void testaddUserWhenUserTypeIsInvalid() {

		getAdminUser();

		UserTypeDTO userDTO = getAdminDTO();
		userDTO.setUserType(USER_TYPE_INVALID);
		getAdmin();

		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		when(userRepository.findByUsername(anyString())).thenReturn(null);
		assertFalse(adminService.addUser(userDTO));

	}

	/**
	 * Testcase of adduser for invalid userName.
	 */
	@Test
	public void testaddUserWhenUsernameIsInvalid() {

		getAdminUser();

		UserTypeDTO userDTO = getAdminDTO();

		userDTO.setUsername(USER_NAME_INVALID);

		getAdmin();

		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.FALSE);
		when(userRepository.findByUsername(anyString())).thenReturn(null);
		assertFalse(adminService.addUser(userDTO));

	}

	/**
	 * Testcase of adduser for existing userName in database.
	 */
	@Test
	public void testAddUserWhenUsernameExists() {

		User user = getInterviewerUser();

		UserTypeDTO userDTO = getInterviewerDTO();

		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		when(userRepository.findByUsername(anyString())).thenReturn(user);

		assertFalse(adminService.addUser(userDTO));

	}

	/**
	 * Testcase for retrieving all data from User.
	 */
//	@Test
//	public void testViewAllUser() {
//		List<User> listOfUser = new ArrayList<User>();
//
//		User userHumanResource = getHumanResourceUser();
//
//		User userInterviewer = getInterviewerUser();
//
//		User userAdmin = getAdminUser();
//
//		listOfUser.add(userHumanResource);
//		listOfUser.add(userInterviewer);
//		listOfUser.add(userAdmin);
//
//		when(userRepository.findAll()).thenReturn(listOfUser);
//
//		assertNotNull(adminService.viewAllUser());
//
//	}

	/**
	 * Testcase of update user for Admin userType.
	 */
	@Test
	public void testUpdateUserWhenUserTypeIsAdmin() {
		UserTypeDTO userAdminDTO = getAdminDTO();

		User userAdmin = getAdminUser();

		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(userAdmin);
		when(adminRepository.findByUser(any())).thenReturn(admin);

		assertTrue(adminService.updateUser(ID, userAdminDTO));

	}

	/**
	 * Testcase of update user for Interviewer userType.
	 */
	@Test
	public void testUpdateUserwhenUserTypeIsInterviewer() {
		UserTypeDTO userInterviewerDTO = getInterviewerDTO();

		User userInterviewer = getInterviewerUser();

		Interviewer interviewer = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(userInterviewer);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);

		assertTrue(adminService.updateUser(ID, userInterviewerDTO));

	}

	/**
	 * Testcase of update user for Human resource userType.
	 */
	@Test
	public void testUpdateUserWhenUserTypeIsHumanResource() {
		UserTypeDTO userHumanResourceDTO = getHumanResourceDTO();
		User userHumanResource = getHumanResourceUser();
		HumanResource humanResource = getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(userHumanResource);
		when(humanResourceRepository.findByUser(any())).thenReturn(humanResource);

		assertTrue(adminService.updateUser(ID, userHumanResourceDTO));

	}

	/**
	 * Testcase of update user when userType is invalid.
	 */
	@Test
	public void testUpdateUserWhenUserTypeIsInvalid() {
		UserTypeDTO userHumanResourceDTO = getHumanResourceDTO();

		User userHumanResource = getHumanResourceUser();
		userHumanResource.setUser_type(USER_TYPE_INVALID);

		when(userRepository.findByUserId(anyLong())).thenReturn(userHumanResource);

		assertFalse(adminService.updateUser(ID, userHumanResourceDTO));

	}

	/**
	 * Testcase of update user when User not found from userId.
	 */
	@Test
	public void testUpdateUserWhenUserNotFound() {
		UserTypeDTO userHumanResourceDTO = getHumanResourceDTO();

		getHumanResourceUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);

		assertFalse(adminService.updateUser(ID, userHumanResourceDTO));

	}

	/**
	 * Testcase of delete user for Admin userType.
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsAdmin() {

		User user = getAdminUser();

		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(adminRepository.findByUser(any())).thenReturn(admin);

		assertTrue(adminService.deleteUser(ID));

	}

	/**
	 * Testcase of delete user for Human resource userType.
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsHumanResource() {

		User user = getHumanResourceUser();

		HumanResource humanResource = getHumanResource();
		humanResource.setUser(null);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(humanResourceRepository.findByUser(any())).thenReturn(humanResource);

		assertTrue(adminService.deleteUser(ID));

	}

	/**
	 * Testcase of delete user for Interviewer userType, when interview is assigned
	 * to interviewer and candiate is rejected after interview.
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewerAndInterviewResultIsRejected() {

		User user = getInterviewerUser();

		Interviewer interviewer = getInterviewer();
		interviewer.setFlag(FLAG_ZERO);
		interviewer.setUser(null);

		List<Interview> listOfInterview = new ArrayList<>();

		listOfInterview.add(getInterview());

		List<Candidate> listOfCandidate = new ArrayList<>();

		listOfCandidate.add(getCandidate());

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);
		when(interviewRepository.getInterviewByInterviewerId(any())).thenReturn(listOfInterview);
		when(candidateRepository.getCandidateScheduleInterview()).thenReturn(listOfCandidate);

		assertTrue(adminService.deleteUser(ID));

	}

	/**
	 * Testcase of delete user for Interviewer userType, when interview is assigned
	 * to interviewer and candiate is accepted after interview.
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewerAndInterviewResultIsAccepted() {

		User user = getInterviewerUser();

		Interviewer interviewer = getInterviewer();
		interviewer.setFlag(FLAG_ZERO);
		interviewer.setUser(null);

		List<Interview> listOfInterview = new ArrayList<>();
		Interview interview = getInterview();
		listOfInterview.add(interview);

		List<Candidate> listOfCandidate = new ArrayList<>();
		Candidate candidate = getCandidate();
		candidate.setInterviewResult(ACCEPT_CANDIDATE);

		listOfCandidate.add(candidate);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);
		when(interviewRepository.getInterviewByInterviewerId(any())).thenReturn(listOfInterview);
		when(candidateRepository.getCandidateScheduleInterview()).thenReturn(listOfCandidate);

		assertTrue(adminService.deleteUser(ID));

	}

	/**
	 * Testcase of delete user for Interviewer userType, when interview is not
	 * assigned to interviewer.
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewerAndInterviewIsNotAssigned() {

		User user = getInterviewerUser();

		Interviewer interviewer = getInterviewer();
		interviewer.setFlag(FLAG_ZERO);
		interviewer.setUser(null);

		List<Interview> listOfInterview = new ArrayList<>();
		Interview interview = getInterview();
		interview.setInterviewId(INTERVIEW_ID);
		listOfInterview.add(interview);

		List<Candidate> listOfCandidate = new ArrayList<>();
		Candidate candidate = getCandidate();

		listOfCandidate.add(candidate);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);
		when(interviewRepository.getInterviewByInterviewerId(any())).thenReturn(listOfInterview);
		when(candidateRepository.getCandidateScheduleInterview()).thenReturn(listOfCandidate);

		assertTrue(adminService.deleteUser(ID));

	}

	/**
	 * Testcase of delete user when userId is null.
	 */
	@Test
	public void testDeleteUserWhenUserIdIsNull() {

		getInterviewerUser();

		Interviewer interviewer = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);

		assertFalse(adminService.deleteUser(1));
	}

	/**
	 * Testcase for retrieve data of Admin from userId.
	 */
	@Test
	public void testGetAdminFromUserId() {
		User user = getAdminUser();
		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(adminRepository.findByUser(any())).thenReturn(admin);

		assertNotNull(adminService.getAdminFromUserId(ID));

	}

	/**
	 * Testcase for retrieve data of Admin from user Id, When Admin is not found
	 * from userId.
	 */
	@Test
	public void testGetAdminFromUserIdWhenUserNotFound() {
		getAdminUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);

		assertNull(adminService.getAdminFromUserId(ID));

	}

	/**
	 * Testcase for retrieve data of Admin from userId, When userType is not Admin.
	 */
	@Test
	public void testGetAdminFromUserIdWhenUserTypeIsNotAdmin() {
		User user = getInterviewerUser();
		getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(adminRepository.findByUser(any())).thenReturn(null);
		assertNull(adminService.getAdminFromUserId(ID));

	}

	/**
	 * Testcase for retrieve data of Human resource from userId.
	 */
	@Test
	public void testGetHumanResourceFromUserId() {
		User user = getHumanResourceUser();
		HumanResource humanResource = getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(humanResourceRepository.findByUser(any())).thenReturn(humanResource);

		assertNotNull(adminService.getHumanResourceFromUserId(ID));

	}

	/**
	 * Testcase for retrieve data of Human resource from userId, When Human resource
	 * is not found from userId.
	 */
	@Test
	public void testGetHumanResourceFromUserIdWhenUserNotFound() {
		getHumanResourceUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);

		assertNull(adminService.getHumanResourceFromUserId(ID));

	}

	/**
	 * Testcase for retrieve data of Human resource from userId, When userType is
	 * not Human resource.
	 */
	@Test
	public void testgetHumanResourceFromUserIdWhenUserTypeIsNotInterviewer() {
		User user = getInterviewerUser();
		getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(humanResourceRepository.findByUser(any())).thenReturn(null);

		assertNull(adminService.getHumanResourceFromUserId(ID));

	}

	/**
	 * Testcase for retrieve data of Interviewer from userId.
	 */
	@Test
	public void testGetInterviewerFromUserId() {
		User user = getInterviewerUser();
		Interviewer interviewer = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);

		assertNotNull(adminService.getInterviewerFromUserId(ID));

	}

	/**
	 * Testcase for retrieve data of Interviewer from userId, When Interviewer is
	 * not found from userId.
	 */
	@Test
	public void testGetInterviewerFromUserIdWhenUserNotFound() {
		getInterviewerUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);

		assertNull(adminService.getInterviewerFromUserId(ID));

	}

	/**
	 * Testcase for retrieve data of Interviewer from userId, When userType is not
	 * Interviewer.
	 */
	@Test
	public void testGetInterviewerFromUserIdWhenUserTypeIsNotInterviewer() {
		User user = getAdminUser();
		getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(interviewerRepository.findByUser(any())).thenReturn(null);

		assertNull(adminService.getInterviewerFromUserId(ID));

	}

	/**
	 * Test case for update details of logged in Admin.
	 */
	@Test
	public void testUpdateAdminProfile() {
		UserTypeDTO userAdminDTO = getAdminDTO();

		User userAdmin = getAdminUser();

		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(userAdmin);
		when(adminRepository.findByUser(any())).thenReturn(admin);

		assertNotNull(adminService.updateAdminProfile(ID, userAdminDTO));

	}

	/**
	 * Test case for update details of logged in Admin,when user not found from
	 * userId.
	 */
	@Test
	public void testUpdateAdminProfileWhenUserNotFound() {
		UserTypeDTO userAdminDTO = getAdminDTO();

		getAdminUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);

		assertNull(adminService.updateAdminProfile(ID, userAdminDTO));

	}

	/**
	 * Test case for update details of logged in Admin,when userType is not Admin.
	 */
	@Test
	public void testUpdateAdminProfileWhenUserTypeIsNotAdmin() {
		UserTypeDTO userAdminDTO = getAdminDTO();

		User userHumanResource = getHumanResourceUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(userHumanResource);

		assertNull(adminService.updateAdminProfile(ID, userAdminDTO));

	}

	/**
	 * Testcase for retrieving all data from Admin.
	 */
	@Test
	public void testViewAllAdmin() {
		List<Admin> listOfAdmin = new ArrayList<Admin>();

		Admin admin = getAdmin();

		listOfAdmin.add(admin);

		when(adminRepository.findAll()).thenReturn(listOfAdmin);

		assertNotNull(adminService.viewAllAdmin());

	}

	/**
	 * Testcase for retrieving all data from Admin , when Admin table is empty.
	 */
	@Test
	public void testViewAllAdminWhenAdminIsNull() {
		List<Admin> listOfAdmin = new ArrayList<Admin>();

		Admin admin = getAdmin();

		listOfAdmin.add(admin);
		when(adminRepository.findAll()).thenReturn(null);

		assertNull(adminService.viewAllAdmin());

	}

	/**
	 * Testcase for retrieving all data from HumanResource.
	 */
	@Test
	public void testViewAllHumanResource() {
		List<HumanResource> listOfHumanResource = new ArrayList<HumanResource>();

		HumanResource humanResource = getHumanResource();

		listOfHumanResource.add(humanResource);

		when(humanResourceRepository.findAll()).thenReturn(listOfHumanResource);

		assertNotNull(adminService.viewAllHumanResource());

	}

	/**
	 * Testcase for retrieving all data from HumanResource , when HumanResource
	 * table is empty.
	 */
	@Test
	public void testViewAllHumanResourceWhenHumanResourceIsNull() {
		List<HumanResource> listOfHumanResource = new ArrayList<HumanResource>();

		HumanResource humanResource = getHumanResource();

		listOfHumanResource.add(humanResource);

		when(humanResourceRepository.findAll()).thenReturn(null);

		assertNull(adminService.viewAllHumanResource());
	}

	/**
	 * Testcase for retrieving all data from Interviewer.
	 */
	@Test
	public void testViewAllInterviewer() {
		List<Interviewer> listOfInterviewer = new ArrayList<Interviewer>();

		Interviewer interviewer = getInterviewer();

		listOfInterviewer.add(interviewer);

		when(interviewerRepository.findAll()).thenReturn(listOfInterviewer);

		assertNotNull(adminService.viewAllInterviewer());

	}

	/**
	 * Testcase for retrieving all data from Interviewer , when Interviewer table is
	 * empty.
	 */
	@Test
	public void testViewAllInterviewerWhenInterviewerIsNull() {
		List<Interviewer> listOfInterviewer = new ArrayList<Interviewer>();

		Interviewer interviewer = getInterviewer();

		listOfInterviewer.add(interviewer);

		when(interviewerRepository.findAll()).thenReturn(null);

		assertNull(adminService.viewAllInterviewer());
	}

}