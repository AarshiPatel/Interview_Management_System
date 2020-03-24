package com.ims.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

import com.ims.dao.JobRequirementCounterRepository;
import com.ims.dao.JobRequirementRepository;
import com.ims.dao.UserDetailsRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.request.JobRequirmentRequestDTO;
import com.ims.entity.JobRequirement;
import com.ims.entity.JobRequirementCounter;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;

/**
 * The Class JobRequirmentServiceImpl.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JobRequirmentServiceImpl {

	/** The Constant REQUIRMENT_DEPARTMENT. */
	private static final String REQUIRMENT_DEPARTMENT = "PES";

	/** The Constant REQUIRMENT_JOB_DESIGNATON. */
	private static final String REQUIRMENT_JOB_DESIGNATON = "Engineer";

	/** The Constant REQUIRMENT_SKILL. */
	private static final String REQUIRMENT_SKILL = "Java";

	/** The Constant REQUIRMENT_GROUP. */
	private static final String REQUIRMENT_GROUP = "QA";

	/** The Constant REQUIRMENT_RANGE. */
	private static final String REQUIRMENT_RANGE = "2-4";

	/** The Constant REQUIRMENT_JOB_OPENING. */
	private static final int REQUIRMENT_JOB_OPENING = 2;

	/** The Constant REQUIRMENT_JOB_OPENING_ZERO. */
	private static final int REQUIRMENT_JOB_OPENING_ZERO = 0;

	/** The Constant ZERO. */
	private static final long ZERO = 0;
	/** The Constant USER_ID. */
	private static final int USER_ID = 12;

	/** The Constant EMAIL_ID. */
	private static final String EMAIL_ID = "ram@gmail.com";

	/** The Constant INVALID_DATA. */
	private static final String INVALID_DATA = "AA";

	/** The Constant NAME. */
	private static final String NAME = "Ram";

	private static final long MANAGER_ID = 2;

	private static final String USER_TYPE_MANAGER = "Manager";

	private static final String USER_TYPE_NOT_MANAGER = "Admin";

	private static final long REQUIRMENT_ID = 12;

	/** The job requirement service impl. */
	@InjectMocks
	private JobRequirmentImpl jobRequirmentServiceImpl;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/** The user details repository. */
	@Mock
	private UserDetailsRepository userDetailsRepository;

	/** The job requirement repository. */
	@Mock
	private JobRequirementRepository jobRequirementRepository;

	/** The job requirement counter repository. */
	@Mock
	private JobRequirementCounterRepository jobRequirementCounterRepository;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	/**
	 * Test add new job requirement.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testAddJobRequirementAddNewRequirement() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		when(jobRequirementCounterRepository.findByIsRequirementAvalible(anyString(), anyString(), anyString(),
				anyString(), anyString())).thenReturn(getRequrimentCounter());
		assertNotNull(jobRequirmentServiceImpl.addJobRequirement(getJobRequirmentDTO()));
	}

	// Aarshi Home 23-03-2020
	@Test
	public void testUpdateJobRequirement() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		when(jobRequirementCounterRepository.findByIsRequirementAvalible(anyString(), anyString(), anyString(),
				anyString(), anyString())).thenReturn(getRequrimentCounter());
		when(jobRequirementRepository.findByRequirementId(anyLong())).thenReturn(getJobRequirment());
		assertNotNull(jobRequirmentServiceImpl.updateJobRequirement(getJobRequirmentDTO()));
	}

	// Aarshi Home 23-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateJobRequirementRequrimentNotFound() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		when(jobRequirementCounterRepository.findByIsRequirementAvalible(anyString(), anyString(), anyString(),
				anyString(), anyString())).thenReturn(getRequrimentCounter());
		when(jobRequirementRepository.findByRequirementId(anyLong())).thenReturn(null);
		jobRequirmentServiceImpl.updateJobRequirement(getJobRequirmentDTO());
	}
	// Aarshi Home 23-03-2020

	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateJobRequirementNotMatch() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		when(jobRequirementCounterRepository.findByIsRequirementAvalible(anyString(), anyString(), anyString(),
				anyString(), anyString())).thenReturn(null);
		when(jobRequirementRepository.findByRequirementId(anyLong())).thenReturn(getJobRequirment());
		jobRequirmentServiceImpl.updateJobRequirement(getJobRequirmentDTO());
	}

	/**
	 * Test add job requirement add new requriment.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test
	public void testAddJobRequirementAddNewRequriment() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		when(jobRequirementCounterRepository.findByIsRequirementAvalible(anyString(), anyString(), anyString(),
				anyString(), anyString())).thenReturn(null);
		assertNotNull(jobRequirmentServiceImpl.addJobRequirement(getJobRequirmentDTO()));
	}

	// Aarshi Home 23-03-2020
	@Test
	public void testAddJobRequirementUpdateAlreadyAvalibleRequriment()
			throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		when(jobRequirementCounterRepository.findByIsRequirementAvalible(anyString(), anyString(), anyString(),
				anyString(), anyString())).thenReturn(getRequrimentCounter());
		when(jobRequirementRepository.findJobRequirement(anyString(), anyString(), anyString(), anyString(),
				anyString(), anyLong())).thenReturn(getJobRequirment());
		assertNotNull(jobRequirmentServiceImpl.addJobRequirement(getJobRequirmentDTO()));
	}
	
	// Aarshi Home 24-03-2020
	
	@Test
	public void testAddJobRequirementUpdateAlreadyAvalibleRequrimentNonFoundInCounter()
			throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		when(jobRequirementCounterRepository.findByIsRequirementAvalible(anyString(), anyString(), anyString(),
				anyString(), anyString())).thenReturn(null);
		when(jobRequirementRepository.findJobRequirement(anyString(), anyString(), anyString(), anyString(),
				anyString(), anyLong())).thenReturn(getJobRequirment());
		assertNotNull(jobRequirmentServiceImpl.addJobRequirement(getJobRequirmentDTO()));
	}

	/**
	 * Test add job requirement user not found.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testAddJobRequirementUserNotFound() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(null);
		jobRequirmentServiceImpl.addJobRequirement(getJobRequirmentDTO());
	}

	// Aarshi Home 23-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateJobRequirementUserNotFound() throws UnprocessableEntity, ResourceNotFoundException {
		User user = getUser();

		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(null);
		jobRequirmentServiceImpl.updateJobRequirement(getJobRequirmentDTO());
	}

	/**
	 * Test add job requirement expriance null.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testAddJobRequirementExprianceNull() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setExprianceRange(null);
		jobRequirmentServiceImpl.addJobRequirement(jobRequriment);
	}

//Aarshi Home 23-03-2020
	@Test(expected = UnprocessableEntity.class)
	public void testUpdateJobRequirementExprianceNull() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setExprianceRange(null);
		jobRequirmentServiceImpl.updateJobRequirement(jobRequriment);
	}

	/**
	 * Test add job requirement requirement department null.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testAddJobRequirementRequirementDepartmentNull() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setRequirementDepartment(null);
		jobRequirmentServiceImpl.addJobRequirement(jobRequriment);
	}

	// Aarshi Home 23-03-2020
	@Test(expected = UnprocessableEntity.class)
	public void testUpdateJobRequirementRequirementDepartmentNull()
			throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setRequirementDepartment(null);
		jobRequirmentServiceImpl.updateJobRequirement(jobRequriment);
	}

	/**
	 * Test add job requirement requirement designation null.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testAddJobRequirementRequirementDesignationNull()
			throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setRequirementDesignation(null);
		jobRequirmentServiceImpl.addJobRequirement(jobRequriment);
	}

	// Aarshi Home 23-03-2020
	@Test(expected = UnprocessableEntity.class)
	public void testUpdateJobRequirementRequirementDesignationNull()
			throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setRequirementDesignation(null);
		jobRequirmentServiceImpl.updateJobRequirement(jobRequriment);
	}

	/**
	 * Test add job requirement requirement group null.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testAddJobRequirementRequirementGroupNull() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setRequirementGroup(null);
		jobRequirmentServiceImpl.addJobRequirement(jobRequriment);
	}

	// Aarshi Home 23-03-2020
	@Test(expected = UnprocessableEntity.class)
	public void testUpdateJobRequirementRequirementGroupNull() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setRequirementGroup(null);
		jobRequirmentServiceImpl.updateJobRequirement(jobRequriment);
	}

	/**
	 * Test add job requirement requirement name null.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testAddJobRequirementRequirementNameNull() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setRequirmentName(null);
		jobRequirmentServiceImpl.addJobRequirement(jobRequriment);
	}

//Aarshi Home 23-03-2020
	@Test(expected = UnprocessableEntity.class)
	public void testUpdateJobRequirementRequirementNameNull() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setRequirmentName(null);
		jobRequirmentServiceImpl.updateJobRequirement(jobRequriment);
	}

	/**
	 * Test add job requirement job opeing zero.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testAddJobRequirementJobOpeingZero() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setJobOpening(REQUIRMENT_JOB_OPENING_ZERO);
		jobRequirmentServiceImpl.addJobRequirement(jobRequriment);
	}

	// Aarshi Home 23-03-2020
	@Test(expected = UnprocessableEntity.class)
	public void testUpdateJobRequirementJobOpeingZero() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setJobOpening(REQUIRMENT_JOB_OPENING_ZERO);
		jobRequirmentServiceImpl.updateJobRequirement(jobRequriment);
	}

	/**
	 * Test add job requirement user id zero.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testAddJobRequirementUserIdZero() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setUserId(ZERO);
		jobRequirmentServiceImpl.addJobRequirement(jobRequriment);
	}

	// Aarshi Home 23-03-2020
	@Test(expected = UnprocessableEntity.class)
	public void testupdateJobRequirementUserIdZero() throws UnprocessableEntity, ResourceNotFoundException {

		JobRequirmentRequestDTO jobRequriment = getJobRequirmentDTO();
		jobRequriment.setUserId(ZERO);
		jobRequirmentServiceImpl.updateJobRequirement(jobRequriment);
	}

	/**
	 * Test get job requirement list.
	 */
	@Test
	public void testGetJobRequirementList() {
		List<JobRequirementCounter> jobRequirementCounterList = new ArrayList<>();
		jobRequirementCounterList.add(getRequrimentCounter());

		List<JobRequirement> jobRequirmentResponseDTOList = new ArrayList<>();
		jobRequirmentResponseDTOList.add(getJobRequirment());
		when(jobRequirementCounterRepository.findAllRequiremnet()).thenReturn(jobRequirementCounterList);
		when(jobRequirementRepository.getRequrirementList()).thenReturn(jobRequirmentResponseDTOList);
		assertNotNull(jobRequirmentServiceImpl.getJobRequirementList());

	}

	/**
	 * Test get job requirement department not match.
	 */
	@Test
	public void testGetJobRequirementDepartmentNotMatch() {
		List<JobRequirementCounter> jobRequirementCounterList = new ArrayList<>();
		jobRequirementCounterList.add(getRequrimentCounter());

		JobRequirement jobRequirement = getJobRequirment();
		jobRequirement.setRequirementDepartment(INVALID_DATA);
		List<JobRequirement> jobRequirmentResponseDTOList = new ArrayList<>();

		jobRequirmentResponseDTOList.add(jobRequirement);

		when(jobRequirementCounterRepository.findAllRequiremnet()).thenReturn(jobRequirementCounterList);
		when(jobRequirementRepository.getRequrirementList()).thenReturn(jobRequirmentResponseDTOList);
		assertNotNull(jobRequirmentServiceImpl.getJobRequirementList());

	}

	/**
	 * Test get job requirement designation not match.
	 */
	@Test
	public void testGetJobRequirementDesignationNotMatch() {
		List<JobRequirementCounter> jobRequirementCounterList = new ArrayList<>();
		jobRequirementCounterList.add(getRequrimentCounter());

		JobRequirement jobRequirement = getJobRequirment();
		jobRequirement.setRequirementDesignation(INVALID_DATA);
		List<JobRequirement> jobRequirmentResponseDTOList = new ArrayList<>();

		jobRequirmentResponseDTOList.add(jobRequirement);

		when(jobRequirementCounterRepository.findAllRequiremnet()).thenReturn(jobRequirementCounterList);
		when(jobRequirementRepository.getRequrirementList()).thenReturn(jobRequirmentResponseDTOList);
		assertNotNull(jobRequirmentServiceImpl.getJobRequirementList());

	}

	/**
	 * Test get job requirement exprianve range not match.
	 */
	@Test
	public void testGetJobRequirementExprianveRangeNotMatch() {
		List<JobRequirementCounter> jobRequirementCounterList = new ArrayList<>();
		jobRequirementCounterList.add(getRequrimentCounter());

		JobRequirement jobRequirement = getJobRequirment();
		jobRequirement.setExprianceRange(INVALID_DATA);
		List<JobRequirement> jobRequirmentResponseDTOList = new ArrayList<>();

		jobRequirmentResponseDTOList.add(jobRequirement);

		when(jobRequirementCounterRepository.findAllRequiremnet()).thenReturn(jobRequirementCounterList);
		when(jobRequirementRepository.getRequrirementList()).thenReturn(jobRequirmentResponseDTOList);
		assertNotNull(jobRequirmentServiceImpl.getJobRequirementList());

	}

	/**
	 * Test get job requirement group not match.
	 */
	@Test
	public void testGetJobRequirementGroupNotMatch() {
		List<JobRequirementCounter> jobRequirementCounterList = new ArrayList<>();
		jobRequirementCounterList.add(getRequrimentCounter());

		JobRequirement jobRequirement = getJobRequirment();
		jobRequirement.setRequirementGroup(INVALID_DATA);
		List<JobRequirement> jobRequirmentResponseDTOList = new ArrayList<>();

		jobRequirmentResponseDTOList.add(jobRequirement);

		when(jobRequirementCounterRepository.findAllRequiremnet()).thenReturn(jobRequirementCounterList);
		when(jobRequirementRepository.getRequrirementList()).thenReturn(jobRequirmentResponseDTOList);
		assertNotNull(jobRequirmentServiceImpl.getJobRequirementList());

	}

	@Test
	public void testShowspecificRequirements() {
		List<JobRequirementCounter> jobRequirementCounterList = new ArrayList<>();
		jobRequirementCounterList.add(getRequrimentCounter());
		when(jobRequirementCounterRepository.findSpecifiedRequirements(anyString(), anyString()))
				.thenReturn(jobRequirementCounterList);
		assertNotNull(jobRequirmentServiceImpl.showJobRequirementCounter(anyString(), anyString()));

	}

	@Test
	public void testShowRequirementWithId() {
		JobRequirementCounter jobRequirementCounter = getRequrimentCounter();
		when(jobRequirementCounterRepository.findSpecifiedRequirementswithId(anyLong()))
				.thenReturn(jobRequirementCounter);
		assertNotNull(jobRequirmentServiceImpl.showRequirementWithId(anyLong()));
	}

	// Aarshi Home 23-03-2020
	@Test
	public void testGetJobRequirementListByManageId() throws ResourceNotFoundException {
		User user = getUser();
		user.setUser_type(USER_TYPE_MANAGER);
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getManager());
		assertNotNull(jobRequirmentServiceImpl.getJobRequirementPendingApprovalListByManageId(MANAGER_ID));

	}

	// Aarshi Home 23-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testGetJobRequirementListByManageIdUserNotFound() throws ResourceNotFoundException {

		when(userRepository.findByUserId(anyLong())).thenReturn(null);
		jobRequirmentServiceImpl.getJobRequirementPendingApprovalListByManageId(MANAGER_ID);

	}

	// Aarshi Home 23-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testGetJobRequirementListByManageIdUserTypeNotManager() throws ResourceNotFoundException {
		User user = getUser();
		user.setUser_type(USER_TYPE_NOT_MANAGER);
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		jobRequirmentServiceImpl.getJobRequirementPendingApprovalListByManageId(MANAGER_ID);

	}

	// Aarshi Home 23-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testGetJobRequirementListByManageIdManagerNotFound() throws ResourceNotFoundException {
		User user = getUser();
		user.setUser_type(USER_TYPE_MANAGER);
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(null);
		jobRequirmentServiceImpl.getJobRequirementPendingApprovalListByManageId(MANAGER_ID);

	}

	@Test
	public void testGetApprovedJobRequirmentByManagerId() throws ResourceNotFoundException {
		User user = getUser();
		user.setUser_type(USER_TYPE_MANAGER);
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getManager());
		assertNotNull(jobRequirmentServiceImpl.getApprovedJobRequirmentByManagerId(MANAGER_ID));

	}

// Aarshi Home 24-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testGetApprovedJobRequirmentByManagerIdNotFound() throws ResourceNotFoundException {

		when(userRepository.findByUserId(anyLong())).thenReturn(null);
		jobRequirmentServiceImpl.getApprovedJobRequirmentByManagerId(MANAGER_ID);

	}

// Aarshi Home 24-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testGetApprovedJobRequirmentByManagerIdUserTypeNotManager() throws ResourceNotFoundException {
		User user = getUser();
		user.setUser_type(USER_TYPE_NOT_MANAGER);
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(getUserDetails());
		jobRequirmentServiceImpl.getApprovedJobRequirmentByManagerId(MANAGER_ID);

	}

// Aarshi Home 24-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testGetApprovedJobRequirmentByManagerIdManagerNotFound() throws ResourceNotFoundException {
		User user = getUser();
		user.setUser_type(USER_TYPE_MANAGER);
		when(userRepository.findByUserId(anyLong())).thenReturn(user);
		when(userDetailsRepository.findByUser(user)).thenReturn(null);
		jobRequirmentServiceImpl.getApprovedJobRequirmentByManagerId(MANAGER_ID);

	}

	// Aarshi Home 23-03-2020
	@Test
	public void testGetJobRequirementById() throws ResourceNotFoundException {
		when(jobRequirementRepository.getRequriement(anyLong())).thenReturn(getJobRequirment());
		jobRequirmentServiceImpl.getJobRequirementById(REQUIRMENT_ID);
	}

	// Aarshi Home 23-03-2020
	@Test(expected = ResourceNotFoundException.class)
	public void testGetJobRequirementByIdNotFound() throws ResourceNotFoundException {
		when(jobRequirementRepository.getRequriement(anyLong())).thenReturn(null);
		jobRequirmentServiceImpl.getJobRequirementById(REQUIRMENT_ID);
	}

	/**
	 * Gets the job requirment.
	 *
	 * @return the job requirment
	 */
	JobRequirement getJobRequirment() {
		JobRequirement jobRequriment = new JobRequirement();
		jobRequriment.setRequirementDepartment(REQUIRMENT_DEPARTMENT);
		jobRequriment.setRequirementDesignation(REQUIRMENT_JOB_DESIGNATON);
		jobRequriment.setRequirmentName(REQUIRMENT_SKILL);
		jobRequriment.setRequirementGroup(REQUIRMENT_GROUP);
		jobRequriment.setExprianceRange(REQUIRMENT_RANGE);
		jobRequriment.setJobOpening(REQUIRMENT_JOB_OPENING);
		jobRequriment.setManager(getManager());
		return jobRequriment;
	}

	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	private UserDetails getManager() {
		UserDetails user = new UserDetails();
		user.setUserDetailsId(USER_ID);
		user.setName(NAME);
		return user;
	}

	/**
	 * Gets the job requirement DTO.
	 *
	 * @return the job requirement DTO
	 */
	JobRequirmentRequestDTO getJobRequirmentDTO() {
		JobRequirmentRequestDTO jobRequriment = new JobRequirmentRequestDTO();
		jobRequriment.setRequirementDepartment(REQUIRMENT_DEPARTMENT);
		jobRequriment.setRequirementDesignation(REQUIRMENT_JOB_DESIGNATON);
		jobRequriment.setRequirmentName(REQUIRMENT_SKILL);
		jobRequriment.setRequirementGroup(REQUIRMENT_GROUP);
		jobRequriment.setExprianceRange(REQUIRMENT_RANGE);
		jobRequriment.setJobOpening(REQUIRMENT_JOB_OPENING);
		jobRequriment.setUserId(USER_ID);
		return jobRequriment;

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

	/**
	 * Gets the user details.
	 *
	 * @return the user details
	 */
	UserDetails getUserDetails() {
		UserDetails userDetails = new UserDetails();
		userDetails.setUser(getUser());
		return userDetails;
	}

	/**
	 * Gets the requriment counter.
	 *
	 * @return the requriment counter
	 */
	JobRequirementCounter getRequrimentCounter() {

		JobRequirementCounter counter = new JobRequirementCounter();

		counter.setRequirementDepartment(REQUIRMENT_DEPARTMENT);
		counter.setRequirementDesignation(REQUIRMENT_JOB_DESIGNATON);
		counter.setRequirmentName(REQUIRMENT_SKILL);
		counter.setRequirementGroup(REQUIRMENT_GROUP);
		counter.setExprianceRange(REQUIRMENT_RANGE);
//		counter.setJobOpening(REQUIRMENT_JOB_OPENING);
//		counter.setUserId(USER_ID);

		return counter;
	}
}
