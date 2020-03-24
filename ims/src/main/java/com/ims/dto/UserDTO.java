package com.ims.dto;

/**
 * The Class UserDTO.
 */
public class UserDTO {

	/** The user id. */
	private long userId;

	/** The username. */
	private String username;

	/** The password. */
	private String password;

	
	/** The user type. */
	private String user_type;

	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public String getUser_type() {
		return user_type;
	}

	/**
	 * Sets the user type.
	 *
	 * @param user_type the new user type
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

}
