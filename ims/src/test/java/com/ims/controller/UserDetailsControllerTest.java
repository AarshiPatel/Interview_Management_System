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
import com.ims.dao.CandidateRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.UserDetailsRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.UserDetailsDTO;
import com.ims.dto.response.UserDetailsResponseDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.service.UserDetailsServiceInterface;

/**
 * The Class UserDetailsControllerTest.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsControllerTest {
	/** The adminController. */
	@InjectMocks
	private UserDetailsController userDetailsController;

	/** The adminserviceinterface. */
	@Mock
	private UserDetailsServiceInterface userDetailsServiceInterface;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

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

	/** The Constant mobilenumber. */
	private final static String MOBILE_NUMBER = "9876543210";

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

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userDetailsController)
				.addPlaceholderValue("settings.cors_origin", "https://localhost:3000.").build();
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
	 * Testcase of adduser for Interviewer userType.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addUserWhenUserTypeIsInterviewer() throws Exception {

		UserDetailsDTO userDTO = getInterviewerDTO();

		getInterviewerUser();

		UserDetails userDetails = getInterviewer();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(userDetailsServiceInterface.addUser(any())).thenReturn(userDetails);

		String inputInJson = this.mapToJson(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/adduserdetails").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Adds the user when user type is human resource.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addUserWhenUserTypeIsHumanResource() throws Exception {

		UserDetailsDTO userDTO = getHumanResourceDTO();

		getHumanResourceUser();

		UserDetails userDetails = getHumanResource();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(userDetailsServiceInterface.addUser(any())).thenReturn(userDetails);

		String inputInJson = this.mapToJson(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/adduserdetails").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Adds the user when user type is admin.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addUserWhenUserTypeIsAdmin() throws Exception {

		UserDetailsDTO userDTO = getAdminDTO();

		getAdminUser();

		UserDetails userDetails = getAdmin();

		when(userRepository.findByUsername(anyString())).thenReturn(null);
		when(userDetailsServiceInterface.addUser(any())).thenReturn(userDetails);

		String inputInJson = this.mapToJson(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/adduserdetails").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * View all user.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void viewAllUser() throws Exception {

		List<UserResponseDTO> listOfUserResponseDTO = new ArrayList<UserResponseDTO>();

		List<User> listOfUser = new ArrayList<User>();

		User user = getAdminUser();

		listOfUser.add(user);

		listOfUserResponseDTO.add(getUserResponseDTO());

		when(userRepository.findAll()).thenReturn(listOfUser);
		when(userDetailsServiceInterface.viewAllUser()).thenReturn(listOfUserResponseDTO);

		String inputInJson = this.mapToJson(listOfUserResponseDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewalluser").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

//	/**
//	 * View all user return null.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void viewAllUserReturnNull() throws Exception {
//
//		List<UserResponseDTO> listOfUserResponseDTO = new ArrayList<UserResponseDTO>();
//
//		List<User> listOfUser = new ArrayList<User>();
//
//		User user = getAdminUser();
//
//		listOfUser.add(user);
//
//		when(userRepository.findAll()).thenReturn(listOfUser);
//		when(userDetailsServiceInterface.viewAllUser()).thenReturn(listOfUserResponseDTO);
//
//		String inputInJson = this.mapToJson(listOfUserResponseDTO);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/viewalluser").accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//	}

	/**
	 * View all interviewer.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void viewAllInterviewer() throws Exception {

		List<UserDetailsResponseDTO> listOfUserDetailsResponseDTO = new ArrayList<UserDetailsResponseDTO>();

		List<UserDetails> listOfUserDetails = new ArrayList<UserDetails>();

		UserDetails interviewer = getInterviewer();

		listOfUserDetails.add(interviewer);

		listOfUserDetailsResponseDTO.add(getInterviewerResponseDTO());

		when(userDetailsRepository.findAllInterviewer()).thenReturn(listOfUserDetails);
		when(userDetailsServiceInterface.viewAllInterviewer()).thenReturn(listOfUserDetailsResponseDTO);

		String inputInJson = this.mapToJson(listOfUserDetailsResponseDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewAllInterviewer").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

//	/**
//	 * View all interviewer return null.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void viewAllInterviewerReturnNull() throws Exception {
//
//		List<UserDetailsResponseDTO> listOfUserDetailsResponseDTO = new ArrayList<UserDetailsResponseDTO>();
//
//		List<UserDetails> listOfUserDetails = new ArrayList<UserDetails>();
//
//		UserDetails interviewer = getInterviewer();
//
//		listOfUserDetails.add(interviewer);
//
//		when(userDetailsRepository.findAllInterviewer()).thenReturn(listOfUserDetails);
//		when(userDetailsServiceInterface.viewAllInterviewer()).thenReturn(listOfUserDetailsResponseDTO);
//
//		String inputInJson = this.mapToJson(listOfUserDetailsResponseDTO);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/viewAllInterviewer").accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//
//	}

	/**
	 * View all admin.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void viewAllAdmin() throws Exception {

		List<UserDetailsResponseDTO> listOfUserDetailsResponseDTO = new ArrayList<UserDetailsResponseDTO>();

		List<UserDetails> listOfUserDetails = new ArrayList<UserDetails>();

		UserDetails admin = getAdmin();

		listOfUserDetails.add(admin);

		listOfUserDetailsResponseDTO.add(getAdminResponseDTO());

		when(userDetailsRepository.findAllAdmin()).thenReturn(listOfUserDetails);
		when(userDetailsServiceInterface.viewAllAdmin()).thenReturn(listOfUserDetailsResponseDTO);

		String inputInJson = this.mapToJson(listOfUserDetailsResponseDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewAllAdmin").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
//
//	/**
//	 * View all admin return null.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void viewAllAdminReturnNull() throws Exception {
//
//		List<UserDetailsResponseDTO> listOfUserDetailsResponseDTO = new ArrayList<UserDetailsResponseDTO>();
//
//		List<UserDetails> listOfUserDetails = new ArrayList<UserDetails>();
//
//		UserDetails admin = getInterviewer();
//
//		listOfUserDetails.add(admin);
//
//		when(userDetailsRepository.findAllAdmin()).thenReturn(listOfUserDetails);
//		when(userDetailsServiceInterface.viewAllAdmin()).thenReturn(listOfUserDetailsResponseDTO);
//
//		String inputInJson = this.mapToJson(listOfUserDetailsResponseDTO);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/viewAllAdmin").accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//
//	}

	/**
	 * View all human resource.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void viewAllHumanResource() throws Exception {

		List<UserDetailsResponseDTO> listOfUserDetailsResponseDTO = new ArrayList<UserDetailsResponseDTO>();

		List<UserDetails> listOfUserDetails = new ArrayList<UserDetails>();

		UserDetails humanResource = getHumanResource();

		listOfUserDetails.add(humanResource);

		listOfUserDetailsResponseDTO.add(getHumanResourceResponseDTO());

		when(userDetailsRepository.findAllHumanResource()).thenReturn(listOfUserDetails);
		when(userDetailsServiceInterface.viewAllHumanResource()).thenReturn(listOfUserDetailsResponseDTO);

		String inputInJson = this.mapToJson(listOfUserDetailsResponseDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewAllHumanResource").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

//	/**
//	 * View all human resource return null.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void viewAllHumanResourceReturnNull() throws Exception {
//
//		List<UserDetailsResponseDTO> listOfUserDetailsResponseDTO = new ArrayList<UserDetailsResponseDTO>();
//
//		List<UserDetails> listOfUserDetails = new ArrayList<UserDetails>();
//
//		UserDetails humanResource = getHumanResource();
//
//		listOfUserDetails.add(humanResource);
//
//		when(userDetailsRepository.findAllAdmin()).thenReturn(listOfUserDetails);
//		when(userDetailsServiceInterface.viewAllHumanResource()).thenReturn(listOfUserDetailsResponseDTO);
//
//		String inputInJson = this.mapToJson(listOfUserDetailsResponseDTO);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/viewAllHumanResource").accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//
//	}
	/**
	 * View all manager.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void viewAllManager() throws Exception {

		List<UserDetailsResponseDTO> listOfUserDetailsResponseDTO = new ArrayList<UserDetailsResponseDTO>();

		List<UserDetails> listOfUserDetails = new ArrayList<UserDetails>();

		UserDetails manager = getManager();

		listOfUserDetails.add(manager);

		listOfUserDetailsResponseDTO.add(getManagerResponseDTO());

		when(userDetailsRepository.findAllManager()).thenReturn(listOfUserDetails);
		when(userDetailsServiceInterface.viewAllManager()).thenReturn(listOfUserDetailsResponseDTO);

		String inputInJson = this.mapToJson(listOfUserDetailsResponseDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewAllManager").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Gets the user details from user id.
	 *
	 * @return the user details from user id
	 * @throws Exception the exception
	 */
	@Test
	public void getUserDetailsFromUserId() throws Exception {

		UserDetailsResponseDTO userDetailsResponseDTO = getHumanResourceResponseDTO();

		UserDetails humanResource = getHumanResource();

		when(userDetailsRepository.findByUser(any())).thenReturn(humanResource);
		when(userDetailsServiceInterface.getUserDetailsFromUserId(anyLong())).thenReturn(userDetailsResponseDTO);

		String inputInJson = this.mapToJson(userDetailsResponseDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewuserdetails/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Gets the user details from user id return null.
	 *
	 * @return the user details from user id return null
	 * @throws Exception the exception
	 */
	@Test
	public void getUserDetailsFromUserIdReturnNull() throws Exception {

		UserDetailsResponseDTO userDetailsResponseDTO = getHumanResourceResponseDTO();

		UserDetails humanResource = getHumanResource();

		when(userDetailsRepository.findByUser(any())).thenReturn(humanResource);
		when(userDetailsServiceInterface.getUserDetailsFromUserId(anyLong())).thenReturn(null);

		String inputInJson = this.mapToJson(userDetailsResponseDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/viewuserdetails/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Update user when user type is admin.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateUserWhenUserTypeIsAdmin() throws Exception {

		UserDetails userDetails = getAdmin();
		UserDetailsDTO userDetailsDTO = getAdminDTO();

		User userAdmin = getAdminUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(userAdmin);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);
		when(userDetailsServiceInterface.updateUser(anyLong(), any())).thenReturn(userDetails);

		String inputInJson = this.mapToJson(userDetailsDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateuserdetails/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Update user when user type is interviewer.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateUserWhenUserTypeIsInterviewer() throws Exception {

		UserDetails userDetails = getInterviewer();
		UserDetailsDTO userDetailsDTO = getInterviewerDTO();

		User userInterviewer = getInterviewerUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(userInterviewer);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);
		when(userDetailsServiceInterface.updateUser(anyLong(), any())).thenReturn(userDetails);

		String inputInJson = this.mapToJson(userDetailsDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateuserdetails/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Update user when user type is human rerource.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateUserWhenUserTypeIsHumanRerource() throws Exception {

		UserDetails userDetails = getHumanResource();
		UserDetailsDTO userDetailsDTO = getHumanResourceDTO();

		User userHumanResource = getHumanResourceUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(userHumanResource);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);
		when(userDetailsServiceInterface.updateUser(anyLong(), any())).thenReturn(userDetails);

		String inputInJson = this.mapToJson(userDetailsDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateuserdetails/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Delete user.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteUser() throws Exception {
		User user = getAdminUser();

		UserDetails admin = getAdmin();
		admin.setActiveFlag(0);
		admin.setUser(null);

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(any())).thenReturn(admin);
		when(userDetailsServiceInterface.deleteUser(anyLong())).thenReturn(admin);

		String inputInJson = this.mapToJson(admin);

		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteuserdetails/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Update admin profile.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateAdminProfile() throws Exception {

		UserDetails userDetails = getAdmin();
		UserDetailsDTO userDetailsDTO = getAdminDTO();

		User userAdmin = getAdminUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(userAdmin);
		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);
		when(userDetailsServiceInterface.updateAdminProfile(anyLong(), any())).thenReturn(getAdminResponseDTO());

		String inputInJson = this.mapToJson(userDetailsDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/updateadminprofile/1").content(inputInJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

//	/**
//	 * Update admin profile return null.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void updateAdminProfileReturnNull() throws Exception {
//
//		UserDetails userDetails = getAdmin();
//		UserDetailsDTO userDetailsDTO = getAdminDTO();
//
//		getAdminUser();
//
//		when(userRepository.findByUserId(anyLong())).thenReturn(null);
//		when(userDetailsRepository.findByUser(any())).thenReturn(userDetails);
//		when(userDetailsServiceInterface.updateAdminProfile(anyLong(), any())).thenReturn(null);
//
//		String inputInJson = this.mapToJson(userDetailsDTO);
//
//		mockMvc.perform(MockMvcRequestBuilders.put("/updateadminprofile/1").content(inputInJson)
//				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//	}

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
