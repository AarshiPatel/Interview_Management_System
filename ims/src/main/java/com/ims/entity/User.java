package com.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class User.
 */
@Entity
@Table(name = "User")
public class User {

	/** The user id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	/** The username. */
	@Column(name = "username", unique = true)
	private String username;

	/** The password. */
	@Column(name = "password")
	private String password;

	/** The name. */
	@Column(name = "name")
	private String name;

	/** The user type. */
	@Column(name = "user_type")
	private String user_type;

	/** The auth code. */
	@Column(name = "auth_code")
	private String auth_code;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
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
	 * Gets the auth code.
	 *
	 * @return the auth code
	 */
	public String getAuth_code() {
		return auth_code;
	}

	/**
	 * Sets the auth code.
	 *
	 * @param auth_code the new auth code
	 */
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
}
