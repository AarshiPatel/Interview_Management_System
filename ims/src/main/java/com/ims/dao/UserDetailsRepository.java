package com.ims.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ims.entity.User;
import com.ims.entity.UserDetails;


/**
 * The Interface UserDetailsRepository.
 */
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	/**
	 * Find all admin.
	 *
	 * @return the list
	 */
	@Query("from  UserDetails userDetails where userDetails.user_type = 'Admin' and userDetails.user!=null ")
	List<UserDetails> findAllAdmin();

	/**
	 * Find all interviewer.
	 *
	 * @return the list
	 */
	@Query("from  UserDetails userDetails where userDetails.user_type = 'Interviewer' and userDetails.user!=null")
	List<UserDetails> findAllInterviewer();

	/**
	 * Find all human resource.
	 *
	 * @return the list
	 */
	@Query("from  UserDetails userDetails where userDetails.user_type = 'HumanResource' and userDetails.user!=null ")
	List<UserDetails> findAllHumanResource();

	/**
	 * Find all manager.
	 *
	 * @return the list
	 */
	@Query("from  UserDetails userDetails where userDetails.user_type = 'Manager' and userDetails.user!=null ")
	List<UserDetails> findAllManager();

	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the user details
	 */
	UserDetails findByUser(User user);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Query("from UserDetails userdetails where userdetails.activeFlag!=0 and userdetails.user_type='Interviewer' ")
	List<UserDetails> findAll();

	/**
	 * Find byuser details id.
	 *
	 * @param id the id
	 * @return the user details
	 */
	UserDetails findByuserDetailsId(long id);

}