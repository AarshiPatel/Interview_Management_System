package com.ims.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ims.dto.response.FeedbackResponseDTO;
import com.ims.entity.FeedbackHistory;
import com.ims.entity.FeedbackOFCandidate;

/**
 * The Class UserMapper.
 */
public class FeedbackMapper {

	/**
	 * Instantiates a new user mapper.
	 */
	private FeedbackMapper() {

	}

	/**
	 * Map to user response DTO.
	 *
	 * @param user the user
	 * @return the user response DTO
	 */
	public static FeedbackResponseDTO mapToFeedbackResponseDTO(FeedbackOFCandidate feedback) {
		FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
		feedbackResponseDTO.setInterview(feedback.getInterview().getInterviewId());
		feedbackResponseDTO.setInterviewObject(feedback.getInterview());
		feedbackResponseDTO.setCandidateDepartment(feedback.getCandidate().getCandidateDepartment());
		feedbackResponseDTO.setCandidateDesignation(feedback.getCandidate().getCandidateDesignation());
		feedbackResponseDTO.setQualification(feedback.getCandidate().getQualification());
		feedbackResponseDTO.setCandidate(feedback.getCandidate().getCandidateId());
		feedbackResponseDTO.setInterviewer(feedback.getUserDetails().getUserDetailsId());
		feedbackResponseDTO.setCandidateName(feedback.getCandidateName());
		feedbackResponseDTO.setExperience(feedback.getExperience());
		feedbackResponseDTO.setRound(feedback.getRound());
		feedbackResponseDTO.setDateOfInterview(feedback.getDateOfInterview());
		feedbackResponseDTO.setJobRole(feedback.getJobRole());
		feedbackResponseDTO.setInterviewerName(feedback.getInterviewerName());
		feedbackResponseDTO.setTechnicalSkill(feedback.getTechnicalSkill());
		feedbackResponseDTO.setCommunicationSkill(feedback.getCommunicationSkill());
		feedbackResponseDTO.setOrganizationalSkill(feedback.getOrganizationalSkill());
		feedbackResponseDTO.setEducationSkill(feedback.getEducationSkill());
		feedbackResponseDTO.setHrLeaderAbility(feedback.getHrleaderAbility());
		feedbackResponseDTO.setOverallRating(feedback.getOverallRating());
		feedbackResponseDTO.setReview(feedback.getReview());
		feedbackResponseDTO.setFeedbackId(feedback.getFeedbackId());
		feedbackResponseDTO.setInterviewResult(feedback.getInterviewResult());
		feedbackResponseDTO.setNextRound(feedback.getNextRound());

		return feedbackResponseDTO;

	}

	/**
	 * Map to user response DTO.
	 *
	 * @param users the users
	 * @return the list
	 */
	public static List<FeedbackResponseDTO> mapToFeedbackResponseDTO(List<FeedbackOFCandidate> feedback) {

		return Optional.ofNullable(feedback).orElse(Collections.emptyList()).stream()
				.map(FeedbackMapper::mapToFeedbackResponseDTO).collect(Collectors.toList());

	}

	public static FeedbackResponseDTO mapToFeedbackHistoryResponseDTO(FeedbackHistory feedbackHistory) {
		FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
		feedbackResponseDTO.setCandidateDepartment(feedbackHistory.getCandidateDepartment());
		feedbackResponseDTO.setCandidateDesignation(feedbackHistory.getCandidateDesignation());
		feedbackResponseDTO.setQualification(feedbackHistory.getQualification());
		feedbackResponseDTO.setInterviewer(feedbackHistory.getUserId());

		feedbackResponseDTO.setCandidate(feedbackHistory.getCandidateId());
		feedbackResponseDTO.setInterviewer(feedbackHistory.getUserId());
		feedbackResponseDTO.setCandidateName(feedbackHistory.getCandidateName());
		feedbackResponseDTO.setExperience(feedbackHistory.getExperience());
		feedbackResponseDTO.setRound(feedbackHistory.getRound());
		feedbackResponseDTO.setDateOfInterview(feedbackHistory.getDateOfInterview());
		feedbackResponseDTO.setJobRole(feedbackHistory.getJobRole());
		feedbackResponseDTO.setInterviewerName(feedbackHistory.getInterviewerName());
		feedbackResponseDTO.setTechnicalSkill(feedbackHistory.getTechnicalSkill());
		feedbackResponseDTO.setCommunicationSkill(feedbackHistory.getCommunicationSkill());
		feedbackResponseDTO.setOrganizationalSkill(feedbackHistory.getOrganizationalSkill());
		feedbackResponseDTO.setEducationSkill(feedbackHistory.getEducationSkill());
		feedbackResponseDTO.setHrLeaderAbility(feedbackHistory.getHrleaderAbility());
		feedbackResponseDTO.setOverallRating(feedbackHistory.getOverallRating());
		feedbackResponseDTO.setReview(feedbackHistory.getReview());
		feedbackResponseDTO.setFeedbackId(feedbackHistory.getFeedbackId());
		feedbackResponseDTO.setInterviewResult(feedbackHistory.getInterviewResult());
		feedbackResponseDTO.setNextRound(feedbackHistory.getNextRound());

		return feedbackResponseDTO;

	}

	/**
	 * Map to user response DTO.
	 *
	 * @param users the users
	 * @return the list
	 */
	public static List<FeedbackResponseDTO> mapToFeedbackHistoryResponseDTO(List<FeedbackHistory> feedback) {

		return Optional.ofNullable(feedback).orElse(Collections.emptyList()).stream()
				.map(FeedbackMapper::mapToFeedbackHistoryResponseDTO).collect(Collectors.toList());

	}

}
