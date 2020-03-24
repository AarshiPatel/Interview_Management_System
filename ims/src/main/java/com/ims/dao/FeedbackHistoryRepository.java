package com.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.entity.FeedbackHistory;



/**
 * The Interface FeedbackHistoryRepository.
 */
@Repository
public interface FeedbackHistoryRepository extends JpaRepository<FeedbackHistory, Long> {
	
	/**
	 * Find by feedback history id.
	 *
	 * @param feedbackHistoryId the feedback history id
	 * @return the feedback history
	 */
	FeedbackHistory findByFeedbackHistoryId(Long feedbackHistoryId);
	
	/**
	 * Find by feedback id.
	 *
	 * @param feedbackId the feedback id
	 * @return the feedback history
	 */
	FeedbackHistory findByFeedbackId(Long feedbackId);
	
	/**
	 * Find by candidate id.
	 *
	 * @param candidateId the candidate id
	 * @return the feedback history
	 */
	FeedbackHistory findByCandidateId(Long candidateId);
	
	/**
	 * Gets the accepted candidates.
	 *
	 * @param userId the user id
	 * @return the accepted candidates
	 */
	@Query("from FeedbackHistory feedbackHistory where feedbackHistory.interviewResult='Accept' and feedbackHistory.userId=:userId")
	List<FeedbackHistory> getAcceptedCandidates(@Param("userId") long userId);
	
	/**
	 * Given feedback list.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Query("from FeedbackHistory feedbackHistory where feedbackHistory.userId=:userId")
	List<FeedbackHistory> givenFeedbackList(@Param("userId") long userId);
}
