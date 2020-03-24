package com.ims.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.AdminRepository;
import com.ims.dao.HumanResourseRepository;
import com.ims.dao.InterviewerRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.UserTypeDTO;
import com.ims.entity.Admin;
import com.ims.entity.HumanResource;
import com.ims.entity.Interviewer;
import com.ims.entity.User;
import com.ims.properties.ConstantProperties;
import com.ims.service.AdminServiceInterface;
import com.ims.util.NameUtil;
import com.ims.util.PasswordUtil;

/**
 * The Class AdminServiceImpl.
 */
@Service
public class AdminServiceImpl implements AdminServiceInterface {

	/** The Constant logger. */
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The interviewer repository. */
	@Autowired
	private InterviewerRepository interviewerRepository;

	/** The human resource repository. */
	@Autowired
	private HumanResourseRepository humanResourceRepository;

	/** The admin repository. */
	@Autowired
	private AdminRepository adminRepository;

	/** The password util. */
	@Autowired
	private PasswordUtil passwordUtil;

	/** The name util. */
	@Autowired
	private NameUtil nameUtil;

	/**
	 * Method for add user into database.
	 *
	 * @param addNewUser the add new user
	 * @return true, if successful. false , if username already exists in database or
	 *         username is not validated in isValid() method or userType is invalid
	 *         .
	 */
	@Override
	public boolean addUser(UserTypeDTO addNewUser) {

		User existingUsername = userRepository.findByUsername(addNewUser.getUsername());
		User user = new User();

		boolean validUsername = nameUtil.isValidName(addNewUser.getUsername());

		if (Objects.isNull(existingUsername)) {

			if (validUsername) {

				String encrypted = passwordUtil.encryptPassword(addNewUser.getPassword());
				user.setPassword(encrypted);
				user.setUser_type(addNewUser.getUserType());
				user.setUsername(addNewUser.getUsername());
				user.setName(addNewUser.getName());

				if (addNewUser.getUserType().equalsIgnoreCase(ConstantProperties.USER_INTERVIEWER)) {

					userRepository.save(user);

					Interviewer interviewer = new Interviewer();
					interviewer.setInterviewerDepartment(addNewUser.getDepartment());
					interviewer.setInterviewerDesignation(addNewUser.getDesignation());
					interviewer.setInterviewerDob(addNewUser.getDob());
					interviewer.setInterviewerExperience(addNewUser.getExperience());
					interviewer.setInterviewerMobilenumber(addNewUser.getMobilenumber());
					interviewer.setInterviewerName(addNewUser.getName());
					interviewer.setFlag(1);
					interviewer.setUser(user);
					interviewerRepository.save(interviewer);

					log.info("Added Interviewer : {}", addNewUser.getName());

					return true;
				} else if (addNewUser.getUserType().equalsIgnoreCase(ConstantProperties.USER_HUMANRESOURCE)) {
					userRepository.save(user);

					HumanResource humanResource = new HumanResource();
					humanResource.setHrDepartment(addNewUser.getDepartment());
					humanResource.setHrDesignation(addNewUser.getDesignation());
					humanResource.setHrDob(addNewUser.getDob());
					humanResource.setHrExperience(addNewUser.getExperience());
					humanResource.setHrMobilenumber(addNewUser.getMobilenumber());
					humanResource.setHrName(addNewUser.getName());
					humanResource.setUser(user);
					humanResourceRepository.save(humanResource);
					log.info("Added Humanresource : {}", addNewUser.getName());

					return true;
				} else if (addNewUser.getUserType().equalsIgnoreCase(ConstantProperties.USER_ADMIN)) {
					userRepository.save(user);

					Admin admin = new Admin();
					admin.setAdminDepartment(addNewUser.getDepartment());
					admin.setAdminDesignation(addNewUser.getDesignation());
					admin.setAdminDob(addNewUser.getDob());
					admin.setAdminExperience(addNewUser.getExperience());
					admin.setAdminMobilenumber(addNewUser.getMobilenumber());
					admin.setAdminName(addNewUser.getName());
					admin.setUser(user);
					adminRepository.save(admin);
					log.info("Added Admin : {}", addNewUser.getName());

					return true;
				}

				log.info("Usertype is invalid : {}", addNewUser.getUserType());

				return false;

			}

			log.info("Username is invalid : {}", addNewUser.getUsername());

			return false;

		}

		log.info("Username already exists in database : {}", addNewUser.getUsername());

		return false;

	}


	/**
	 * Method for update user into database.
	 *
	 * @param userId     the user id
	 * @param updateUser the update user
	 * @return true, if successful. 
	 * @return false , if user is not found in database or
	 *         userType is invalid .
	 */
	@Override
	public boolean updateUser(long userId, UserTypeDTO updateUser) {
		User user = userRepository.findByUserId(userId);
		if (!Objects.isNull(user)) {

			if (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_INTERVIEWER)) {

				Interviewer interviewer = interviewerRepository.findByUser(user);

				interviewer.setInterviewerDepartment(updateUser.getDepartment());
				interviewer.setInterviewerDesignation(updateUser.getDesignation());
				interviewer.setInterviewerDob(updateUser.getDob());
				interviewer.setInterviewerExperience(updateUser.getExperience());
				interviewer.setInterviewerMobilenumber(updateUser.getMobilenumber());
				interviewer.setInterviewerName(updateUser.getName());
				interviewer.setAvailableDate(updateUser.getAvailableDate());
				interviewer.setAvailableTimeFrom(updateUser.getAvailableTimeFrom());
				interviewer.setAvailableTimeTo(updateUser.getAvailableTimeTo());

				interviewerRepository.save(interviewer);

				return true;

			} else if (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_HUMANRESOURCE)) {

				HumanResource humanResource = humanResourceRepository.findByUser(user);

				humanResource.setHrDepartment(updateUser.getDepartment());
				humanResource.setHrDesignation(updateUser.getDesignation());
				humanResource.setHrDob(updateUser.getDob());
				humanResource.setHrExperience(updateUser.getExperience());
				humanResource.setHrMobilenumber(updateUser.getMobilenumber());
				humanResource.setHrName(updateUser.getName());
				humanResource.setUser(user);

				humanResourceRepository.save(humanResource);

				return true;
			} else if (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_ADMIN)) {

				Admin admin = adminRepository.findByUser(user);

				admin.setAdminDepartment(updateUser.getDepartment());
				admin.setAdminDesignation(updateUser.getDesignation());
				admin.setAdminDob(updateUser.getDob());
				admin.setAdminExperience(updateUser.getExperience());
				admin.setAdminMobilenumber(updateUser.getMobilenumber());
				admin.setAdminName(updateUser.getName());
				admin.setUser(user);

				adminRepository.save(admin);

				return true;
			}

			return false;

		}

		return false;

	}

	/**
	 * Method for delete user from database.
	 *
	 * @param userId the user id
	 * @return true, if successful 
	 * @return false , if user is not found in database .
	 */
	@Override
	public boolean deleteUser(long userId) {

		User user = userRepository.findByUserId(userId);
		if (!Objects.isNull(user)) {
				return true;
				
			} else if (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_HUMANRESOURCE)) {
				humanResourceRepository.findByUser(user);
				return true;

			} else {
				return false;
			}
	}

	/**
	 * Method for retreiving Admin from userId.
	 *
	 * @param userId the user id
	 * @return instance of Admin , if successful 
	 * @return null , if user is not found from
	 *         database or userType of user is not Admin.
	 */
	@Override
	public Admin getAdminFromUserId(long userId) {

		User user = userRepository.findByUserId(userId);

		if (!Objects.isNull(user) && (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_ADMIN))) {

			return adminRepository.findByUser(user);

		}
		return null;
	}
	
	/**
	 * Method for retreiving HumanResource from userId.
	 *
	 * @param userId the user id
	 * @return instance of HumanResource , if successful 
	 * @return null , if user is not found
	 *         from database or userType of user is not HumanResource.
	 */
	@Override
	public HumanResource getHumanResourceFromUserId(long userId) {
		User user = userRepository.findByUserId(userId);

		if (!Objects.isNull(user) && (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_HUMANRESOURCE))) {

			return humanResourceRepository.findByUser(user);

		}
		return null;

	}


	/**
	 * Method for retreiving Interviewer from userId.
	 *
	 * @param userId the user id
	 * @return instance of Interviewer , if successful
	 * @return null , if user is not found
	 *         from database or userType of user is not Interviewer.
	 */
	@Override
	public Interviewer getInterviewerFromUserId(long userId) {
		User user = userRepository.findByUserId(userId);

		if (!Objects.isNull(user) && (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_INTERVIEWER))) {

			return interviewerRepository.findByUser(user);

		}
		return null;
	}

	
	/**
	 * Method for update profile for Admin.
	 *
	 * @param userId  
	 * @param userDTO the user DTO
	 * @return instance of Admin , if successful
	 * @return null , if user is not found from
	 *         database or userType of user is not Admin.
	 */
	@Override
	public Admin updateAdminProfile(long userId, UserTypeDTO userDTO) {

		User user = userRepository.findByUserId(userId);

		if (!Objects.isNull(user) && (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_ADMIN))) {

			Admin admin = adminRepository.findByUser(user);
			admin.setAdminDepartment(userDTO.getDepartment());
			admin.setAdminDesignation(userDTO.getDesignation());
			admin.setAdminDob(userDTO.getDob());
			admin.setAdminExperience(userDTO.getExperience());
			admin.setAdminMobilenumber(userDTO.getMobilenumber());
			admin.setAdminName(userDTO.getName());
			admin.setUser(user);
			adminRepository.save(admin);
			return admin;
		}
		return null;
	}

	/**
	 * Method for retrieving data of Admin table from database.
	 *
	 * @return all data of Admin table.
	 */
	@Override
	public List<Admin> viewAllAdmin() {

		return adminRepository.findAll();

	}

	/**
	 * Method for retrieving data of HumanResource table from database.
	 *
	 * @return all data of HumanResource table.
	 */
	@Override
	public List<HumanResource> viewAllHumanResource() {

		return humanResourceRepository.findAll();

	}

	/**
	 * Method for retrieving data of Interviewer table from database.
	 *
	 * @return all data of Interviewer table.
	 */
	@Override
	public List<Interviewer> viewAllInterviewer() {

		return interviewerRepository.findAll();

	}

//	@Override
//	public List<UserResponseDTO> viewAllUser() {
//		
//		return UserMapper.mapToUserResponseDTO(userRepository.findAll());
//	}


}
