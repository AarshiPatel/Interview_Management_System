package com.ims.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.ims.dto.CandidateDTO;
import com.ims.dto.ShowCandidateDTO;
import com.ims.dto.response.CandidateResponseDTO;
import com.ims.entity.Candidate;

public class CandidateMapper {
	private CandidateMapper() {

	}

	public static ShowCandidateDTO mapToCandidateResponseDTO(Candidate candidate) {
		ShowCandidateDTO candidateResponseDTO = new ShowCandidateDTO();
		BeanUtils.copyProperties(candidate, candidateResponseDTO);

		return candidateResponseDTO;

	}
	
	

	public static List<ShowCandidateDTO> mapToCandidateResponseDTO(List<Candidate> candidates) {

		return Optional.ofNullable(candidates).orElse(Collections.emptyList()).stream()
				.map(CandidateMapper::mapToCandidateResponseDTO).collect(Collectors.toList());

	}
	
	public static List<ShowCandidateDTO> mapToCandidateResponseDTOListInterview(List<Candidate> candidates){
		
		ShowCandidateDTO candidateResponseDTO = new ShowCandidateDTO();
		List<ShowCandidateDTO> showCandidateDTO = new ArrayList<>();
		BeanUtils.copyProperties(candidates, candidateResponseDTO);
		
		showCandidateDTO.add(candidateResponseDTO);
		return showCandidateDTO;
		
	}
	
	
	
	public static ShowCandidateDTO mapToCandidateResponseDTOInterview(Candidate candidate) {
		ShowCandidateDTO candidateResponseDTO = new ShowCandidateDTO();
		BeanUtils.copyProperties(candidate, candidateResponseDTO);
		candidateResponseDTO.setName(candidate.getInterview().getInterviewer().getName());
		candidateResponseDTO.setInterviewId(candidate.getInterview().getInterviewId());
		candidateResponseDTO.setInterviewDate(candidate.getInterview().getInterviewDate());
		return candidateResponseDTO;

	}
	
	

	public static List<ShowCandidateDTO> mapToCandidateResponseDTOInterview(List<Candidate> candidates) {

		return Optional.ofNullable(candidates).orElse(Collections.emptyList()).stream()
				.map(CandidateMapper::mapToCandidateResponseDTOInterview).collect(Collectors.toList());

	}
	
	
	public static CandidateDTO mapToCandidateDTO(Candidate candidate) {
		CandidateDTO candidateDTO = new CandidateDTO();
		BeanUtils.copyProperties(candidate, candidateDTO);
		
		return candidateDTO;

	}

	public static List<CandidateDTO> mapToCandidateDTO(List<Candidate> candidates) {

		return Optional.ofNullable(candidates).orElse(Collections.emptyList()).stream()
				.map(CandidateMapper::mapToCandidateDTO).collect(Collectors.toList());

	}


	
	public static CandidateResponseDTO mapToSingleCandidate(Candidate candidate) {
		CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();
		BeanUtils.copyProperties(candidate, candidateResponseDTO);
		if(candidate.getInterview()!=null) {
		candidateResponseDTO.setInterviewDate(candidate.getInterview().getInterviewDate());
		candidateResponseDTO.setInterviewTime(candidate.getInterview().getInterviewTime());
		candidateResponseDTO.setInterviewVenue(candidate.getInterview().getInterviewVenue());
		candidateResponseDTO.setInterviewerDepartment(candidate.getInterview().getInterviewerDepartment());
		candidateResponseDTO.setInterviewerDesignation(candidate.getInterview().getInterviewerDesignation());
		candidateResponseDTO.setInterviewerId(candidate.getInterview().getInterviewer().getUserDetailsId());
		candidateResponseDTO.setInterviewerName(candidate.getInterview().getInterviewer().getName());
		candidateResponseDTO.setRound(candidate.getRound());
		candidateResponseDTO.setInterviewId(candidate.getInterview().getInterviewId());
		}
		return candidateResponseDTO;

	}
	
	
	public static List<ShowCandidateDTO> mapToCandidateResponseDTOGraph(List<Candidate> candidates) {

		return Optional.ofNullable(candidates).orElse(Collections.emptyList()).stream()
				.map(CandidateMapper::mapToCandidateResponseDTOGraph).collect(Collectors.toList());

	}
	

	public static ShowCandidateDTO mapToCandidateResponseDTOGraph(Candidate candidate) {
		ShowCandidateDTO candidateResponseDTO = new ShowCandidateDTO();
		if(candidate.getInterview()!=null) {
		BeanUtils.copyProperties(candidate, candidateResponseDTO);
		candidateResponseDTO.setInterviewId(candidate.getInterview().getInterviewId());
		candidateResponseDTO.setInterviewStatus(candidate.getInterview().getInterviewStatus());
		}else {
			
			BeanUtils.copyProperties(candidate, candidateResponseDTO);
			candidateResponseDTO.setInterviewId((long) 0);		
		}
		return candidateResponseDTO;

	}
	

}
