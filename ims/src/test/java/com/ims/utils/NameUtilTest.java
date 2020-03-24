package com.ims.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.ims.util.NameUtil;

@SpringBootTest
public class NameUtilTest {
	
	@InjectMocks
	private NameUtil nameUtil;

	private final static String USERNAME="vahak@gmail.com";
	
	private final static String USERNAME_INVALID="vahak@gmail.com";
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void isValidName() {
		
		assertTrue(nameUtil.isValidName(USERNAME));
		
	}
	
	@Test
	public void isValidNameWhenUsernameIsNull() {
		
		assertFalse(nameUtil.isValidName(null));
		
	}
	@Test
	public void isValidNameWhenUsernameIsInvalid() {
		
		assertTrue(nameUtil.isValidName(USERNAME_INVALID));
		
	}

}
