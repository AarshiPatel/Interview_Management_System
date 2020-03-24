package com.ims.utils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ims.dao.CandidateRepository;
import com.ims.dao.FeedbackOfCandidateRepository;
import com.ims.dao.HumanResourseRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.InterviewerRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.CandidateDTO;
import com.ims.dto.ScheduleInterviewDTO;
import com.ims.entity.Candidate;
import com.ims.entity.FeedbackOFCandidate;
import com.ims.entity.HumanResource;
import com.ims.entity.Interview;
import com.ims.entity.Interviewer;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.excel.ExcelFile;

/**
 * The Class ExcelFileTestcCase.
 */
@SpringBootTest
public class ExcelFileTestcCase {

	/** The human resourse service. */
	@InjectMocks
	private ExcelFile genExcelFile;

	/** The candidate repository. */
	@Mock
	private CandidateRepository candidateRepository;

	/** The response. */
	@Mock
	private HttpServletResponse response;

	/** The feedback OF candidate. */
	@Mock
	private FeedbackOFCandidate feedbackOFCandidate;

	/** The feedback of candidate repository. */
	@Mock
	private FeedbackOfCandidateRepository feedbackOfCandidateRepository;

	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/** The human resourse repository. */
	@Mock
	private HumanResourseRepository humanResourseRepository;

	/** The interviewer repository. */
	@Mock
	InterviewerRepository interviewerRepository;

	@Mock
	private InterviewRepository interviewRepository;

	@Mock
	private ExcelFile genrateExcel;
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

	/** The Constant department. */
	private final static String DEPARTMENT = "PES";

	/** The Constant designation. */
	private final static String DESIGNATION = "Fresher";

	/** The Constant qualification. */
	private static final String QUALIFIATION = "BE";

	/** The Constant candidateId. */
	private static final long CANDIDATE_ID = 1;

	/** The Constant experience. */
	private static final int EXPERIENCE = 2;

	/** The Constant filePath. */
	private static final String FILE_PATH = "C:\\Users\\kalgi.shah\\Documents\\PassLeader 1Z0-808 Exam Dumps (1-20).pdf";
	// Path filePath = Paths.get( "java.pdf" );

	// Path file =

	/** The Constant convertToByte. */
	private static final String CONVERT_TO_BYTE = "Dummy";

	/** The Constant interviewRound. */
	private static final String INTERVIEW_ROUND = "1";

	/** The interview date. */
	private static final LocalDate INTERVIEW_DATE = LocalDate.now();

	/** The interview time. */
	private static final LocalTime INTERVIEW_TIME = LocalTime.now();

	/** The venue. */
	private static final String VENUE = "MCG";

	/** The interviewer department. */
	private static final String INTERVIEWER_DEPARTMENT = "PES";

	/** The interviewer job designation. */
	private static final String INTERVIEWER_DESIGNATION = "Engineer";

	/** The interviewer id. */
	private static final long INTERVIEWER_ID = 23;

	/** The user id. */
	private static final long USER_ID = 1;

	private static final String REJECTED_CANDIDATE = "Reject";

	private static final long INTERVIEW_ID = 11;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
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
		candidateDTO.setQualification(QUALIFIATION);
		candidateDTO.setCandidateId(1);
		candidateDTO.setFilepath(FILE_PATH);
		candidateDTO.setCandidateDepartment(DEPARTMENT);
		candidateDTO.setCandidateDesignation(DESIGNATION);

//		byte[] resume = convertToByte.getBytes();
//		candidateDTO.setResume(resume);

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
		candidateDTO.setCandidateDepartment(DEPARTMENT);
		candidateDTO.setCandidateDesignation(DESIGNATION);

		candidate.setFilepath(candidateDTO.getFilepath());
		candidate.setInterview(getInterview());
		return candidate;

	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	User getUser() {
		User user = new User();
		user.setUserId(USER_ID);
		user.setUsername(EMAIL_ID);
		return user;
	}

	Interview getInterview() {
		Interview interview = new Interview();
		interview.setInterviewId(INTERVIEW_ID);
		interview.setInterviewer(getInterviewer());
		return interview;
	}

	/**
	 * Gets the human resource.
	 *
	 * @return the human resource
	 */
	HumanResource getHumanResource() {
		HumanResource humanResource = new HumanResource();
		humanResource.setHrId(2);
		humanResource.setUser(getUser());
		return humanResource;
	}

	/**
	 * Gets the interviewer.
	 *
	 * @return the interviewer
	 */
	UserDetails getInterviewer() {
		UserDetails interviewer = new UserDetails();
		interviewer.setUserDetailsId(INTERVIEWER_ID);
		return interviewer;
	}

	/**
	 * Gets the candidate object.
	 *
	 * @return the candidate object
	 */
	Candidate getCandidateObject() {
		Candidate candidate = new Candidate();

		byte[] resume = CONVERT_TO_BYTE.getBytes();
		candidate.setAddress(ADDRESS);
		candidate.setDateOfBirth(DATE);
		candidate.setContactNumber(NUMBER);
		candidate.setEmailId(EMAIL_ID);
		candidate.setExperience(EXPERIENCE);
		candidate.setGender(GENDER);
		candidate.setCandidateName(NAME);
		candidate.setQualification(QUALIFIATION);
		candidate.setCandidateId(CANDIDATE_ID);
		candidate.setFilepath(FILE_PATH);
		candidate.setResume(resume);
		candidate.setInterview(getInterview());
		return candidate;
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
		scheduleInterviewDTO.setInterviewerDepartment(INTERVIEWER_DEPARTMENT);
		scheduleInterviewDTO.setInterviewerId(INTERVIEWER_ID);
		scheduleInterviewDTO.setInterviewDate(INTERVIEW_DATE);
		scheduleInterviewDTO.setInterviewTime(INTERVIEW_TIME);
		scheduleInterviewDTO.setInterviewerDesignation(INTERVIEWER_DESIGNATION);
		scheduleInterviewDTO.setInterviewVenue(VENUE);
		scheduleInterviewDTO.setUserId(USER_ID);
		return scheduleInterviewDTO;
	}

	/**
	 * Genrate excel.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testDownloadCandidateExcel() throws IOException {
		ServletOutputStream outputStream = new ServletOutputStream() {

			@Override
			public boolean isReady() {

				return false;
			}

			@Override
			public void setWriteListener(WriteListener listener) {

			}

			@Override
			public void write(int b) throws IOException {

			}

		};
		List<Candidate> listOfCandidate = new ArrayList<Candidate>();
		Candidate candidate = getCandidate();
		candidate.setInterviewResult(REJECTED_CANDIDATE);
		listOfCandidate.add(candidate);
		when(response.getOutputStream()).thenReturn(outputStream);
		assertTrue(genExcelFile.downloadCandidateExcel(response, listOfCandidate));

	}

}
