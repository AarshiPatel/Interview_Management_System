package com.ims.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

/**
 * The Class NameUtil.
 */
@Service
public class NameUtil {
	/**
	 * Method for validating username.
	 *
	 * @param userName the userName
	 * @return true, if is valid
	 */
	public boolean isValidName(String userName) {

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (userName == null)
			return false;
		return pat.matcher(userName).matches();

	}

}
