package com.ims.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.CandidateRepository;
import com.ims.dao.FeedbackOfCandidateRepository;
import com.ims.dao.InformationRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.UserDetailsRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.CandidateDTO;
import com.ims.dto.ScheduleInterviewDTO;
import com.ims.dto.ShowCandidateDTO;
import com.ims.dto.request.InformationRequestDTO;
import com.ims.dto.response.CandidateResponseDTO;
import com.ims.dto.response.InformationResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.FeedbackOFCandidate;
import com.ims.entity.Information;
import com.ims.entity.Interview;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.excel.ExcelFile;
import com.ims.exception.ConflictException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.mapper.CandidateMapper;
import com.ims.mapper.InformationMapper;
import com.ims.properties.ConstantProperties;
import com.ims.service.HumanResourseServiceInterface;
import com.ims.util.DateOfBirthUtil;
import com.ims.util.NameUtil;

@Service
public class HumanResourseServiceImpl implements HumanResourseServiceInterface {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(HumanResourseServiceImpl.class);

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private FeedbackOfCandidateRepository feedbackOfCandidateRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InterviewRepository interviewRepository;

	@Autowired
	private ExcelFile genrateExcel;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private NameUtil nameUtil;

	@Autowired
	private DateOfBirthUtil dateOfBirthUtil;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private InformationRepository informationRepository;

	/**
	 * Adds the candidate.
	 *
	 * @param candidateDTO the candidate DTO
	 * @return the candidate
	 * @throws IOException         Signals that an I/O exception has occurred.
	 * @throws ConflictException
	 * @throws UnprocessableEntity
	 */
	@Override
	public Candidate addCandidate(CandidateDTO candidateDTO)
			throws IOException, ConflictException, UnprocessableEntity {

		Candidate candidateEmailId = candidateRepository.findByEmailId(candidateDTO.getEmailId());
		boolean validEmailId = nameUtil.isValidName(candidateDTO.getEmailId());
		boolean validAge = dateOfBirthUtil.isValidAge(candidateDTO.getDateOfBirth());

		if (!validEmailId || !validAge) {
			throw new UnprocessableEntity("Fields are not correct");

		}

		if (Objects.nonNull(candidateEmailId)) { 
			logger.info("Something Went wrong for candidate whoes id is{}", candidateDTO.getCandidateId());
			logger.info("Something Went wrong for candidate whoes id is{}", candidateDTO.getCandidateId());
			throw new ConflictException(ConstantProperties.CONFLICT);
		}

		Candidate candidate = new Candidate();

		BeanUtils.copyProperties(candidateDTO, candidate);
		candidate.setRound(ConstantProperties.FLAG_ONE);
		candidate.setFlag(ConstantProperties.FLAG_ZERO);
		candidate.setInterviewResult(ConstantProperties.INTERVIEW_RESULT);

		byte[] candidateResume = Files.readAllBytes(new File(candidateDTO.getFilepath()).toPath());
		candidate.setResume(candidateResume);
		candidateRepository.save(candidate);
		return candidate;

	}

	/**
	 * Gets the resume. this method is used to get resume of candidate
	 * 
	 * @param candidateId the candidate id
	 * @param response    the response
	 * @return the resume
	 */
	@Override
	public boolean getResume(long candidateId, HttpServletResponse response) {
		Candidate candidate = candidateRepository.findBycandidateId(candidateId);
		if (Objects.nonNull(candidate)) {

			try {

				byte[] bytes = candidate.getResume();
				response.setContentType("application/pdf");
				OutputStream outputStram = response.getOutputStream();
				outputStram.write(bytes);
				outputStram.flush();
				outputStram.close();
				return true;

			} catch (Exception exception) {
				return false;
			}

		}
		return false;

	}

	/**
	 * Update candidate. This method is used to update the fields of candidate.
	 * 
	 * @param candidateId  the candidate id
	 * @param candidateDTO the candidate DTO
	 * @return the candidate
	 * @throws IOException               Signals that an I/O exception has occurred.
	 * @throws ResourceNotFoundException
	 */
	@Override
	public Candidate updateCandidate(long candidateId, CandidateDTO candidateDTO)
			throws IOException, ResourceNotFoundException {

		Candidate candidate = candidateRepository.findBycandidateId(candidateId);

		if (Objects.nonNull(candidate)) {
			int round = candidate.getRound();
			String address = candidate.getAddress();
			String gender = candidate.getGender();
			String interviewResult=candidate.getInterviewResult();
			byte[] candidateResume = Files.readAllBytes(new File(candidate.getFilepath()).toPath());

			BeanUtils.copyProperties(candidateDTO, candidate);
			candidate.setRound(round);
			candidate.setAddress(address);
			candidate.setGender(gender);
			candidate.setInterviewResult(interviewResult);
			candidate.setResume(candidateResume);
			
			try {

				byte[] file = Files.readAllBytes(new File(candidateDTO.getFilepath()).toPath());
				candidate.setResume(file);

			} catch (Exception excepaction) {

				logger.info("File is not uploaded");
			}

			candidateRepository.save(candidate);

			return candidate;
		}
		throw new ResourceNotFoundException("CandidateId is not in database.");

	}

	/**
	 * Delete candidate. This method used to delete the candidate.
	 * 
	 * @param candidateId the candidate id
	 * @return the candidate
	 * @throws ResourceNotFoundException
	 */
	@Override
	public Candidate deleteCandidate(long candidateId) throws ResourceNotFoundException {

		Candidate candidate = candidateRepository.findBycandidateId(candidateId);
		if (!Objects.isNull(candidate)) {

			FeedbackOFCandidate feedbackOFCandidate = feedbackOfCandidateRepository.findByCandidate(candidate);
			if (feedbackOFCandidate != null) {

				long feedbackId = feedbackOFCandidate.getFeedbackId();
				feedbackOfCandidateRepository.deleteById(feedbackId);
			}
			candidateRepository.deleteById(candidateId);
			return candidate;
		}
		throw new ResourceNotFoundException(ConstantProperties.RESOURCE_CANDIDATENOTFOUND);

	}

	/**
	 * Gets the list of unscheduled interview. This method use to get list of
	 * unscheduled interview
	 * 
	 * @return the list of unscheduled interview
	 */
	@Override
	public List<ShowCandidateDTO> getListOfUnscheduledInterview() {
		return CandidateMapper.mapToCandidateResponseDTO(candidateRepository.getCandidateUnscheduledInterview());

	}

	/**
	 * Gets the candidate. This method is use to get one candidate using candidateId
	 * 
	 * @param candidateId the candidate id
	 * @return the candidate
	 * @throws ResourceNotFoundException
	 */
	@Override
	public CandidateResponseDTO getCandidate(long candidateId) throws ResourceNotFoundException {

		Candidate candidate = candidateRepository.findBycandidateId(candidateId);
		if (Objects.nonNull(candidate)) {
			return CandidateMapper.mapToSingleCandidate(candidate);
		}
		throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);

	}

	/**
	 * Schedule interview. This method is used to schedule interview of candidate
	 * with interviewer.
	 * 
	 * @param interviewDTO the interview
	 * @return true, if successful
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@Override
	public Interview scheduleInterview(ScheduleInterviewDTO interviewDTO)
			throws UnprocessableEntity, ResourceNotFoundException {
		if (interviewDTO.getUserId() != ConstantProperties.INVALID_ID && interviewDTO.getInterviewerDepartment() != null
				&& interviewDTO.getInterviewerDesignation() != null
				&& interviewDTO.getInterviewerId() != ConstantProperties.INVALID_ID
				&& interviewDTO.getInterviewVenue() != null) {
			User user = userRepository.findByUserId(interviewDTO.getUserId());

			UserDetails humanResource = userDetailsRepository.findByUser(user);
			if (Objects.nonNull(humanResource)) {

				Interview scheduleInterview = new Interview();
				BeanUtils.copyProperties(interviewDTO, scheduleInterview);
				scheduleInterview.setFlag(ConstantProperties.FLAG_ZERO);
				scheduleInterview.setInterviewStatus(ConstantProperties.INTERVIEW_SCHEDULED);
				Candidate candidate = candidateRepository.findBycandidateId(interviewDTO.getCandidateId());
				UserDetails interviewer = userDetailsRepository.findByuserDetailsId(interviewDTO.getInterviewerId());

				scheduleInterview.setInterviewer(interviewer);
				interviewRepository.save(scheduleInterview);
				candidate.setInterview(scheduleInterview);
				candidate.setHumanResource(humanResource);
				candidateRepository.save(candidate);

				return scheduleInterview;
			}
			throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
		}
		throw new UnprocessableEntity(ConstantProperties.FEILDS_MANDATORY_ERROR);

	}

	/**
	 * Gets the pendding interview approval.
	 *
	 * @return the pendding interview approval
	 */
	@Override
	public List<ShowCandidateDTO> getPenddingInterviewApproval() {
		return CandidateMapper
				.mapToCandidateResponseDTOInterview(candidateRepository.getCandidatePendingInterviewApproval());

	}

	/**
	 * Gets the list of schedule interview.
	 *
	 * @return the list of schedule interview
	 */
	@Override
	public List<ShowCandidateDTO> getListOfScheduleInterview() {
		return CandidateMapper.mapToCandidateResponseDTOInterview(candidateRepository.getScheduledIntervewList());

	}

	/**
	 * Gets the list OF accepted candidates.
	 *
	 * @return the list OF accepted candidates
	 */

	/**
	 * Download hire candidate excel.
	 *
	 * @param response the response
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override

	public boolean downloadHireCandidateExcel() throws IOException {

		List<Candidate> candidate = candidateRepository.getListOfAcceptedCandidates();
		boolean isExcelGenrate = genrateExcel.downloadCandidateExcel(response, candidate);
		if (isExcelGenrate) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the list of rejected candidates.
	 *
	 * @return the list of rejected candidates
	 */
	@Override
	public List<ShowCandidateDTO> getListOfRejectedCandidates() {

		return CandidateMapper.mapToCandidateResponseDTO(candidateRepository.getListOfRejectedCandidates());
	}

	/**
	 * Download reject candidate excel.
	 *
	 * @param response the response
	 * @return true, if successful
	 * @throws IOException
	 */
	@Override
	public boolean downloadRejectCandidateExcel() throws IOException {

		List<Candidate> candidate = candidateRepository.getListOfRejectedCandidates();
		boolean isExcelGenrate = genrateExcel.downloadCandidateExcel(response, candidate);
		if (isExcelGenrate) {
			return true;
		}
		return false;

	}

	/**
	 * Reschedule interview.
	 *
	 * @param interview the interview
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@Override
	public Interview rescheduleInterview(ScheduleInterviewDTO interview)
			throws UnprocessableEntity, ResourceNotFoundException {
		if (interview.getUserId() != ConstantProperties.INVALID_ID && interview.getInterviewerDepartment() != null
				&& interview.getInterviewerDesignation() != null
				&& interview.getInterviewerId() != ConstantProperties.INVALID_ID
				&& interview.getInterviewVenue() != null) {
			User user = userRepository.findByUserId(interview.getUserId());
			UserDetails humanResource = userDetailsRepository.findByUser(user);

			if (Objects.nonNull(humanResource)) {
				Candidate candidate = candidateRepository.findBycandidateId(interview.getCandidateId());
				Interview oldInterview = interviewRepository
						.findByInterviewId(candidate.getInterview().getInterviewId());
				oldInterview.setFlag(ConstantProperties.FLAG_ONE);
				interviewRepository.save(oldInterview);
				Interview newInterview = new Interview();
				BeanUtils.copyProperties(interview, newInterview);
				newInterview.setFlag(ConstantProperties.FLAG_ZERO);
				newInterview.setInterviewStatus(ConstantProperties.INTERVIEW_SCHEDULED);
				Candidate newCandidate = candidateRepository.findBycandidateId(interview.getCandidateId());
				UserDetails interviewer = userDetailsRepository.findByuserDetailsId(interview.getInterviewerId());

				newInterview.setInterviewer(interviewer);
				interviewRepository.save(newInterview);
				newCandidate.setInterview(newInterview);
				newCandidate.setHumanResource(humanResource);
				candidateRepository.save(newCandidate);

				return newInterview;
			}
			throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);
		}

		throw new UnprocessableEntity(ConstantProperties.FEILDS_MANDATORY_ERROR);

	}

	@Override
	public List<ShowCandidateDTO> getListOfHireCandidates() {
		return CandidateMapper.mapToCandidateResponseDTO(candidateRepository.getListOfAcceptedCandidates());

	}

	@Override
	public List<ShowCandidateDTO> getListOfNotScheduleInterview() {

		return CandidateMapper.mapToCandidateResponseDTO(candidateRepository.getCandidateUnscheduledInterview());
	}

	@Override
	public List<ShowCandidateDTO> getCandidates() {

		return CandidateMapper.mapToCandidateResponseDTO(candidateRepository.getCandidates());

	}

	/**
	 * Gets the interview of today.
	 *
	 * @return the interview of today
	 */
	@Override
	public List<ShowCandidateDTO> getInterviewOfToday() {

		List<ShowCandidateDTO> candidateList = new ArrayList<>();

		LocalDate dateOfToday = LocalDate.now();

		List<ShowCandidateDTO> candidate = CandidateMapper
				.mapToCandidateResponseDTOInterview(candidateRepository.getScheduledIntervewList());

		for (ShowCandidateDTO candidateDTO : candidate) {

			LocalDate interviewDate = candidateDTO.getInterviewDate();

			if (dateOfToday.equals(interviewDate)) {

				candidateList.add(candidateDTO);
			}
		}
		return candidateList;

	}

	/**
	 * Gets the candidate list.
	 *
	 * @return the candidate list
	 */
	@Override
	public List<ShowCandidateDTO> getCandidateList() {
		return CandidateMapper.mapToCandidateResponseDTOGraph(candidateRepository.getCandidateList());

	}

	/**
	 * Save infomation.
	 *
	 * @param infomation the infomation
	 */
	@Override
	public void saveInfomation(InformationRequestDTO infomation) {
		Information saveInformation = new Information();
		BeanUtils.copyProperties(infomation, saveInformation);
		informationRepository.save(saveInformation);

	}

	/**
	 * Gets the information.
	 *
	 * @return the information
	 */
	@Override
	public List<InformationResponseDTO> getInformation() {
		

		return InformationMapper.mapToCandidateResponseDTO(informationRepository.findAll());

	}

}
