package com.ims.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ims.entity.HumanResource;
import com.ims.entity.User;

/**
 * The Interface HumanResourseRepository.
 */
@Repository
public interface HumanResourseRepository extends JpaRepository<HumanResource, Long> {

	
	/**
	 * Find by hr id.
	 *
	 * @param hrId the hr id
	 * @return the human resource
	 */
	HumanResource findByHrId(long hrId);
	
	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the human resource
	 */
	HumanResource findByUser(User user);
	/**
	 * Find all HumanResource.
	 * This method is used to get HumanResource list where user is not null.
	 * @return the list
	 */
	@Query("from HumanResource humanResource where humanResource.user!=null")
	List<HumanResource> findAll();
	
	

}