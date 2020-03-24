package com.ims.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.ims.dto.request.JobRequirmentRequestDTO;
import com.ims.dto.response.JobRequirementCounterResponseDTO;
import com.ims.dto.response.JobRequirmentResponseDTO;
import com.ims.entity.JobRequirementCounter;
import com.ims.service.JobRequirmentInterface;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

public class JobRequirmentControllerTest {

	@InjectMocks
	private JobRequirmentController jobRequirmentController;

	@Mock
	private JobRequirmentInterface jobRequirmentService;

	private static final String REQUIRMENT_DEPARTMENT = "PES";
	private static final String REQUIRMENT_JOB_DESIGNATON = "Engineer";
	private static final String REQUIRMENT_SKILL = "Java";
	private static final String REQUIRMENT_GROUP = "QA";
	private static final long MANAGER_ID=1;

	private static final long REQURIMENT_ID = 2;
	private MockMvc mockMvc;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(jobRequirmentController)
				.addPlaceholderValue("settings.cors_origin", "https://localhost:3000.").build();
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
	 * Gets the job requirment DTO.
	 *
	 * @return the job requirment DTO
	 */
	JobRequirmentRequestDTO getJobRequirmentDTO() {
		JobRequirmentRequestDTO jobRequriment = new JobRequirmentRequestDTO();
		jobRequriment.setRequirementDepartment(REQUIRMENT_DEPARTMENT);
		jobRequriment.setRequirementDesignation(REQUIRMENT_JOB_DESIGNATON);
		jobRequriment.setRequirmentName(REQUIRMENT_SKILL);
		jobRequriment.setRequirementGroup(REQUIRMENT_GROUP);
		return jobRequriment;

	}

	/**
	 * Test postjobrequirment.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPostjobrequirment() throws Exception {

		when(jobRequirmentService.addJobRequirement(any())).thenReturn(getJobRequirment());
		String inputInJson = this.mapToJson(getJobRequirmentDTO());
		mockMvc.perform(MockMvcRequestBuilders.post("/postjobrequirment").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

//Aarshi Home 23-03-2020	
	@Test
	public void testUpdatePostjobrequirment() throws Exception {

		when(jobRequirmentService.updateJobRequirement(any())).thenReturn(getRequrimentCounter());
		String inputInJson = this.mapToJson(getJobRequirmentDTO());
		mockMvc.perform(MockMvcRequestBuilders.post("/updatejobrequirment").accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Test get job requirment.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetJobRequirment() throws Exception {
		List<JobRequirmentResponseDTO> jobRequirmentResponseDTOList = new ArrayList<>();
		jobRequirmentResponseDTOList.add(getJobRequirment());
		when(jobRequirmentService.getJobRequirementList()).thenReturn(jobRequirmentResponseDTOList);
		mockMvc.perform(MockMvcRequestBuilders.get("/getjobpostedrequirment"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	

	
	@Test
	public void testShowRequirement() throws Exception{
		List<JobRequirementCounterResponseDTO> jobRequirmentResponseDTOList = new ArrayList<>();
		jobRequirmentResponseDTOList.add(getJobRequirmentCounter());
		when(jobRequirmentService.showJobRequirementCounter(anyString(),anyString() )).thenReturn(jobRequirmentResponseDTOList);
		mockMvc.perform(MockMvcRequestBuilders.get("/getspecificrequirements/Digital/PES"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testShowspecificRequirementswithId() throws Exception {
		JobRequirementCounterResponseDTO jobRequirmentResponseDTO =getJobRequirmentCounter();
		when(jobRequirmentService.showRequirementWithId(anyLong())).thenReturn(getJobRequirmentCounter());
		mockMvc.perform(MockMvcRequestBuilders.get("/getspecificrequirementsbyid/1"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

//Aarshi Home 23-03-2020	
	@Test
	public void testGetJobRequirmentPendingApprovalByManagerId() throws Exception {
		List<JobRequirmentResponseDTO> jobRequirmentResponseDTOList = new ArrayList<>();
		jobRequirmentResponseDTOList.add(getJobRequirment());
		when(jobRequirmentService.getJobRequirementPendingApprovalListByManageId(anyLong())).thenReturn(jobRequirmentResponseDTOList);
		mockMvc.perform(MockMvcRequestBuilders.get("/getjobrequirmentpendingapprovalbyid/"+MANAGER_ID+""))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	//Aarshi Home 24-03-2020	
		@Test
		public void testGetApprovedJobRequirmentByManagerId() throws Exception {
			List<JobRequirmentResponseDTO> jobRequirmentResponseDTOList = new ArrayList<>();
			jobRequirmentResponseDTOList.add(getJobRequirment());
			when(jobRequirmentService.getJobRequirementPendingApprovalListByManageId(anyLong())).thenReturn(jobRequirmentResponseDTOList);
			mockMvc.perform(MockMvcRequestBuilders.get("/getapprovejobrequirmentbyid/"+MANAGER_ID+""))
					.andExpect(MockMvcResultMatchers.status().isOk());
		}
		
	
	
	//Aarshi Home 24-03-2020
	@Test
	public void testetJobRequirmentById() throws Exception {
	
		when(jobRequirmentService.getJobRequirementById(anyLong())).thenReturn(getJobRequirment());
		mockMvc.perform(MockMvcRequestBuilders.get("/getonejobrequirementbyid/"+REQURIMENT_ID+""))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	/**
	 * Gets the requriment counter.
	 *
	 * @return the requriment counter
	 */
	JobRequirementCounter getRequrimentCounter() {
		JobRequirementCounter counter = new JobRequirementCounter();
		return counter;
	}

	/**
	 * Gets the job requriment.
	 *
	 * @return the job requirment
	 */
	JobRequirmentResponseDTO getJobRequirment() {
		JobRequirmentResponseDTO jobRequriment = new JobRequirmentResponseDTO();
		jobRequriment.setRequirementDepartment(REQUIRMENT_DEPARTMENT);
		jobRequriment.setRequirementDesignation(REQUIRMENT_JOB_DESIGNATON);
		jobRequriment.setRequirmentName(REQUIRMENT_SKILL);
		jobRequriment.setRequirementGroup(REQUIRMENT_GROUP);

		return jobRequriment;
	}
	
	JobRequirementCounterResponseDTO getJobRequirmentCounter() {
		JobRequirementCounterResponseDTO jobRequirementCounter = new JobRequirementCounterResponseDTO();
		jobRequirementCounter.setRequirementDepartment(REQUIRMENT_DEPARTMENT);
		jobRequirementCounter.setRequirementDesignation(REQUIRMENT_JOB_DESIGNATON);
		jobRequirementCounter.setRequirmentName(REQUIRMENT_SKILL);
		jobRequirementCounter.setRequirementGroup(REQUIRMENT_GROUP);

		return jobRequirementCounter;
	}
}
