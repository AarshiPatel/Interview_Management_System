package com.ims.controller;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import com.ims.service.AdminServiceInterface;

/**
 * The Class AdminControllerTest.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminControllerTest {

	/** The adminController. */
	@InjectMocks
	private AdminController adminController;

	/** The adminserviceinterface. */
	@Mock
	private AdminServiceInterface adminServiceInterface;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/** The admin repository. */
	@Mock
	private AdminRepository adminRepository;

	/** The interviewer repository. */
	@Mock
	private InterviewerRepository interviewerRepository;

	/** The human resourse repository. */
	@Mock
	private HumanResourseRepository humanResourceRepository;

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

	/** The Constant interviewer_department. */
	private final static String INTERVIEWER_DEPARTMENT = "PES";

	/** The Constant interviewer_designation. */
	private final static String INTERVIWER_DESIGNATION = "Fresher";

	/** The Constant humanresource_department. */
	private final static String HUMANRESOURCE_DEPARTMENT = "humanresource";

	/** The Constant humanresource_designation. */
	private final static String HUMANRESOURCE_DESIGNATION = "Fresher";

	/** The Constant admin_department. */
	private final static String ADMIN_DEPARTMENT = "admin";

	/** The Constant admin_designation. */
	private final static String ADMIN_DESIGNATION = "Fresher";

	/** The Constant experience. */
	private final static double EXPERIENCE = 1;

	/** The Constant flag. */
	private final static int FLAG = 1;

	/** The Constant flagZero. */
	private final static int FLAG_ZERO = 0;

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

	// private final static Long l1 = Id;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).addPlaceholderValue("settings.cors_origin","https://localhost:3000.").build();
	}

	/**
	 * Method for creating instace of UserTypeDTO for Interviewer userType .
	 *
	 * @return the interviewer DTO
	 */
	public UserTypeDTO getInterviewerDTO() {

		UserTypeDTO userDTO = new UserTypeDTO();
		userDTO.setUsername(USERNAME);
		userDTO.setPassword(PASSWORD);
		userDTO.setUserType(USER_INTERVIEWER);
		userDTO.setDepartment(INTERVIEWER_DEPARTMENT);
		userDTO.setDesignation(INTERVIWER_DESIGNATION);
		userDTO.setDob(DOB);
		userDTO.setExperience(EXPERIENCE);
		userDTO.setMobilenumber(MOBILE_NUMBER);
		userDTO.setName(NAME);
		userDTO.setAvailableDate(AVAILABLE_DATE);
		userDTO.setAvailableTimeFrom(AVAILABLE_TIME_FROM);
		userDTO.setAvailableTimeTo(AVAILABLE_TIME_TO);
		userDTO.setFlag(FLAG);
		return userDTO;
	}

	/**
	 * Method for creating instace of UserTypeDTO for Admin userType .
	 *
	 * @return the admin DTO
	 */
	public UserTypeDTO getAdminDTO() {

		UserTypeDTO userDTO = new UserTypeDTO();
		userDTO.setUsername(USERNAME);
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
	 * @return the human resourceDTO
	 */
	public UserTypeDTO getHumanResourceDTO() {

		UserTypeDTO userDTO = new UserTypeDTO();
		userDTO.setUsername(USERNAME);
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
	public Interviewer getInterviewer() {

		Interviewer interviewer = new Interviewer();
		interviewer.setInterviewerId(ID);
		interviewer.setInterviewerDepartment(INTERVIEWER_DEPARTMENT);
		interviewer.setInterviewerDesignation(INTERVIWER_DESIGNATION);
		interviewer.setInterviewerDob(DOB);
		interviewer.setInterviewerExperience(EXPERIENCE);
		interviewer.setInterviewerMobilenumber(MOBILE_NUMBER);
		interviewer.setFlag(FLAG);
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
	public Admin getAdmin() {

		Admin admin = new Admin();
		User user = getAdminUser();
		admin.setAdminId(ID);
		admin.setAdminDepartment(ADMIN_DEPARTMENT);
		admin.setAdminDesignation(ADMIN_DESIGNATION);
		admin.setAdminDob(DOB);
		admin.setAdminExperience(EXPERIENCE);
		admin.setAdminMobilenumber(MOBILE_NUMBER);
		admin.setUser(user);
		return admin;
	}

	/**
	 * Method for creating instace of Human resource.
	 *
	 * @return the human resource
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
	//	interview.setInterviewer(getInterviewer());
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
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addUserWhenUserTypeIsInterviewer() throws Exception {

		UserTypeDTO userDTO = getInterviewerDTO();

		getInterviewerUser();

		getInterviewer();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(adminServiceInterface.addUser(any())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/adduser").content(inputInJson).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Testcase of adduser for Admin userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addUserWhenUserTypeIsAdmin() throws Exception {

		UserTypeDTO userDTO = getAdminDTO();

		getAdminUser();

		getAdmin();
		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(adminServiceInterface.addUser(any())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/adduser").accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Testcase of adduser for Human resource userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addUserWhenUserTypeIsHumanResource() throws Exception {

		UserTypeDTO userDTO = getHumanResourceDTO();

		getHumanResourceUser();

		getHumanResource();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(adminServiceInterface.addUser(any())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/adduser").accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Testcase of adduser when Interface returns false.
	 *
	 * @throws Exception the exception
	 */

	@Test
	public void addUserWhenInterfaceReturnFalse() throws Exception {

		UserTypeDTO userDTO = getHumanResourceDTO();

		User user = getHumanResourceUser();
		getHumanResource();

		when(userRepository.findByUsername(anyString())).thenReturn(user);
		when(adminServiceInterface.addUser(any())).thenReturn(false);

		String inputInJson = this.mapToJson(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/adduser").accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

	}

//	/**
//	 * Testcase for retrieving all data from User.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void viewAllUser() throws Exception {
//
//		List<User> listOfUser = new ArrayList<User>();
//
//		User userHumanResource = getHumanResourceUser();
//		User userInterviewer = getInterviewerUser();
//		User userAdmin = getAdminUser();
//
//		listOfUser.add(userHumanResource);
//		listOfUser.add(userInterviewer);
//		listOfUser.add(userAdmin);
//
//		when(userRepository.findAll()).thenReturn(listOfUser);
//		when(adminServiceInterface.viewAllUser()).thenReturn(listOfUser);
//
//		String inputInJson = this.mapToJson(listOfUser);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/viewalluser").accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//
//	}
//
//	/**
//	 * Testcase for retrieving all data from User,When User is not found
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void viewAllUserReturnNull() throws Exception {
//
//		List<User> listOfUser = new ArrayList<User>();
//
//		getHumanResourceUser();
//		getInterviewerUser();
//		getAdminUser();
//
//		when(userRepository.findAll()).thenReturn(listOfUser);
//		when(adminServiceInterface.viewAllUser()).thenReturn(listOfUser);
//
//		String inputInJson = this.mapToJson(listOfUser);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/viewalluser").accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//
//	}

	/**
	 * Testcase of update user for Interviewer userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateUserWhenUserTypeIsInterviewer() throws Exception {

		UserTypeDTO userInterviewerDTO = getInterviewerDTO();

		User userInterviewer = getInterviewerUser();

		Interviewer interviewer = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(userInterviewer);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);
		when(adminServiceInterface.updateUser(anyLong(), any())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(userInterviewerDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateuser/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Testcase of update user for Admin userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateUserWhenUserTypeIsAdmin() throws Exception {

		UserTypeDTO userAdminDTO = getAdminDTO();

		User userAdmin = getAdminUser();

		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(userAdmin);
		when(adminRepository.findByUser(any())).thenReturn(admin);
		when(adminServiceInterface.updateUser(anyLong(), any())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(userAdminDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateuser/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Testcase of update user for Human resource userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateUserWhenUserTypeIsHumanResource() throws Exception {

		UserTypeDTO userHumanResourceDTO = getHumanResourceDTO();

		User userHumanResource = getHumanResourceUser();

		HumanResource humanResource = getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(userHumanResource);
		when(humanResourceRepository.findByUser(any())).thenReturn(humanResource);
		when(adminServiceInterface.updateUser(anyLong(), any())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(userHumanResourceDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateuser/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Testcase of update user when Interface returns false.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateUserWhenInterfaceReturnFalse() throws Exception {

		UserTypeDTO userInterviewerDTO = getInterviewerDTO();

		User userInterviewer = getInterviewerUser();

		Interviewer interviewer = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(userInterviewer);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);
		when(adminServiceInterface.updateUser(anyLong(), any())).thenReturn(Boolean.FALSE);

		String inputInJson = this.mapToJson(userInterviewerDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateuser/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	/**
	 * Testcase of delete user for Admin userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteUserWhenUserTypeIsAdmin() throws Exception {

		User user = getAdminUser();

		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(adminRepository.findByUser(any())).thenReturn(admin);
		when(adminServiceInterface.deleteUser(anyLong())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(admin);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteuser/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testcase of delete user for Interviewer userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteUserWhenUserTypeIsInterviewer() throws Exception {

		User user = getInterviewerUser();

		Interviewer interviewer = getInterviewer();
		interviewer.setFlag(FLAG_ZERO);
		interviewer.setUser(null);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);
		when(adminServiceInterface.deleteUser(any())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(interviewer);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteUser/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	/**
	 * Testcase of delete user for Interviewer userType, when interview is assigned
	 * to interviewer and candiate is rejected after interview.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewerAndInterviewResultIsRejected() throws Exception {

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

		when(adminServiceInterface.deleteUser(anyLong())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(interviewer);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteuser/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Testcase of delete user for Interviewer userType, when interview is assigned
	 * to interviewer and candiate is accepted after interview.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewerAndInterviewResultIsAccepted() throws Exception {

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

		when(adminServiceInterface.deleteUser(anyLong())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(interviewer);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteuser/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Testcase of delete user for Interviewer userType, when interview is not
	 * assigned to interviewer.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteUserWhenUserTypeIsInterviewerAndInterviewIsNotAssigned() throws Exception {

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

		when(adminServiceInterface.deleteUser(anyLong())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(interviewer);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteuser/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Testcase of delete user for Human resource userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteUserWhenUserTypeIsHumanResource() throws Exception {

		User user = getHumanResourceUser();

		HumanResource humanResource = getHumanResource();
		humanResource.setUser(null);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(humanResourceRepository.findByUser(any())).thenReturn(humanResource);
		when(adminServiceInterface.deleteUser(anyLong())).thenReturn(Boolean.TRUE);

		String inputInJson = this.mapToJson(humanResource);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteuser/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testcase of delete user when userId is null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteUserWhenUserIdIsNull() throws Exception {

		User user = getHumanResourceUser();

		HumanResource humanResource = getHumanResource();
		humanResource.setUser(null);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(humanResourceRepository.findByUser(any())).thenReturn(humanResource);
		when(adminServiceInterface.deleteUser(anyLong())).thenReturn(Boolean.FALSE);

		String inputInJson = this.mapToJson(humanResource);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteuser/1 ").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Testcase of delete user when user not found from userId.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteUserWhenUserNotFound() throws Exception {

		getHumanResourceUser();

		HumanResource humanResource = getHumanResource();
		humanResource.setUser(null);

		when(userRepository.findByUserId(anyLong())).thenReturn(null);
		when(humanResourceRepository.findByUser(any())).thenReturn(humanResource);
		when(adminServiceInterface.deleteUser(anyLong())).thenReturn(Boolean.FALSE);

		String inputInJson = this.mapToJson(humanResource);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteuser/1 ").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Testcase for retrieve data of Admin from userId.
	 *
	 * @return the admin from userId
	 * @throws Exception the exception
	 */
	@Test
	public void getAdminFromUserId() throws Exception {
		User user = getAdminUser();
		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(adminRepository.findByUser(any())).thenReturn(admin);

		when(adminServiceInterface.getAdminFromUserId(anyLong())).thenReturn(admin);

		String inputInJson = this.mapToJson(admin);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewadmin/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testcase for retrieve data of Admin from userId. When admin is not found from
	 * that userId,Interface returns null.
	 *
	 * @return the admin from userId return null
	 * @throws Exception the exception
	 */
	@Test
	public void getAdminFromUserIdWhenInterfaceReturnNull() throws Exception {
		User user = getAdminUser();
		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(adminRepository.findByUser(any())).thenReturn(null);

		when(adminServiceInterface.getAdminFromUserId(anyLong())).thenReturn(null);

		String inputInJson = this.mapToJson(admin);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewadmin/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Testcase for retrieve data of Human resource from userId.
	 *
	 * @return the Human resource from userId
	 * @throws Exception the exception
	 */
	@Test
	public void getHumanResourceFromUserId() throws Exception {
		User user = getHumanResourceUser();
		HumanResource humanResource = getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(humanResourceRepository.findByUser(any())).thenReturn(humanResource);

		when(adminServiceInterface.getHumanResourceFromUserId(anyLong())).thenReturn(humanResource);

		String inputInJson = this.mapToJson(humanResource);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewhumanresource/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testcase for retrieve data of human resouce from userId. When Human resource
	 * is not found from that userId,Interface returns null.
	 *
	 * @return the Human resource from userId return null
	 * @throws Exception the exception
	 */
	@Test
	public void getHumanResourceFromUserIdWhenInterfaceReturnNull() throws Exception {
		User user = getHumanResourceUser();
		HumanResource humanResource = getHumanResource();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(humanResourceRepository.findByUser(any())).thenReturn(null);

		when(adminServiceInterface.getHumanResourceFromUserId(anyLong())).thenReturn(null);

		String inputInJson = this.mapToJson(humanResource);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewhumanresource/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Testcase for retrieve data of Interviewer from userId.
	 *
	 * @return the Interviewer from userId
	 * @throws Exception the exception
	 */
	@Test
	public void getInterviewerFromUserId() throws Exception {
		User user = getInterviewerUser();
		Interviewer interviewer = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(interviewerRepository.findByUser(any())).thenReturn(interviewer);

		when(adminServiceInterface.getInterviewerFromUserId(anyLong())).thenReturn(interviewer);

		String inputInJson = this.mapToJson(interviewer);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewinterviewer/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testcase for retrieve data of Interviewer from userId. When Interviewer is
	 * not found from that userId,Interface returns null.
	 *
	 * @return the Interviewer from userId return null
	 * @throws Exception the exception
	 */
	@Test
	public void getInterviewerFromUserIdWhenInterfaceReturnNull() throws Exception {
		User user = getInterviewerUser();
		Interviewer interviewer = getInterviewer();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(interviewerRepository.findByUser(any())).thenReturn(null);

		when(adminServiceInterface.getInterviewerFromUserId(anyLong())).thenReturn(null);

		String inputInJson = this.mapToJson(interviewer);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewinterviewer/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Test case for update details of logged in Admin.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateAdminProfile() throws Exception {

		UserTypeDTO userAdminDTO = getAdminDTO();

		User userAdmin = getAdminUser();

		Admin admin = getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(userAdmin);
		when(adminRepository.findByUser(any())).thenReturn(admin);
		when(adminServiceInterface.updateAdminProfile(anyLong(), any())).thenReturn(admin);

		String inputInJson = this.mapToJson(userAdminDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateadminprofile/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test case for update details of logged in Admin,When admin is not found from
	 * that userId,Interface returns null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateAdminProfileWhenInterfaceReturnNull() throws Exception {

		UserTypeDTO userAdminDTO = getAdminDTO();

		User userAdmin = getAdminUser();

		getAdmin();

		when(userRepository.findByUserId(anyLong())).thenReturn(userAdmin);
		when(adminRepository.findByUser(any())).thenReturn(null);
		when(adminServiceInterface.updateAdminProfile(anyLong(), any())).thenReturn(null);

		String inputInJson = this.mapToJson(userAdminDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateadminprofile/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	/**
	 * Testcase for retrieving all data from Admin.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void viewAllAdmin() throws Exception {

		List<Admin> listOfAdmin = new ArrayList<Admin>();

		Admin admin = getAdmin();

		listOfAdmin.add(admin);

		when(adminRepository.findAll()).thenReturn(listOfAdmin);
		when(adminServiceInterface.viewAllAdmin()).thenReturn(listOfAdmin);

		String inputInJson = this.mapToJson(listOfAdmin);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewalladmin").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testcase for retrieving all data from Admin,When Admin is not found
	 *
	 * @throws Exception the exception
	 * 
	 */
	@Test
	public void viewAllAdminReturnNull() throws Exception {

		List<Admin> listOfAdmin = new ArrayList<Admin>();

		getAdmin();

		when(adminRepository.findAll()).thenReturn(listOfAdmin);
		when(adminServiceInterface.viewAllAdmin()).thenReturn(listOfAdmin);

		String inputInJson = this.mapToJson(listOfAdmin);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewalladmin").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Testcase for retrieving all data from HumanResource.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void viewAllHumanResource() throws Exception {

		List<HumanResource> listOfHumanResource = new ArrayList<HumanResource>();

		HumanResource humanResource = getHumanResource();

		listOfHumanResource.add(humanResource);

		when(humanResourceRepository.findAll()).thenReturn(listOfHumanResource);
		when(adminServiceInterface.viewAllHumanResource()).thenReturn(listOfHumanResource);

		String inputInJson = this.mapToJson(listOfHumanResource);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewallhumanresource").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testcase for retrieving all data from HumanResource,When HumanResource is not
	 * found
	 *
	 * @throws Exception the exception
	 * 
	 */
	@Test
	public void viewAllHumanResourceReturnNull() throws Exception {

		List<HumanResource> listOfHumanResource = new ArrayList<HumanResource>();

		getHumanResource();

		when(humanResourceRepository.findAll()).thenReturn(listOfHumanResource);
		when(adminServiceInterface.viewAllHumanResource()).thenReturn(listOfHumanResource);

		String inputInJson = this.mapToJson(listOfHumanResource);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewallhumanresource").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Testcase for retrieving all data from Interviewer.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void viewAllInterviewer() throws Exception {

		List<Interviewer> listOfInterviewer = new ArrayList<Interviewer>();

		Interviewer admin = getInterviewer();

		listOfInterviewer.add(admin);

		when(interviewerRepository.findAll()).thenReturn(listOfInterviewer);
		when(adminServiceInterface.viewAllInterviewer()).thenReturn(listOfInterviewer);

		String inputInJson = this.mapToJson(listOfInterviewer);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewallinterviewer").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Testcase for retrieving all data from Interviewer,When Interviewer is not
	 * found
	 *
	 * @throws Exception the exception
	 * 
	 */
	@Test
	public void viewAllInterviewerReturnNull() throws Exception {

		List<Interviewer> listOfInterviewer = new ArrayList<Interviewer>();

		getInterviewer();

		when(interviewerRepository.findAll()).thenReturn(listOfInterviewer);
		when(adminServiceInterface.viewAllInterviewer()).thenReturn(listOfInterviewer);

		String inputInJson = this.mapToJson(listOfInterviewer);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewallinterviewer").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	/**
	 * Used for converting data to JSON format.
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
}
