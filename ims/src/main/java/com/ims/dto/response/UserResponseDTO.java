package com.ims.dto.response;

/**
 * The Class UserResponseDTO.
 */
public class UserResponseDTO {
	
	/** The user id. */
	private long userId;
	
	/** The username. */
	private String username;
	
	/** The user type. */
	private String user_type;

	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
}
