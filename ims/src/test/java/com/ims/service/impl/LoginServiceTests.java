package com.ims.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ims.dao.InterviewerRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.UserDTO;
import com.ims.entity.User;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.mail.Mail;
import com.ims.util.NameUtil;
import static org.mockito.ArgumentMatchers.anyString;
/**
 * The Class LoginServiceTests.
 */
@SpringBootTest
public class LoginServiceTests {

	/** The loginservice. */
	@InjectMocks
	private LoginServiceImpl loginService;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/** The request. */
	@Mock
	private HttpServletRequest request;

	/** The response. */
	@Mock
	private HttpServletResponse response;

	@Mock
	private InterviewerRepository interviewerRepository;

	@InjectMocks
	private InterviewerServiceImpl interviewerService;

	@Mock
	NameUtil isValidEmailId;
	@Mock
	private Mail sendForgotPasswordMail;
	/** The session. */
	@Mock
	private HttpSession session;

	/** The Constant emailId. */
	private final static String EMAIL_ID = "vahak15@gmail.com";

	/** The Constant password. */
	private final static String PASSWORD = "AB@123";

	/** The Constant userId. */
	private final static long USER_ID = 1;

	/** The Constant AuthCode. */
	private final static String AUTH_CODE = "ADJD%@#GD";

	/** The Constant DummyAuthCode. */
	private final static String DUMMY_AUTH_CODE = "JD%@#GD";

	private final static String EMPTY_STRING = "";

	private final static String INVALID_EMAIL_ID = "vahak1com";

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test login service.
	 * 
	 * @throws BadRequestException
	 * @throws ResourceNotFoundException
	 * @throws UnprocessableEntity
	 */
	@Test
	public void testLoginService() throws UnprocessableEntity, ResourceNotFoundException, BadRequestException {

//		UserDTO userDTO = new UserDTO();
//		userDTO.setUserId(USER_ID);
//		userDTO.setUsername(EMAIL_ID);
//		userDTO.setPassword(PASSWORD);
//		User user = new User();
//		user.setUserId(USER_ID);
//		user.setPassword(PASSWORD);
//		user.setUsername(EMAIL_ID);

		when(isValidEmailId.isValidName(anyString())).thenReturn(true);
		when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(getUser());
		loginService.login(getUserDTO());
	}

	/**
	 * Test login servicenull.
	 * 
	 * @throws BadRequestException
	 * @throws ResourceNotFoundException
	 * @throws UnprocessableEntity
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testLoginServiceInValidUser()
			throws UnprocessableEntity, ResourceNotFoundException, BadRequestException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(EMAIL_ID);
		userDTO.setPassword(PASSWORD);
		when(isValidEmailId.isValidName(anyString())).thenReturn(true);
		when(userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword())).thenReturn(null);

		loginService.login(getUserDTO());
	}

	/**
	 * Test login service in valid user name.
	 *
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 * @throws BadRequestException
	 */
	@Test(expected = BadRequestException.class)
	public void testLoginServiceInValidUserName()
			throws UnprocessableEntity, ResourceNotFoundException, BadRequestException {

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(INVALID_EMAIL_ID);

		when(isValidEmailId.isValidName(anyString())).thenReturn(false);

		loginService.login(getUserDTO());
	}

	/**
	 * Test login service email ID empty.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws BadRequestException       the bad request exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testLoginServiceEmailIDEmpty()
			throws UnprocessableEntity, ResourceNotFoundException, BadRequestException {

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(EMPTY_STRING);
		userDTO.setPassword(PASSWORD);
		when(userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword()))
				.thenReturn(getUser());
		when(isValidEmailId.isValidName(anyString())).thenReturn(true);

		loginService.login(userDTO);
	}

	/**
	 * Test login service password empty.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws BadRequestException       the bad request exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testLoginServicePasswordEmpty()
			throws UnprocessableEntity, ResourceNotFoundException, BadRequestException {

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(EMPTY_STRING);
		userDTO.setPassword(EMPTY_STRING);
		when(userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword()))
				.thenReturn(getUser());
		when(isValidEmailId.isValidName(anyString())).thenReturn(true);

		loginService.login(userDTO);
	}

	/**
	 * Test login service password user name null.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws BadRequestException       the bad request exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testLoginServicePasswordUserNameNull()
			throws UnprocessableEntity, ResourceNotFoundException, BadRequestException {

		UserDTO userDTO = new UserDTO();
		userDTO.setPassword(EMPTY_STRING);
		when(userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword()))
				.thenReturn(getUser());
		when(isValidEmailId.isValidName(anyString())).thenReturn(true);

		loginService.login(userDTO);
	}

	/**
	 * Test login service password null.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws BadRequestException       the bad request exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testLoginServicePasswordNull()
			throws UnprocessableEntity, ResourceNotFoundException, BadRequestException {

		UserDTO userDTO = new UserDTO();
		userDTO.setUser_type(EMAIL_ID);
		when(userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword()))
				.thenReturn(getUser());
		when(isValidEmailId.isValidName(anyString())).thenReturn(true);

		loginService.login(userDTO);
	}

	/**
	 * Test forgotpassword.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testForgotpassword() throws Exception {
		when(isValidEmailId.isValidName(anyString())).thenReturn(true);
		when(userRepository.findByUsername(anyString())).thenReturn(getUser());

		assertNotNull(loginService.forgotPasswordMail(EMAIL_ID));
	}

	/**
	 * Test forgot password user not found.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testForgotPasswordUserNotFound() throws Exception {
		when(isValidEmailId.isValidName(anyString())).thenReturn(true);
		when(userRepository.findByUsername(anyString())).thenReturn(null);
		loginService.forgotPasswordMail(EMAIL_ID);
	}

	/**
	 * Test forgot password usernam null.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = BadRequestException.class)
	public void testForgotPasswordUsernamInvalid() throws Exception {
		when(isValidEmailId.isValidName(anyString())).thenReturn(false);

		loginService.forgotPasswordMail(EMAIL_ID);
	}

	/**
	 * Test forgot password username empty.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testForgotPasswordUsernameEmpty() throws Exception {

		loginService.forgotPasswordMail(EMPTY_STRING);
	}

	/**
	 * Test rest password.
	 * 
	 * @throws ResourceNotFoundException
	 * @throws UnprocessableEntity
	 */
	@Test
	public void testRestPassword() throws UnprocessableEntity, ResourceNotFoundException {
		UserDTO userDTO = getUserDTO();
		userDTO.setNewPassword(PASSWORD);
		when(userRepository.findByUserId(USER_ID)).thenReturn(getUser());
		assertNotNull(loginService.restPassword(userDTO));
	}

	/**
	 * Test rest password user not found.
	 * 
	 * @throws ResourceNotFoundException
	 * @throws UnprocessableEntity
	 */
	@Test(expected = ResourceNotFoundException.class)
	public void testRestPasswordUserNotFound() throws UnprocessableEntity, ResourceNotFoundException {
		UserDTO userDTO = getUserDTO();
		userDTO.setNewPassword(PASSWORD);
		when(userRepository.findByUserId(USER_ID)).thenReturn(null);
		loginService.restPassword(userDTO);
	}

	/**
	 * Test rest password user passwors is empty.
	 *
	 * @throws UnprocessableEntity       the unprocessable entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Test(expected = UnprocessableEntity.class)
	public void testRestPasswordUserPassworsIsEmpty() throws UnprocessableEntity, ResourceNotFoundException {
		UserDTO userDTO = getUserDTO();
		userDTO.setPassword(EMPTY_STRING);
		when(userRepository.findByUserId(USER_ID)).thenReturn(getUser());
		loginService.restPassword(userDTO);
	}

	/**
	 * Test auth code verification.
	 */
	@Test
	public void testAuthCodeVerification() {
		when(userRepository.findByUsername(EMAIL_ID)).thenReturn(getUser());
		assertNotNull(loginService.authCodeVerification(AUTH_CODE, EMAIL_ID));
	}

	/**
	 * Test auth code verification user not found.
	 */
	@Test
	public void testAuthCodeVerificationUserNotFound() {
		when(userRepository.findByUsername(EMAIL_ID)).thenReturn(null);
		assertEquals(0, loginService.authCodeVerification(AUTH_CODE, EMAIL_ID));
	}

	/**
	 * Test auth code verification auth code not match.
	 */
	@Test
	public void testAuthCodeVerificationAuthCodeNotMatch() {
		when(userRepository.findByUsername(EMAIL_ID)).thenReturn(getUser());
		assertEquals(0, loginService.authCodeVerification(DUMMY_AUTH_CODE, EMAIL_ID));
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	private User getUser() {
		User user = new User();
		user.setUserId(USER_ID);
		user.setUsername(EMAIL_ID);
		user.setPassword(PASSWORD);
		user.setAuth_code(AUTH_CODE);
		return user;
	}

	private UserDTO getUserDTO() {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(USER_ID);
		userDto.setUsername(EMAIL_ID);
		userDto.setPassword(PASSWORD);

		return userDto;
	}

}