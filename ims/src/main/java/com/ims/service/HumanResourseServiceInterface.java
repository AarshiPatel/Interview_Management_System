package com.ims.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ims.dto.CandidateDTO;
import com.ims.dto.ScheduleInterviewDTO;
import com.ims.dto.ShowCandidateDTO;
import com.ims.dto.request.InformationRequestDTO;
import com.ims.dto.response.CandidateResponseDTO;
import com.ims.dto.response.InformationResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.Interview;
import com.ims.exception.ConflictException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;

public interface HumanResourseServiceInterface {

	/**
	 * Adds the candidate.
	 *
	 * @param candidateDTO the candidate DTO
	 * @return the candidate
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException 
	 * @throws Exception 
	 */
	Candidate addCandidate(CandidateDTO candidateDTO) throws IOException, ConflictException, UnprocessableEntity;

	/**
	 * Update candidate.
	 *
	 * @param candidateId  the candidate id
	 * @param candidateDTO the candidate DTO
	 * @return the candidate
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException 
	 */
	Candidate updateCandidate(long candidateId, CandidateDTO candidateDTO) throws IOException, ResourceNotFoundException;
	
	/**
	 * Gets the interview of today.
	 *
	 * @return the interview of today
	 */
	List<ShowCandidateDTO> getInterviewOfToday();

	/**
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	
	List<ShowCandidateDTO> getCandidates();

	/**
	 * Gets the resume.
	 *
	 * @param candidateId the candidate id
	 * @param response    the response
	 * @return the resume
	 */
	boolean getResume(long candidateId, HttpServletResponse response);

	/**
	 * Gets the list of unscheduled interview.
	 *
	 * @return the list of unscheduled interview
	 */
	List<ShowCandidateDTO> getListOfUnscheduledInterview();

	/**
	 * Delete candidate.
	 *
	 * @param candidateId the candidate id
	 * @return the candidate
	 * @throws ResourceNotFoundException 
	 */
	Candidate deleteCandidate(long candidateId) throws ResourceNotFoundException;

	/**
	 * Gets the candidate.
	 * 
	 * @param id
	 *
	 * @return the candidate
	 * @throws ResourceNotFoundException 
	 */
	CandidateResponseDTO getCandidate(long candidateId) throws ResourceNotFoundException;

	/**
	 * Schedule interview.
	 *
	 * @param interview the interview
	 * @return true, if successful
	 * @throws UnprocessableEntity 
	 * @throws ResourceNotFoundException 
	 */
	Interview scheduleInterview(ScheduleInterviewDTO interview) throws UnprocessableEntity, ResourceNotFoundException;

	/**
	 * Gets the pendding interview approval.
	 *
	 * @return the pendding interview approval
	 */
	List<ShowCandidateDTO> getPenddingInterviewApproval();

	/**
	 * Gets the list of schedule interview.
	 *
	 * @return the list of schedule interview
	 */
	List<ShowCandidateDTO> getListOfScheduleInterview();

	/**
	 * Gets the list OF accepted candidates.
	 *
	 * @return the list OF accepted candidates
	 */
	List<ShowCandidateDTO> getListOfHireCandidates();

	/**
	 * Gets the list of rejected candidates.
	 *
	 * @return the list of rejected candidates
	 */
	List<ShowCandidateDTO> getListOfRejectedCandidates();

	/**
	 * Download excel.
	 * 
	 * @param response
	 * @throws ResourceNotFoundException 
	 */
	boolean  downloadHireCandidateExcel() throws IOException, ResourceNotFoundException;

	/**
	 * Download reject candidate excel.
	 *
	 * @param response the response
	 * @return true, if successful
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	boolean  downloadRejectCandidateExcel() throws IOException, ResourceNotFoundException;

	/**
	 * Reschedule interview.
	 *
	 * @param interview the interview
	 * @throws UnprocessableEntity 
	 * @throws ResourceNotFoundException 
	 */
	Interview rescheduleInterview(ScheduleInterviewDTO interview) throws UnprocessableEntity, ResourceNotFoundException;

	/**
	 * Gets the list of not schedule interview.
	 *
	 * @return the list of not schedule interview
	 */
	List<ShowCandidateDTO> getListOfNotScheduleInterview();

	/**
	 * Gets the candidate list.
	 *
	 * @return the candidate list
	 */
	List<ShowCandidateDTO> getCandidateList();

	/**
	 * Save infomation.
	 *
	 * @param infomation the infomation
	 */
	void saveInfomation(InformationRequestDTO infomation);

	/**
	 * Gets the information.
	 *
	 * @return the information
	 */
	List<InformationResponseDTO> getInformation();

}
