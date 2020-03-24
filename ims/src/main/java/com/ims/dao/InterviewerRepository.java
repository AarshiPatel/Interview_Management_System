package com.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ims.entity.Interviewer;
import com.ims.entity.User;

/**
 * The Interface InterviewerRepository.
 */
@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

	/**
	 * Find by interviewer id.
	 *
	 * @param interviewerId the interviewer id
	 * @return the interviewer
	 */
	Interviewer findByInterviewerId(long interviewerId);

	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the interviewer
	 */
	Interviewer findByUser(User user);

	/**
	 * Find all Interviewer. This method is used to get interviewer list in
	 * scheduled interview dropdown.
	 * 
	 * @return the list
	 */
	
	@Query("from UserDetails userdetails where userdetails.activeFlag!=0  and userdetails.user_type='Interviewer'")
	List<Interviewer> findAll();
	

}