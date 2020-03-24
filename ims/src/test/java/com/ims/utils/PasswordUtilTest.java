package com.ims.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.ims.util.PasswordUtil;

/**
 * The Class PasswordUtilTest.
 */
@SpringBootTest
public class PasswordUtilTest {
	
	/** The password util. */
	@InjectMocks
	private PasswordUtil passwordUtil;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 *This method is use for encrypt password.
	 */
	@Test
	public void encryptPassword() {
		
		assertNotNull(passwordUtil.encryptPassword(anyString()));
		
	}
	
	
	/**
	 * This method is use for encrypt password when password is null.
	 */
	@Test
	public void encryptPasswordWhenPasswordIsNull() {
		
		assertNull(passwordUtil.encryptPassword(null));
		
	}
	
	

}
