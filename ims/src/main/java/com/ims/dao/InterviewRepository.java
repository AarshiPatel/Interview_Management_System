package com.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.entity.Interview;
import com.ims.entity.UserDetails;

/**
 * The Interface InterviewerRepository.
 */
@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

	/**
	 * Gets the interview by interviewer id.
	 *
	 * @param interviewer the interviewer
	 * @return the interview by interviewer id
	 */
	@Query("from Interview interviewer  where interviewer.interviewer=:interviewer")
	List<Interview> getInterviewByInterviewerId(@Param("interviewer") UserDetails interviewer);

	/**
	 * Gets the interview by user id.
	 *
	 * @param interviewer the interviewer
	 * @return the interview by user id
	 */
	@Query("from Interview interviewer  where interviewer.interviewer=:interviewer")
	List<Interview> getInterviewByUserId(@Param("interviewer") UserDetails interviewer);

	/**
	 * Gets the particular interview by interviewer id.
	 *
	 * @param userId the user id
	 * @return the particular interview by interviewer id
	 */
	@Query("from Interview interview  where interview.flag=1 and interview.interviewer.userDetailsId=:userId")
	List<Interview> getParticularInterviewByInterviewerId(@Param("userId") Long userId);

	/**
	 * Find by interview id.
	 *
	 * @param interview the interview
	 * @return the interview
	 */
	Interview findByInterviewId(long interview);
}