package com.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.entity.Candidate;
import com.ims.entity.FeedbackOFCandidate;

/**
 * The Interface FeedbackOfCandidateRepository.
 */
@Repository
public interface FeedbackOfCandidateRepository extends JpaRepository<FeedbackOFCandidate, Long> {

	/**
	 * Find by candidate.
	 *
	 * @param candidate the candidate
	 * @return the feedback OF candidate
	 */
	FeedbackOFCandidate findByCandidate(Candidate candidate);

	/**
	 * Given feedback list.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Query("from FeedbackOFCandidate feedbackOFCandidate where feedbackOFCandidate.interview!=null and feedbackOFCandidate.interview.interviewStatus='Accepted' and feedbackOFCandidate.interview.interviewer.user.userId=:userId")
	List<FeedbackOFCandidate> givenFeedbackList(@Param("userId") long userId);

	/**
	 * Find by feedback id.
	 *
	 * @param feedbackId the feedback id
	 * @return the feedback OF candidate
	 */
	FeedbackOFCandidate findByFeedbackId(Long feedbackId);

	/**
	 * Gets the accepted candidates.
	 *
	 * @param userId the user id
	 * @return the accepted candidates
	 */
	@Query("from FeedbackOFCandidate feedbackOFCandidate where feedbackOFCandidate.interviewResult='Accept' and feedbackOFCandidate.interview.interviewer.user.userId=:userId")
	List<FeedbackOFCandidate> getAcceptedCandidates(@Param("userId") long userId);

}
