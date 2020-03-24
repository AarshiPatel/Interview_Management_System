package com.ims.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ims.util.DateOfBirthUtil;

/**
 * The Class ExcelFileTestcCase.
 */
@SpringBootTest
public class DateOfBirthTest {

	@InjectMocks
	DateOfBirthUtil dateOfBirthUtil;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test is valid age.
	 */
	@Test
	public void testIsValidAge() {
		LocalDate dateOfBirth = LocalDate.parse("1995-02-02");
		assertTrue(dateOfBirthUtil.isValidAge(dateOfBirth));
	}

	/**
	 * Test is not valid age.
	 */
	@Test
	public void testIsNotValidAge() {
		LocalDate dateOfBirth = LocalDate.now();
		assertFalse(dateOfBirthUtil.isValidAge(dateOfBirth));
	}

}
