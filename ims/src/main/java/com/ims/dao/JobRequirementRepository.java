package com.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.ims.entity.JobRequirement;

/**
 * The Interface JobRequirementRepository.
 */
@Repository
public interface JobRequirementRepository extends JpaRepository<JobRequirement, Long> {

	@Query("from JobRequirement")
	List<JobRequirement> getRequrirementList();


	@Query("from JobRequirement jobrequirementmt where jobrequirementmt.manager.userDetailsId=:userDetailsId and jobrequirementmt.jobOpeningIsApprove=0")
	List<JobRequirement> getRequrirementPendingApprovalListByManagerId(@Param("userDetailsId")long userDetailsId);


	@Query("from JobRequirement jobrequirementmt where jobrequirementmt.requirementDepartment=:requirementDepartment and jobrequirementmt.requirementDesignation=:requirementDesignation and jobrequirementmt.requirementGroup=:requirementGroup and jobrequirementmt.requirmentName=:requirmentName and jobrequirementmt.exprianceRange=:exprianceRange and jobrequirementmt.manager.userDetailsId=:userDetailsId")

	JobRequirement findJobRequirement(@Param("requirementDepartment")String requirementDepartment, @Param("requirementDesignation")String requirementDesignation,
			@Param("requirementGroup")String requirementGroup, @Param("requirmentName")String requirmentName, @Param("exprianceRange")String exprianceRange, @Param("userDetailsId")long userDetailsId);


	@Query("from JobRequirement jobrequirementmt where jobrequirementmt.requirementId=:requirementId ")
	JobRequirement getRequriement(@Param("requirementId")long requrimentId);


	JobRequirement findByRequirementId(long jobPostId);

	@Query("from JobRequirement jobrequirementmt where jobrequirementmt.manager.userDetailsId=:userDetailsId and jobrequirementmt.jobOpeningIsApprove=1")
	List<JobRequirement> getApprovedJobRequirmentByManagerId(@Param("userDetailsId")long userDetailsId);


	@Query("from JobRequirement jobrequirementmt where  jobrequirementmt.jobOpeningIsApprove=0")
	List<JobRequirement> getJobOpeningPendingApprovalList();

	@Query("from JobRequirement jobrequirementmt where  jobrequirementmt.jobOpeningIsApprove=1")
	List<JobRequirement> getApprovedJobOpeningList();

}
