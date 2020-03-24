package com.ims.util;

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DateOfBirthUtil {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DateOfBirthUtil.class);
	
	/**
	 * Checks if is valid age. These method is to check candidate's age is greater
	 * then 18 or not.
	 * 
	 * @param date the date
	 * @return true, if is valid age
	 */
	public boolean isValidAge(LocalDate date) {

		LocalDate today = LocalDate.now(); // Today's date
		LocalDate birthdate = date; // Birth date

		Period period = Period.between(birthdate, today);

		// Now access the values as below
		logger.info("age of candidate is {}", period.getYears());
		if(period.getYears() < 18.0) {
			return false;
		} else {
			return true;
		}

	}

}
