package com.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.entity.Candidate;


/**
 * The Interface CandidateRepository.
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	/**
	 * Find bycandidate id.
	 *
	 * @param candidateId the candidate id
	 * @return the candidate
	 */

	Candidate findBycandidateId(long candidateId);

	/**
	 * Find by email id.
	 *
	 * @param emailId the email id
	 * @return the candidate
	 */
	Candidate findByEmailId(String emailId);

	/**
	 * Gets the candidate unscheduled interview.
	 *
	 * @return the candidate unscheduled interview
	 */

	@Query("from Candidate candidate where candidate.interview=null and candidate.interviewResult='NAN'")
	List<Candidate> getCandidateUnscheduledInterview();

	/**
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	@Query("from  Candidate candidate where candidate.flag!=1")
	List<Candidate> getCandidates();

	/**
	 * Gets the candidate pending interview approval.
	 *
	 * @return the candidate pending interview approval
	 */
	@Query("from Candidate candidate where candidate.interview!=null and candidate.interview.interviewStatus='scheduled'")
	List<Candidate> getCandidatePendingInterviewApproval();

	/**
	 * Gets the candidate schedule interview.
	 *
	 * @return the candidate schedule interview
	 */
	@Query("from Candidate candidate where candidate.interview!=null")
	List<Candidate> getCandidateScheduleInterview();

	/**
	 * Gets the scheduled intervew list.
	 *
	 * @return the scheduled intervew list
	 */
	@Query("from Candidate candidate where candidate.interview!=null and candidate.interviewResult!='Reject' and candidate.interview.interviewStatus='Accepted' and candidate.interviewResult!='Hire'")
	List<Candidate> getScheduledIntervewList();

	/**
	 * Gets the list of accepted candidates.
	 *
	 * @return the list of accepted candidates
	 */
	@Query("from Candidate candidate where candidate.interview!=null and candidate.interviewResult='Hire'")
	List<Candidate> getListOfAcceptedCandidates();

	/**
	 * Gets the hired candidates.
	 *
	 * @param userId the user id
	 * @return the hired candidates
	 */
	@Query("from Candidate candidate where candidate.interview!=null and candidate.interviewResult='Hire' and candidate.interview.interviewer.user.userId=:userId")
	List<Candidate> getHiredCandidates(@Param("userId") long userId);

	/**
	 * Gets the rejected candidates.
	 *
	 * @param userId the user id
	 * @return the rejected candidates
	 */
	@Query("from Candidate candidate where candidate.interview!=null and candidate.interviewResult='Reject' and candidate.interview.interviewer.user.userId=:userId")
	List<Candidate> getRejectedCandidates(@Param("userId") long userId);

	/**
	 * Gets the accepted candidates.
	 *
	 * @param userId the user id
	 * @return the accepted candidates
	 */
	@Query("from Candidate candidate where candidate.interview>1 and candidate.interviewResult='NAN' and candidate.interview.interviewer.user.userId=:userId")
	List<Candidate> getAcceptedCandidates(@Param("userId") long userId);

	/**
	 * Gets the list of rejected candidates.
	 *
	 * @return the list of rejected candidates
	 */
	@Query("from Candidate candidate where candidate.interview!=null and candidate.interviewResult='Reject'")
	List<Candidate> getListOfRejectedCandidates();

	/**
	 * Scheduled list.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Query("from Candidate candidate where candidate.interview!=null and candidate.interview.interviewStatus='Accepted' and candidate.interviewResult!='Hire' and candidate.interview.interviewer.user.userId=:userId")
	List<Candidate> scheduledList(@Param("userId") long userId);

	/**
	 * Candidate pending interview approval.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Query("from Candidate candidate where candidate.interview!=null and candidate.interview.interviewStatus='scheduled' and candidate.interview.interviewer.user.userId=:userId")
	List<Candidate> candidatePendingInterviewApproval(@Param("userId") long userId);

	/**
	 * Gets the candidate list.
	 *
	 * @return the candidate list
	 */
	@Query("from Candidate candidate ")
	List<Candidate> getCandidateList();

}
