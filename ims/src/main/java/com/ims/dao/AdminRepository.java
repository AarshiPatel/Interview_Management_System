package com.ims.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ims.entity.Admin;
import com.ims.entity.User;

/**
 * The Interface AdminRepository.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	/**
	 * Find by admin id.
	 *
	 * @param adminId the admin id
	 * @return the admin
	 */
	Admin findByAdminId (long adminId);
	
	/**
	 * Find by user.
	 *
	 * @param user the user
	 * @return the admin
	 */
	Admin findByUser(User user);
	/**
	 * Find all Admin.
	 * This method is used to get admin list.
	 * @return the list
	 */
	@Query("from  Admin admin where admin.user!=null")
	List<Admin> findAll();
	

}