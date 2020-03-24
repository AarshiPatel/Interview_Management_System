package com.ims.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

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
import com.ims.dao.UserRepository;
import com.ims.dto.UserDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.entity.User;
import com.ims.exception.ResourceNotFoundException;
import com.ims.mail.Mail;
import com.ims.service.LoginServiceInterface;

/**
 * The Class LoginControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginControllerTest {

	/** The controller. */
	@InjectMocks
	private LoginController controller;

	/** The login service impl. */
	@Mock
	private LoginServiceInterface loginService;

	@Mock
	private Mail sendForgotPasswordMail;
	/** The response. */

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	@Mock
	private ResourceNotFoundException excepaction;
	/** The request. */
	@Mock
	HttpServletRequest request;

	/** The Constant emailId. */
	private final static String EMAIL_ID = "vahak15@gmail.com";

	/** The Constant password. */
	private final static String PASSWORD = "AB@459876123";

	/** The Constant usertype. */
	private final static String USER_TYPE = "admin";

	/** The Constant userId. */
	private final static long USER_ID = 1;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.addPlaceholderValue("settings.cors_origin", "https://localhost:3000.").build();
	}

	/**
	 * User login.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void userLogin() throws Exception {

		UserDTO userdto = new UserDTO();
		userdto.setUsername(EMAIL_ID);
		userdto.setPassword(PASSWORD);
		userdto.setUser_type(USER_TYPE);

		when(loginService.login(any())).thenReturn(getUserResponseDTO());

		String inputInJson = this.mapToJson(userdto);
		mockMvc.perform(MockMvcRequestBuilders.post("/login").accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

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
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * Forgot password.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testForgotPassword() throws Exception {

		when(userRepository.findByUsername(anyString())).thenReturn(getUser());
		when(loginService.forgotPasswordMail(any())).thenReturn(getUserResponseDTO());
		mockMvc.perform(MockMvcRequestBuilders.get("/forgotpassword/'" + EMAIL_ID + "'"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Test change password.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testChangePassword() throws Exception {
		String inputInJson = this.mapToJson(getUserDTO());
		when(loginService.restPassword(any())).thenReturn(getUserResponseDTO());

		mockMvc.perform(MockMvcRequestBuilders.post("/changepassword").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Check auth code.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void checkAuthCodeInValidURL() throws Exception {
		when(loginService.authCodeVerification(anyString(), anyString())).thenReturn((long) 0);

		mockMvc.perform(MockMvcRequestBuilders.get("/user/authcode?authCode=authCode &emailId=emailId"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost:3000/InValidURL"));
	}

	/**
	 * Check auth code.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void checkAuthCode() throws Exception {
		when(loginService.authCodeVerification(anyString(), anyString())).thenReturn(USER_ID);
		mockMvc.perform(MockMvcRequestBuilders.get("/user/authcode?authCode=authCode &emailId=emailId"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost:3000/restpassword/" + USER_ID + ""));
	}

	private User getUser() {
		User user = new User();
		user.setUserId(USER_ID);
		user.setUsername(EMAIL_ID);
		user.setPassword(PASSWORD);
		return user;
	}

	private UserDTO getUserDTO() {
		UserDTO user = new UserDTO();
		user.setUserId(USER_ID);
		user.setUsername(EMAIL_ID);
		user.setPassword(PASSWORD);
		return user;
	}

	private UserResponseDTO getUserResponseDTO() {
		UserResponseDTO user = new UserResponseDTO();
		user.setUserId(USER_ID);
		user.setUsername(EMAIL_ID);
		return user;
	}

}
