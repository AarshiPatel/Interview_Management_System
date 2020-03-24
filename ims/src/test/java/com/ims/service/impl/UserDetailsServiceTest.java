package com.ims.service.impl;

import static org.junit.Assert.assertNotNull;
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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ims.dao.CandidateRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.UserDetailsRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.CandidateDTO;
import com.ims.dto.UserDetailsDTO;
import com.ims.dto.response.UserDetailsResponseDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.Interview;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.exception.ConflictException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.mapper.UserMapper;
import com.ims.service.UserDetailsServiceInterface;
import com.ims.util.NameUtil;
import com.ims.util.PasswordUtil;


/**
 * The Class UserDetailsServiceTest.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

public class UserDetailsServiceTest {

	/** The user details service impl. */
	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;

	/** The adminserviceinterface. */
	@Mock
	private UserDetailsServiceInterface userDetailsServiceInterface;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/** The name util. */
	@Mock
	private NameUtil nameUtil;

	/** The password util. */
	@Mock
	private PasswordUtil passwordUtil;

	/** The user mapper. */
	@Mock
	private UserMapper userMapper;

	/** The admin repository. */
	@Mock
	private UserDetailsRepository userDetailsRepository;

	/** The candidate repository. */
	@Mock
	private CandidateRepository candidateRepository;

	/** The interview repository. */
	@Mock
	private InterviewRepository interviewRepository;

	/** The Constant emailId. */
	private final static String USERNAME = "vahak15@gmail.com";

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

	/** The Constant USER_MANAGER. */
	private final static String USER_MANAGER = "Manager";

	/** The Constant interviewer_department. */
	private final static String INTERVIEWER_DEPARTMENT = "PES";

	/** The Constant interviewer_designation. */
	private final static String INTERVIEWER_DESIGNATION = "Senior Engineer";

	/** The Constant INTERVIEWER_GROUP. */
	private final static String INTERVIEWER_GROUP = "PES";

	/** The Constant interviewer_department. */
	private final static String MANAGER_DEPARTMENT = "Digital";

	/** The Constant MANAGER_GROUP. */
	private final static String MANAGER_GROUP = "PES";

	/** The Constant interviewer_designation. */
	private final static String MANAGER_DESIGNATION = "Technical Manager";

	/** The Constant humanresource_department. */
	private final static String HUMANRESOURCE_DEPARTMENT = "humanresource";

	/** The Constant humanresource_designation. */
	private final static String HUMANRESOURCE_DESIGNATION = "Fresher";

	/** The Constant HUMANRESOURCE_GROUP. */
	private final static String HUMANRESOURCE_GROUP = "Humanresource";

	/** The Constant admin_department. */
	private final static String ADMIN_DEPARTMENT = "admin";

	/** The Constant admin_designation. */
	private final static String ADMIN_DESIGNATION = "Fresher";

	/** The Constant ADMIN_GROUP. */
	private final static String ADMIN_GROUP = "Admin";

	/** The Constant experience. */
	private final static double EXPERIENCE = 1;

	/** The Constant flag. */
	private final static int FLAG = 1;

	/** The Constant flag. */
	private final static int FLAG_ZERO = 0;

	/** The Constant mobilenumber. */
	private final static String MOBILE_NUMBER = "9876543210";

	/** The Constant userId. */
	private final static long ID = Long.parseLong("1");

	/** The Constant date. */
	private final static LocalDate DOB = LocalDate.of(1996, 5, 15);

	/** The Constant address. */
	private final static String ADDRESS = "Ahmedabad";

	/** The Constant availableDate. */
	private final static LocalDate AVAILABLE_DATE = LocalDate.of(2020, 02, 24);

	/** The Constant availableTimeFrom. */
	private final static LocalTime AVAILABLE_TIME_FROM = LocalTime.of(12, 00);

	/** The Constant availableTimeTo. */
	private final static LocalTime AVAILABLE_TIME_TO = LocalTime.of(13, 00);

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
	 * @return the interviewer DTO
	 */
	public UserDetailsDTO getInterviewerDTO() {

		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setUsername(USERNAME);
		userDetailsDTO.setPassword(PASSWORD);
		userDetailsDTO.setUser_type(USER_INTERVIEWER);
		userDetailsDTO.setUserGroup(INTERVIEWER_GROUP);
		userDetailsDTO.setDepartment(INTERVIEWER_DEPARTMENT);
		userDetailsDTO.setDesignation(INTERVIEWER_DESIGNATION);
		userDetailsDTO.setDateOfBirth(DOB);
		userDetailsDTO.setExperience(EXPERIENCE);
		userDetailsDTO.setContactNumber(MOBILE_NUMBER);
		userDetailsDTO.setName(NAME);
		userDetailsDTO.setAvailableDate(AVAILABLE_DATE);
		userDetailsDTO.setAvailableTimeFrom(AVAILABLE_TIME_FROM);
		userDetailsDTO.setAvailableTimeTo(AVAILABLE_TIME_TO);
		userDetailsDTO.setActiveFlag(FLAG);
		return userDetailsDTO;
	}

	/**
	 * Method for creating instace of UserTypeDTO for Admin userType .
	 *
	 * @return the admin DTO
	 */
	public UserDetailsDTO getAdminDTO() {

		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setUsername(USERNAME);
		userDetailsDTO.setPassword(PASSWORD);
		userDetailsDTO.setUser_type(USER_ADMIN);
		userDetailsDTO.setUserGroup(ADMIN_GROUP);

		userDetailsDTO.setDepartment(ADMIN_DEPARTMENT);
		userDetailsDTO.setDesignation(ADMIN_DESIGNATION);
		userDetailsDTO.setDateOfBirth(DOB);
		userDetailsDTO.setExperience(EXPERIENCE);
		userDetailsDTO.setContactNumber(MOBILE_NUMBER);
		userDetailsDTO.setName(NAME);
		return userDetailsDTO;
	}

	/**
	 * Method for creating instace of UserTypeDTO for Human resource userType .
	 *
	 * @return the human resourceDTO
	 */
	public UserDetailsDTO getHumanResourceDTO() {

		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setUsername(USERNAME);
		userDetailsDTO.setPassword(PASSWORD);
		userDetailsDTO.setUser_type(USER_HUMANRESOURCE);
		userDetailsDTO.setUserGroup(HUMANRESOURCE_GROUP);

		userDetailsDTO.setDepartment(HUMANRESOURCE_DEPARTMENT);
		userDetailsDTO.setDesignation(HUMANRESOURCE_DESIGNATION);
		userDetailsDTO.setDateOfBirth(DOB);
		userDetailsDTO.setExperience(EXPERIENCE);
		userDetailsDTO.setContactNumber(MOBILE_NUMBER);
		userDetailsDTO.setName(NAME);
		return userDetailsDTO;
	}

	/**
	 * Method for creating instace of UserTypeDTO for Manager userType .
	 *
	 * @return the human resourceDTO
	 */
	public UserDetailsDTO getManagerDTO() {

		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setUsername(USERNAME);
		userDetailsDTO.setPassword(PASSWORD);
		userDetailsDTO.setUser_type(USER_MANAGER);
		userDetailsDTO.setUserGroup(MANAGER_GROUP);

		userDetailsDTO.setDepartment(MANAGER_DEPARTMENT);
		userDetailsDTO.setDesignation(MANAGER_DESIGNATION);
		userDetailsDTO.setDateOfBirth(DOB);
		userDetailsDTO.setExperience(EXPERIENCE);
		userDetailsDTO.setContactNumber(MOBILE_NUMBER);
		userDetailsDTO.setName(NAME);
		return userDetailsDTO;
	}

	/**
	 * Gets the interviewer user.
	 *
	 * @return the interviewer user
	 */
	public User getInterviewerUser() {
		User user = new User();
		user.setUserId(ID);
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_INTERVIEWER);
		return user;
	}

	/**
	 * Gets the user response DTO.
	 *
	 * @return the user response DTO
	 */
	public UserResponseDTO getUserResponseDTO() {
		UserResponseDTO user = new UserResponseDTO();
		user.setUserId(ID);
		user.setUsername(USERNAME);
		user.setUser_type(USER_INTERVIEWER);
		return user;
	}

	/**
	 * Method for creating instace of User for Admin userType.
	 *
	 * @return the admin user
	 */
	public User getAdminUser() {
		User user = new User();
		user.setUserId(ID);
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_ADMIN);
		return user;
	}

	/**
	 * Method for creating instace of User for Manager userType.
	 *
	 * @return the admin user
	 */
	public User getManagerUser() {
		User user = new User();
		user.setUserId(ID);
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_MANAGER);
		return user;
	}

	/**
	 * Method for creating instace of User for Human resource userType.
	 *
	 * @return the human resource user
	 */
	public User getHumanResourceUser() {
		User user = new User();
		user.setUserId(ID);
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		user.setUser_type(USER_HUMANRESOURCE);
		return user;
	}

	/**
	 * Method for creating instace of Interviewer.
	 *
	 * @return the interviewer
	 */
	public UserDetails getInterviewer() {

		UserDetails interviewer = new UserDetails();
		interviewer.setUserDetailsId(ID);
		interviewer.setDepartment(INTERVIEWER_DEPARTMENT);
		interviewer.setDesignation(INTERVIEWER_DESIGNATION);
		interviewer.setUserGroup(INTERVIEWER_GROUP);
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
	 * Method for creating instace of Interviewer.
	 *
	 * @return the interviewer
	 */
	public UserDetailsResponseDTO getInterviewerResponseDTO() {

		UserDetailsResponseDTO interviewer = new UserDetailsResponseDTO();
		interviewer.setDepartment(INTERVIEWER_DEPARTMENT);
		interviewer.setDesignation(INTERVIEWER_DESIGNATION);
		interviewer.setDateOfBirth(DOB);
		interviewer.setUserGroup(INTERVIEWER_GROUP);

		interviewer.setExperience(EXPERIENCE);
		interviewer.setContactNumber(MOBILE_NUMBER);
		interviewer.setAvailableDate(AVAILABLE_DATE);
		interviewer.setAvailableTimeFrom(AVAILABLE_TIME_FROM);
		interviewer.setAvailableTimeTo(AVAILABLE_TIME_TO);
		interviewer.setUser(getInterviewerUser());
		return interviewer;

	}

	/**
	 * Method for creating instace of Admin.
	 *
	 * @return the admin
	 */
	public UserDetails getAdmin() {

		UserDetails admin = new UserDetails();
		User user = getAdminUser();
		admin.setUserDetailsId(ID);
		admin.setDepartment(ADMIN_DEPARTMENT);
		admin.setDesignation(ADMIN_DESIGNATION);
		admin.setUserGroup(ADMIN_GROUP);

		admin.setDateOfBirth(DOB);
		admin.setExperience(EXPERIENCE);
		admin.setContactNumber(MOBILE_NUMBER);
		admin.setUser(user);
		return admin;
	}

	/**
	 * Gets the admin response DTO.
	 *
	 * @return the admin response DTO
	 */
	public UserDetailsResponseDTO getAdminResponseDTO() {

		UserDetailsResponseDTO admin = new UserDetailsResponseDTO();
		User user = getAdminUser();
		admin.setDepartment(ADMIN_DEPARTMENT);
		admin.setUserGroup(ADMIN_GROUP);

		admin.setDesignation(ADMIN_DESIGNATION);
		admin.setDateOfBirth(DOB);
		admin.setExperience(EXPERIENCE);
		admin.setContactNumber(MOBILE_NUMBER);
		admin.setUser(user);
		return admin;
	}

	/**
	 * Method for creating instace of Human resource.
	 *
	 * @return the human resource
	 */
	public UserDetails getHumanResource() {

		UserDetails humanResource = new UserDetails();
		humanResource.setUserDetailsId(ID);
		humanResource.setDepartment(HUMANRESOURCE_DEPARTMENT);
		humanResource.setDesignation(HUMANRESOURCE_DESIGNATION);
		humanResource.setUserGroup(HUMANRESOURCE_GROUP);

		humanResource.setDateOfBirth(DOB);
		humanResource.setExperience(EXPERIENCE);
		humanResource.setContactNumber(MOBILE_NUMBER);
		humanResource.setUser(getHumanResourceUser());
		return humanResource;
	}

	/**
	 * Gets the human resource response DTO.
	 *
	 * @return the human resource response DTO
	 */
	public UserDetailsResponseDTO getHumanResourceResponseDTO() {

		UserDetailsResponseDTO humanResource = new UserDetailsResponseDTO();
		humanResource.setDepartment(HUMANRESOURCE_DEPARTMENT);
		humanResource.setDesignation(HUMANRESOURCE_DESIGNATION);
		humanResource.setDateOfBirth(DOB);
		humanResource.setUserGroup(HUMANRESOURCE_GROUP);

		humanResource.setExperience(EXPERIENCE);
		humanResource.setContactNumber(MOBILE_NUMBER);
		humanResource.setUser(getHumanResourceUser());
		return humanResource;
	}

	/**
	 * Gets the manager response DTO.
	 *
	 * @return the manager response DTO
	 */
	public UserDetails getManager() {

		UserDetails manager = new UserDetails();
		manager.setUserDetailsId(ID);
		manager.setDepartment(MANAGER_DEPARTMENT);
		manager.setDesignation(MANAGER_DESIGNATION);
		manager.setUserGroup(MANAGER_GROUP);

		manager.setDateOfBirth(DOB);
		manager.setExperience(EXPERIENCE);
		manager.setContactNumber(MOBILE_NUMBER);
		manager.setUser(getHumanResourceUser());
		return manager;
	}

	/**
	 * Gets the human resource response DTO.
	 *
	 * @return the human resource response DTO
	 */
	public UserDetailsResponseDTO getManagerResponseDTO() {

		UserDetailsResponseDTO manager = new UserDetailsResponseDTO();
		manager.setDepartment(MANAGER_DEPARTMENT);
		manager.setDesignation(MANAGER_DESIGNATION);
		manager.setUserGroup(MANAGER_GROUP);

		manager.setDateOfBirth(DOB);
		manager.setExperience(EXPERIENCE);
		manager.setContactNumber(MOBILE_NUMBER);
		manager.setUser(getHumanResourceUser());
		return manager;
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
		candidateDTO.setEmailId(USERNAME);
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
		interview.setInterviewer(getInterviewer());
		interview.setInterviewId(ID);
		interview.setInterviewDate(AVAILABLE_DATE);
		interview.setInterviewerDepartment(getInterviewer().getDepartment());
		interview.setInterviewerDesignation(getInterviewer().getDesignation());
		interview.setInterviewRound(INTERVIEW_ROUND);
		interview.setInterviewStatus(INTERVIEW_SCHEDULE);
		interview.setInterviewTime(AVAILABLE_TIME_FROM);
		interview.setFlag(FLAG);

		return interview;

	}

	/**
	 * Testcase of adduser for Interviewer userType.
	 *
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity the unprocessable entity @ the exception
	 */
	@Test
	public void testAddUserWhenUserTypeIsInterviewer() throws ConflictException, UnprocessableEntity {

		UserDetailsDTO userDetailsDTO = getInterviewerDTO();

		getInterviewerUser();

		getInterviewer();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		when(passwordUtil.encryptPassword(anyString())).thenReturn(anyString());

		assertNotNull(userDetailsServiceImpl.addUser(userDetailsDTO));
	}

	/**
	 * Adds the user when user type is human resource.
	 *
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity the unprocessable entity @ the exception
	 */
	@Test
	public void testAddUserWhenUserTypeIsHumanResource() throws ConflictException, UnprocessableEntity {

		UserDetailsDTO userDetailsDTO = getHumanResourceDTO();

		getHumanResourceUser();

		getHumanResource();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		when(passwordUtil.encryptPassword(anyString())).thenReturn(anyString());

		assertNotNull(userDetailsServiceImpl.addUser(userDetailsDTO));
	}

	/**
	 * Adds the user when user type is admin.
	 *
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity the unprocessable entity @ the exception
	 */
	@Test
	public void testAddUserWhenUserTypeIsAdmin() throws ConflictException, UnprocessableEntity {

		UserDetailsDTO userDetailsDTO = getAdminDTO();

		getAdminUser();

		getAdmin();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		when(passwordUtil.encryptPassword(anyString())).thenReturn(anyString());

		assertNotNull(userDetailsServiceImpl.addUser(userDetailsDTO));
	}

	/**
	 * Test add user when username already exists.
	 *
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	@Test(expected = ConflictException.class)
	public void testAddUserWhenUsernameAlreadyExists() throws ConflictException, UnprocessableEntity {

		UserDetailsDTO userDetailsDTO = getAdminDTO();

		User user = getAdminUser();

		getAdmin();

		when(userRepository.findByUsername(anyString())).thenReturn(user);
		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.TRUE);
		when(passwordUtil.encryptPassword(anyString())).thenReturn(anyString());

		userDetailsServiceImpl.addUser(userDetailsDTO);
	}

	/**
	 * Test add user when username is invalid.
	 *
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testAddUserWhenUsernameIsInvalid() throws ConflictException, UnprocessableEntity {

		UserDetailsDTO userDetailsDTO = getAdminDTO();

		getAdminUser();

		getAdmin();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(nameUtil.isValidName(anyString())).thenReturn(Boolean.FALSE);
		when(passwordUtil.encryptPassword(anyString())).thenReturn(anyString());

		userDetailsServiceImpl.addUser(userDetailsDTO);
	}

	/**
	 * Test update user when user type is interviewer.
	 *
	 * @throws ConflictException         the conflict exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testUpdateUserWhenUserTypeIsInterviewer() throws ConflictException, ResourceNotFoundException {

		UserDetailsDTO userDetailsDTO = getInterviewerDTO();

		User user = getInterviewerUser();

		UserDetails userDetails = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		assertNotNull(userDetailsServiceImpl.updateUser(ID, userDetailsDTO));
	}

	/**
	 * Test update user when user type is admin.
	 *
	 * @throws ConflictException         the conflict exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testUpdateUserWhenUserTypeIsAdmin() throws ConflictException, ResourceNotFoundException {

		UserDetailsDTO userDetailsDTO = getAdminDTO();

		User user = getAdminUser();

		UserDetails userDetails = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		assertNotNull(userDetailsServiceImpl.updateUser(ID, userDetailsDTO));
	}

	/**
	 * Test update user when user type is human resource.
	 *
	 * @throws ConflictException         the conflict exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testUpdateUserWhenUserTypeIsHumanResource() throws ConflictException, ResourceNotFoundException {

		UserDetailsDTO userDetailsDTO = getHumanResourceDTO();

		User user = getHumanResourceUser();

		UserDetails userDetails = getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		assertNotNull(userDetailsServiceImpl.updateUser(ID, userDetailsDTO));
	}

	/**
	 * Test update user when user not found in user details.
	 *
	 * @throws ConflictException         the conflict exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateUserWhenUserNotFoundInUserDetails() throws ConflictException, ResourceNotFoundException {

		UserDetailsDTO userDetailsDTO = getHumanResourceDTO();

		User user = getHumanResourceUser();

		getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(null);

		userDetailsServiceImpl.updateUser(ID, userDetailsDTO);
	}

	/**
	 * Test update user when user not found from user id.
	 *
	 * @throws ConflictException         the conflict exception
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateUserWhenUserNotFoundFromUserId() throws ConflictException, ResourceNotFoundException {

		UserDetailsDTO userDetailsDTO = getHumanResourceDTO();

		getHumanResourceUser();

		UserDetails userDetails = getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		userDetailsServiceImpl.updateUser(ID, userDetailsDTO);
	}

	/**
	 * Test delete user when user type is interviewer.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewer() throws ResourceNotFoundException {

		User user = getInterviewerUser();

		UserDetails userDetails = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		assertNotNull(userDetailsServiceImpl.deleteUser(ID));
	}

	/**
	 * Testcase of delete user for Interviewer userType, when interview is assigned
	 * to interviewer and candiate is rejected after interview.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewerAndInterviewResultIsRejected() throws ResourceNotFoundException {

		User user = getInterviewerUser();

		UserDetails interviewer = getInterviewer();

		List<Interview> listOfInterview = new ArrayList<>();
		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		listOfInterview.add(interview);

		List<Candidate> listOfCandidate = new ArrayList<>();

		Candidate candidate = getCandidate();
		candidate.setInterview(interview);
		candidate.setInterviewResult(REJECT_CANDIDATE);

		listOfCandidate.add(candidate);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(interviewer);
		when(interviewRepository.getInterviewByUserId(any())).thenReturn(listOfInterview);
		when(candidateRepository.getCandidateScheduleInterview()).thenReturn(listOfCandidate);

		interviewer.setActiveFlag(FLAG_ZERO);
		interviewer.setUser(null);

		assertNotNull(userDetailsServiceImpl.deleteUser(ID));

	}

	/**
	 * Testcase of delete user for Interviewer userType, when interview is assigned
	 * to interviewer and candiate is accepted after interview.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewerAndInterviewResultIsAccepted() throws ResourceNotFoundException {
		User user = getInterviewerUser();

		UserDetails interviewer = getInterviewer();

		List<Interview> listOfInterview = new ArrayList<>();
		Interview interview = getInterview();
		interview.setInterviewer(interviewer);

		listOfInterview.add(interview);

		List<Candidate> listOfCandidate = new ArrayList<>();

		Candidate candidate = getCandidate();
		candidate.setInterview(interview);
		candidate.setInterviewResult(ACCEPT_CANDIDATE);

		listOfCandidate.add(candidate);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(interviewer);
		when(interviewRepository.getInterviewByUserId(any())).thenReturn(listOfInterview);
		when(candidateRepository.getCandidateScheduleInterview()).thenReturn(listOfCandidate);

		interviewer.setActiveFlag(FLAG_ZERO);
		interviewer.setUser(null);

		assertNotNull(userDetailsServiceImpl.deleteUser(ID));

	}

	/**
	 * Test delete user when user type is human resource.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsHumanResource() throws ResourceNotFoundException {

		User user = getHumanResourceUser();

		UserDetails userDetails = getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		assertNotNull(userDetailsServiceImpl.deleteUser(ID));
	}

	/**
	 * Test delete user when user type is admin.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsAdmin() throws ResourceNotFoundException {

		User user = getAdminUser();

		UserDetails userDetails = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		assertNotNull(userDetailsServiceImpl.deleteUser(ID));
	}

	/**
	 * Test delete user when user not found.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testDeleteUserWhenUserNotFound() throws ResourceNotFoundException {

		getInterviewerUser();

		UserDetails userDetails = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		userDetailsServiceImpl.deleteUser(ID);
	}

	/**
	 * Test update admin profile.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testUpdateAdminProfile() throws ResourceNotFoundException {

		UserDetailsDTO userDetailsDTO = getAdminDTO();

		User user = getAdminUser();

		UserDetails userDetails = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);

		assertNotNull(userDetailsServiceImpl.updateAdminProfile(ID, userDetailsDTO));
	}

	/**
	 * Test update admin profile when user not found.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateAdminProfileWhenUserNotFound() throws ResourceNotFoundException {

		UserDetailsDTO userDetailsDTO = getAdminDTO();

		getAdminUser();

		UserDetails userDetails = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(null);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);
		userDetailsServiceImpl.updateAdminProfile(ID, userDetailsDTO);

	}

	/**
	 * Test view all user.
	 */
	@Test
	public void testViewAllUser() {

		getUserResponseDTO();
		assertNotNull(userDetailsServiceImpl.viewAllUser());

	}

	/**
	 * Test view all admin.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testViewAllAdmin() throws ResourceNotFoundException {

		getAdminResponseDTO();
		assertNotNull(userDetailsServiceImpl.viewAllAdmin());

	}

	/**
	 * Test view all interviewer.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testViewAllInterviewer() throws ResourceNotFoundException {

		getInterviewerResponseDTO();
		assertNotNull(userDetailsServiceImpl.viewAllInterviewer());

	}

	/**
	 * Test view all human resource.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testViewAllHumanResource() throws ResourceNotFoundException {

		getHumanResourceResponseDTO();
		assertNotNull(userDetailsServiceImpl.viewAllHumanResource());

	}

	/**
	 * Test view all manager.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testViewAllManager() throws ResourceNotFoundException {

		getManagerResponseDTO();
		assertNotNull(userDetailsServiceImpl.viewAllManager());

	}

	/**
	 * Test get user details from user id.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testGetUserDetailsFromUserId() throws ResourceNotFoundException {
		User user = getAdminUser();
		UserDetails userDetails = getAdmin();
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);
		assertNotNull(userDetailsServiceImpl.getUserDetailsFromUserId(1));
	}

	/**
	 * Test get user details from user id when user not found.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testGetUserDetailsFromUserIdWhenUserNotFound() throws ResourceNotFoundException {
		getAdminUser();
		UserDetails userDetails = getAdmin();
		when(userRepository.findByUserId(anyLong())).thenReturn(null);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);
		userDetailsServiceImpl.getUserDetailsFromUserId(1);
	}

}
