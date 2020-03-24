package com.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.entity.JobRequirementCounter;

/**
 * The Interface JobRequirementCounterRepository.
 */
@Repository
public interface JobRequirementCounterRepository extends JpaRepository<JobRequirementCounter, Long> {

	/**
	 * Find by is requirement available.
	 *
	 * @param requirementDepartment  the requirement department
	 * @param requirementDesignation the requirement designation
	 * @param requirementGroup       the requirement group
	 * @param requirementName        the requirement name
	 * @param exprianceRange         the experience range
	 * @return the job requirement counter
	 */
	@Query("from JobRequirementCounter jobrequirementcounter where jobrequirementcounter.requirementDepartment=:requirementDepartment and "
			+ "jobrequirementcounter.requirementDesignation=:requirementDesignation and "
			+ "jobrequirementcounter.requirementGroup=:requirementGroup and "
			+ "jobrequirementcounter.requirmentName=:requirmentName and "
			+ "jobrequirementcounter.exprianceRange=:exprianceRange and "
			+ "jobrequirementcounter.activeRequirement!=0 ")
	JobRequirementCounter findByIsRequirementAvalible(@Param("requirementDepartment") String requirementDepartment,
			@Param("requirementDesignation") String requirementDesignation,
			@Param("requirementGroup") String requirementGroup, @Param("requirmentName") String requirementName,
			@Param("exprianceRange") String exprianceRange);

	/**
	 * Check job requirment.
	 *
	 * @param candidateDepartment the candidate department
	 * @param candidateDesignation the candidate designation
	 * @param candidateGroup the candidate group
	 * @param candidateSkill the candidate skill
	 * @return the job requirement counter
	 */
	@Query("from JobRequirementCounter jobrequirementcounter where jobrequirementcounter.requirementDepartment=:candidateDepartment and"
			+ " jobrequirementcounter.requirementDesignation=:candidateDesignation and "
			+ "jobrequirementcounter.requirementGroup=:candidateGroup and"
			+ " jobrequirementcounter.requirmentName=:candidateSkill")

	JobRequirementCounter checkJobRequirment(@Param("candidateDepartment") String candidateDepartment,
			@Param("candidateDesignation") String candidateDesignation, @Param("candidateGroup") String candidateGroup,
			@Param("candidateSkill") String candidateSkill);
	
	

	
	/**
	 * Find all requirement.
	 *
	 * @return the list
	 */
	@Query("from JobRequirementCounter jobrequirementcounter where jobrequirementcounter.activeRequirement!=0")
		List<JobRequirementCounter> findAllRequiremnet();
	
	
	/**
	 * Find specified requirements.
	 *
	 * @param requirementGroup the requirement group
	 * @param requirementDepartment the requirement department
	 * @return the list
	 */
	@Query("from JobRequirementCounter jobrequirementcounter where jobrequirementcounter.requirementGroup=:requirementGroup and "
			+ "jobrequirementcounter.requirementDepartment=:requirementDepartment " )
		List<JobRequirementCounter> findSpecifiedRequirements(@Param("requirementGroup")String requirementGroup, @Param("requirementDepartment") String requirementDepartment);


	@Query("from JobRequirementCounter jobrequirementcounter where jobrequirementcounter.jobRequirementCounterId=:jobRequirementCounterId")
	JobRequirementCounter findSpecifiedRequirementswithId(@Param("jobRequirementCounterId")long jobRequirementCounterId);


}
