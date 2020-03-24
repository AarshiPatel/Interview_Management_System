package com.ims.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.entity.User;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find by username and password.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user
	 */
	User findByUsernameAndPassword(String username, String password);

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);

	/**
	 * Find by user id.
	 *
	 * @param userId the user id
	 * @return the user
	 */
	User findByUserId(long userId);

}