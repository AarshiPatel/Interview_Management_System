package com.ims.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ims.dao.CandidateRepository;
import com.ims.dao.FeedbackOfCandidateRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.CandidateDTO;
import com.ims.dto.ScheduleInterviewDTO;
import com.ims.dto.ShowCandidateDTO;
import com.ims.dto.request.InformationRequestDTO;
import com.ims.dto.response.CandidateResponseDTO;
import com.ims.dto.response.InformationResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.FeedbackOFCandidate;
import com.ims.entity.Interview;
import com.ims.service.HumanResourseServiceInterface;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

public class HumanResourseControllerTest {

	@InjectMocks
	private HumanResourseController humanResourseController;

	@Mock
	private HumanResourseServiceInterface candidateService;

	@Mock
	private CandidateRepository candidateRepository;

	@Mock
	private FeedbackOFCandidate feedbackOFCandidate;

	@Mock
	private FeedbackOfCandidateRepository feedbackOfCandidateRepository;

	@Mock

	private HttpServletResponse response;

	@Mock
	private UserRepository userRepository;

	private MockMvc mockMvc;

	/** The Constant emailId. */
	private final static String EMAIL_ID = "vahak@gmail.com";

	/** The Constant address. */
	private final static String ADDRESS = "Ahmedabad";

	/** The Constant date. */
	private final static LocalDate DATE = LocalDate.of(1996, 5, 15);

	/** The Constant number. */
	private final static String NUMBER = "9898850516";

	/** The Constant gender. */
	private final static String GENDER = "female";

	/** The Constant name. */
	private final static String NAME = "abcd";

	/** The Constant qualification. */
	private static final String QUALIFICATION = "BE";

	/** The Constant candidateId. */
	private static final long CANDIDATE_ID = 1;

	/** The Constant filePath. */
	private static final String FILE_PATH = "D:\\java.pdf";

	/** The Constant interviewRound. */
	private static final String INTERVIEW_ROUND = "1";

	/** The interview date. */
	private static final LocalDate INTERVIEW_DATE = LocalDate.now();

	/** The interview time. */
	private static final LocalTime INTERVIEW_TIME = LocalTime.now();

	/** The venue. */
	private static final String VENUE = "MCG";

	/** The interviewer department. */
	private static final String INTERVIWER_DEPARTMENT = "PES";

	/** The interviewer job designation. */
	private static final String INTERVIEWER_JOB_DESIGNATON = "Engineer";

	/** The interviewer id. */
	private static final long INTERVIEWER_ID = 23;

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = "PES";

	/** The Constant INFORMATION_TYPE. */
	private static final String INFORMATION_TYPE = "PES";

	/** The Constant INFORMATION_ID. */
	private static final long INFORMATION_ID = 2;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(humanResourseController)
				.addPlaceholderValue("settings.cors_origin", "https://localhost:3000.").build();
	}

	/**
	 * Gets the candidate DTO.
	 *
	 * @return the candidate DTO
	 */
	CandidateDTO getCandidateDTO() {

		CandidateDTO candidateDTO = new CandidateDTO();
		candidateDTO.setAddress(ADDRESS);
		candidateDTO.setDateOfBirth(DATE);
		candidateDTO.setContactNumber(NUMBER);
		candidateDTO.setEmailId(EMAIL_ID);
		candidateDTO.setExperience(2);
		candidateDTO.setGender(GENDER);
		candidateDTO.setCandidateName(NAME);
		candidateDTO.setQualification(QUALIFICATION);
		candidateDTO.setCandidateId(1);
		candidateDTO.setFilepath(FILE_PATH);
		return candidateDTO;
	}

	/**
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	Candidate getCandidate() {
		CandidateDTO candidateDTO = getCandidateDTO();
		Candidate candidate = new Candidate();
		candidate.setAddress(candidateDTO.getAddress());
		candidate.setDateOfBirth(candidateDTO.getDateOfBirth());
		candidate.setContactNumber(candidateDTO.getContactNumber());
		candidate.setEmailId(candidateDTO.getEmailId());
		candidate.setExperience(candidateDTO.getExperience());
		candidate.setGender(candidateDTO.getGender());
		candidate.setCandidateName(candidateDTO.getCandidateName());
		candidate.setQualification(candidateDTO.getQualification());
		candidate.setCandidateId(candidateDTO.getCandidateId());
		candidate.setFilepath(candidateDTO.getFilepath());
		return candidate;

	}



	@Test
	public void addCandidateTest() throws Exception {
		Candidate candidate = getCandidate();

//		when(candidateRepository.findByEmailId(anyString())).thenReturn(null);

		when(candidateService.addCandidate(any())).thenReturn(candidate);

		String inputInJson = this.mapToJson(candidate);
		mockMvc.perform(MockMvcRequestBuilders.post("/addcandidate").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void updateCandidateTest() throws Exception {

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(getCandidate());

		when(candidateService.updateCandidate(anyLong(), any())).thenReturn(getCandidate());

		String inputInJson = this.mapToJson(getCandidateDTO());
		mockMvc.perform(MockMvcRequestBuilders.put("/updatecandidate/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

//	@Test
//	public void updateCandidateNotExistTest() throws Exception {
//
//		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(null);
//
//		when(candidateService.updateCandidate(CANDIDATE_ID, getCandidateDTO())).thenReturn(null);
//
//		String inputInJson = this.mapToJson(getCandidateDTO());
//		mockMvc.perform(MockMvcRequestBuilders.put("/updatecandidate/1").accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//
//	}
//
	@Test
	public void testdeleteCandidate() throws Exception {

		when(candidateRepository.findBycandidateId(anyLong())).thenReturn(null);
		// when(feedbackOfCandidateRepository.findByCandidate(getCandidate())).thenReturn(feedbackOFCandidate);
		when(candidateService.deleteCandidate(CANDIDATE_ID)).thenReturn(null);
		String inputInJson = this.mapToJson(getCandidateDTO());
		mockMvc.perform(MockMvcRequestBuilders.delete("/deletecandidate/1").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	/**
	 * Gets the interview by date.
	 *
	 * @return the interview by date
	 * @throws Exception the exception
	 */
	@Test
	public void getInterviewByDate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/gettodaysinterview")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	


	/**
	 * Test show resume.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testShowResume() throws Exception {

		when(candidateService.getResume(CANDIDATE_ID, response)).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.get("/showresume/" + CANDIDATE_ID + " "))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Test show resume candidate not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testShowResumeCandidateNotFound() throws Exception {

		when(candidateService.getResume(CANDIDATE_ID, response)).thenReturn(false);

		mockMvc.perform(MockMvcRequestBuilders.get("/showresume/" + CANDIDATE_ID + " "))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Test get unscheduled interview.
	 *
	 * This Test Rest API of (/unscheduledinterview)
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGetUnscheduledInterview() throws Exception {
		List<ShowCandidateDTO> listOfCandidate = new ArrayList<>();
		listOfCandidate.add(getShowCandidateDTO());
		when(candidateService.getListOfUnscheduledInterview()).thenReturn(listOfCandidate);
		mockMvc.perform(MockMvcRequestBuilders.get("/unscheduledinterview"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test get one candidate. This Test Rest API of(/getcandidate/{candidateId})
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGetOneCandidate() throws Exception {
		when(candidateService.getCandidate(anyLong())).thenReturn(getCandidateResponseDTO());
		mockMvc.perform(MockMvcRequestBuilders.get("/getcandidate/" + CANDIDATE_ID + " "))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test display candidates.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDisplayCandidates() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/showcandidate")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test schedule interview. This Test Rest API of (/scheduleinterview)
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testScheduleInterview() throws Exception {

		when(candidateService.scheduleInterview(any())).thenReturn(getInterview());
		String inputInJson = this.mapToJson(getInterviewDTO());
		mockMvc.perform(MockMvcRequestBuilders.post("/scheduleinterview").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Test get pendding approval.
	 *
	 * This Test Rest API of (/getpenddingapproval)
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testGetPenddingApproval() throws Exception {
		List<ShowCandidateDTO> listOfCandidate = new ArrayList<>();
		listOfCandidate.add(getShowCandidateDTO());
		when(candidateService.getPenddingInterviewApproval()).thenReturn(listOfCandidate);
		mockMvc.perform(MockMvcRequestBuilders.get("/getpenddingapproval"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Gets the interview DTO.
	 *
	 * @return the interview DTO
	 */
	ScheduleInterviewDTO getInterviewDTO() {
		ScheduleInterviewDTO scheduleInterviewDTO = new ScheduleInterviewDTO();
		scheduleInterviewDTO.setCandidateId(CANDIDATE_ID);
		scheduleInterviewDTO.setRound(INTERVIEW_ROUND);
		scheduleInterviewDTO.setInterviewDate(INTERVIEW_DATE);
		scheduleInterviewDTO.setInterviewerDepartment(INTERVIWER_DEPARTMENT);
		scheduleInterviewDTO.setInterviewerId(INTERVIEWER_ID);
		scheduleInterviewDTO.setInterviewDate(INTERVIEW_DATE);
		scheduleInterviewDTO.setInterviewTime(INTERVIEW_TIME);
		scheduleInterviewDTO.setInterviewerDesignation(INTERVIEWER_JOB_DESIGNATON);
		scheduleInterviewDTO.setInterviewVenue(VENUE);
		return scheduleInterviewDTO;
	}

	/**
	 * Test get list of schduled interview.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetListOfSchduledInterview() throws Exception {
		List<ShowCandidateDTO> listOfCandidate = new ArrayList<>();
		listOfCandidate.add(getShowCandidateDTO());
		when(candidateService.getListOfScheduleInterview()).thenReturn(listOfCandidate);
		mockMvc.perform(MockMvcRequestBuilders.get("/listofscheduleinterview"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test get list of accepted candidate.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetListOfAcceptedCandidate() throws Exception {
		List<ShowCandidateDTO> listOfCandidate = new ArrayList<>();
		listOfCandidate.add(getShowCandidateDTO());
		when(candidateService.getListOfHireCandidates()).thenReturn(listOfCandidate);
		mockMvc.perform(MockMvcRequestBuilders.get("/listofhirecandidate"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test get list of rejeceted candidate.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetListOfRejecetedCandidate() throws Exception {
		List<ShowCandidateDTO> listOfCandidate = new ArrayList<>();
		listOfCandidate.add(getShowCandidateDTO());
		when(candidateService.getListOfRejectedCandidates()).thenReturn(listOfCandidate);
		mockMvc.perform(MockMvcRequestBuilders.get("/listofrejectedcandidate"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test download hire candidate excel excepaction.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDownloadHireCandidateExcelExcepaction() throws Exception {
		when(candidateService.downloadHireCandidateExcel()).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.get("/downloadhirecandidateexcel"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testDownloadHireCandidateExcel() throws Exception {
		when(candidateService.downloadHireCandidateExcel()).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/downloadhirecandidateexcel"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test download reject candidate excel.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDownloadRejectCandidateExcel() throws Exception {
		when(candidateService.downloadRejectCandidateExcel()).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/downloadrejectcandidateexcel"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test download reject candidate excel error.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDownloadRejectCandidateExcelError() throws Exception {
		when(candidateService.downloadRejectCandidateExcel()).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.get("/downloadrejectcandidateexcel"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	/**
	 * Test reschedule interview.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRescheduleInterview() throws Exception {
		when(candidateService.scheduleInterview(any())).thenReturn(getInterview());

		String inputInJson = this.mapToJson(getInterviewDTO());
		mockMvc.perform(MockMvcRequestBuilders.post("/rescheduleinterview").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	/**
	 * Test get information.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetInformation() throws Exception {
		List<InformationResponseDTO> informationList = new ArrayList<>();
		informationList.add(getInformation());

		when(candidateService.getInformation()).thenReturn(informationList);
		mockMvc.perform(MockMvcRequestBuilders.get("/getinformation")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test get list of candidates.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetListOfCandidates() throws Exception {
		List<ShowCandidateDTO> listOfCandidate = new ArrayList<>();
		listOfCandidate.add(getShowCandidateDTO());
		when(candidateService.getListOfUnscheduledInterview()).thenReturn(listOfCandidate);
		mockMvc.perform(MockMvcRequestBuilders.get("/listofcandidate"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testSaveInformation() throws Exception {

		String inputInJson = this.mapToJson(getInformationDTO());
		mockMvc.perform(MockMvcRequestBuilders.post("/saveinfomation").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	/**
	 * Gets the information.
	 *
	 * @return the information
	 */
	InformationResponseDTO getInformation() {
		InformationResponseDTO informationDTO = new InformationResponseDTO();
		informationDTO.setInformationId(INFORMATION_ID);
		return informationDTO;
	}

	/**
	 * Gets the candidate object.
	 *
	 * @return the candidate object
	 */
	Candidate getCandidateObject() {
		Candidate candidate = new Candidate();

		candidate.setAddress(ADDRESS);
		candidate.setDateOfBirth(DATE);
		candidate.setContactNumber(NUMBER);
		candidate.setEmailId(EMAIL_ID);

		candidate.setGender(GENDER);
		candidate.setCandidateName(NAME);
		candidate.setQualification(QUALIFICATION);
		candidate.setCandidateId(CANDIDATE_ID);
		candidate.setFilepath(FILE_PATH);

		return candidate;
	}

	/**
	 * Map to json.
	 *
	 * @param obj the obj
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * Gets the show candidate DTO.
	 *
	 * @return the show candidate DTO
	 */
	ShowCandidateDTO getShowCandidateDTO() {
		ShowCandidateDTO candidate = new ShowCandidateDTO();

		candidate.setDateOfBirth(DATE);
		candidate.setContactNumber(NUMBER);
		candidate.setEmailId(EMAIL_ID);

		candidate.setGender(GENDER);
		candidate.setCandidateName(NAME);
		candidate.setQualification(QUALIFICATION);
		candidate.setCandidateId(CANDIDATE_ID);

		return candidate;
	}

	/**
	 * Gets the candidate response DTO.
	 *
	 * @return the candidate response DTO
	 */
	CandidateResponseDTO getCandidateResponseDTO() {

		CandidateResponseDTO candidateDTO = new CandidateResponseDTO();

		candidateDTO.setCandidateId(1);

		return candidateDTO;
	}

//	@Test
//	public void TestGetListOfUnscheduleInterview() throws Exception {
//		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
//		listOfCandidate.add(getCandidateObject());
//		when(candidateService.getListOfHireCandidates()).thenReturn(listOfCandidate);
//		mockMvc.perform(MockMvcRequestBuilders.get("/listOfUnscheduleInterview"))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

	/**
	 * Gets the information DTO.
	 *
	 * @return the information DTO
	 */
	InformationRequestDTO getInformationDTO() {
		InformationRequestDTO information = new InformationRequestDTO();
		information.setGroupName(GROUP_NAME);
		information.setInformationType(INFORMATION_TYPE);
		return information;
	}

	Interview getInterview() {
		Interview scheduleInterviewDTO = new Interview();

		scheduleInterviewDTO.setInterviewDate(INTERVIEW_DATE);

		scheduleInterviewDTO.setInterviewDate(INTERVIEW_DATE);
		scheduleInterviewDTO.setInterviewTime(INTERVIEW_TIME);

		scheduleInterviewDTO.setInterviewVenue(VENUE);
		return scheduleInterviewDTO;
	}
}