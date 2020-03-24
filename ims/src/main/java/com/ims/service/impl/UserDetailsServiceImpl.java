package com.ims.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.CandidateRepository;
import com.ims.dao.InterviewRepository;
import com.ims.dao.UserDetailsRepository;
import com.ims.dao.UserRepository;
import com.ims.dto.UserDetailsDTO;
import com.ims.dto.response.UserDetailsResponseDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.entity.Candidate;
import com.ims.entity.Interview;
import com.ims.entity.User;
import com.ims.entity.UserDetails;
import com.ims.exception.ConflictException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.mapper.UserDetailsMapper;
import com.ims.mapper.UserMapper;
import com.ims.properties.ConstantProperties;
import com.ims.service.UserDetailsServiceInterface;
import com.ims.util.NameUtil;
import com.ims.util.PasswordUtil;


/**
 * The Class UserDetailsServiceImpl.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsServiceInterface {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/** The user details repository. */
	@Autowired
	UserDetailsRepository userDetailsRepository;

	/** The password util. */
	@Autowired
	private PasswordUtil passwordUtil;

	/** The name util. */
	@Autowired
	private NameUtil nameUtil;

	/** The candidate repository. */
	@Autowired
	private CandidateRepository candidateRepository;

	/** The interview repository. */
	@Autowired
	private InterviewRepository interviewRepository;

	/**
	 * Adds the user.
	 *
	 * @param userDetailsDTO the user details DTO
	 * @return true, if successful
	 * @throws ConflictException   the conflict exception
	 * @throws UnprocessableEntity the unprocessable entity
	 */
	/**
	 * Adds the user.
	 *
	 * @param userDetailsDTO the user details DTO
	 * @return true, if successful
	 */
	@Override
	public UserDetails addUser(UserDetailsDTO userDetailsDTO) throws ConflictException, UnprocessableEntity {

		User existingUsername = userRepository.findByUsername(userDetailsDTO.getUsername());
		if (Objects.isNull(existingUsername)) {

			boolean validUsername = nameUtil.isValidName(userDetailsDTO.getUsername());
			if (validUsername) {

				User user = new User();
				BeanUtils.copyProperties(userDetailsDTO, user);
				String encrypted = passwordUtil.encryptPassword(userDetailsDTO.getPassword());
				user.setPassword(encrypted);
				userRepository.save(user);

				UserDetails userDetails = new UserDetails();
				BeanUtils.copyProperties(userDetailsDTO, userDetails);
				userDetails.setActiveFlag(ConstantProperties.FLAG_ONE);

				userDetails.setUser(user);
				userDetailsRepository.save(userDetails);

				log.info("Added User : {}", userDetailsDTO.getName());
				return userDetails;
			}
			log.info("Invalid Username : {}", userDetailsDTO.getName());
			throw new UnprocessableEntity();
		}
		log.info("Already Existing User : {}", userDetailsDTO.getName());
		throw new ConflictException(ConstantProperties.CONFLICT_USERDETAIL);

	}

	/**
	 * Update user.
	 *
	 * @param userId         the user id
	 * @param userDetailsDTO the user details DTO
	 * @return true, if successful
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public UserDetails updateUser(long userId, UserDetailsDTO userDetailsDTO) throws ResourceNotFoundException {

		User user = userRepository.findByUserId(userId);
		if (Objects.nonNull(user)) {

			UserDetails userDetails = userDetailsRepository.findByUser(user);
			if (Objects.nonNull(userDetails)) {

				BeanUtils.copyProperties(userDetailsDTO, userDetails);
				userDetails.setActiveFlag(ConstantProperties.FLAG_ONE);
				userDetailsRepository.save(userDetails);

				log.info("Updated User : {}", userDetailsDTO.getName());
				return userDetails;
			}
			log.info("User not found in UserDetails : {}", userDetailsDTO.getName());
			throw new ResourceNotFoundException(ConstantProperties.RESOURCE_USERNOTFOUND);
		}
		log.info("User not Found : {}", userDetailsDTO.getName());
		throw new ResourceNotFoundException(ConstantProperties.RESOURCE_USERNOTFOUND);
	}

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public UserDetails deleteUser(long userId) throws ResourceNotFoundException {

		User user = userRepository.findByUserId(userId);

		if (Objects.nonNull(user)) {

			if (user.getUser_type().equalsIgnoreCase(ConstantProperties.USER_INTERVIEWER)) {

				UserDetails userDetails = userDetailsRepository.findByUser(user);

				List<Interview> listOfInterview = interviewRepository.getInterviewByUserId(userDetails);

				List<Candidate> listOfCandidate = candidateRepository.getCandidateScheduleInterview();

				for (Interview interview : listOfInterview) {

					for (Candidate candidate : listOfCandidate) {
						if (candidate.getInterview() != null) {

							if (candidate.getInterview().getInterviewId() == interview.getInterviewId()) {
								if (candidate.getInterviewResult().equals(ConstantProperties.REJECT_CANDIDATE)) {
									candidate.setInterview(interview);
								} else {
									candidate.setInterview(null);

								}

								candidateRepository.save(candidate);
							}
						}
					}
				}
				userDetails.setActiveFlag(ConstantProperties.FLAG_ZERO);
				userDetails.setUser(null);
				userDetailsRepository.save(userDetails);

				userRepository.deleteById(userId);

				return userDetails;
			} else {
				UserDetails userDetails = userDetailsRepository.findByUser(user);
				userDetails.setActiveFlag(ConstantProperties.FLAG_ZERO);
				userDetails.setUser(null);
				userDetailsRepository.save(userDetails);

				userRepository.deleteById(userId);

				return userDetails;
			}

		}
		log.info("User not Found From Id: {}", userId);
		throw new ResourceNotFoundException(ConstantProperties.RESOURCE_USERNOTFOUND);
	}

	/**
	 * View all user.
	 *
	 * @return the list
	 */
	@Override
	public List<UserResponseDTO> viewAllUser() {

		return UserMapper.mapToUserResponseDTO(userRepository.findAll());
	}

	/**
	 * View all admin.
	 *
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public List<UserDetailsResponseDTO> viewAllAdmin() throws ResourceNotFoundException {

		return UserDetailsMapper.mapToUserDetailsResponseDTO(userDetailsRepository.findAllAdmin());
	}

	/**
	 * View all interviewer.
	 *
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public List<UserDetailsResponseDTO> viewAllInterviewer() throws ResourceNotFoundException {

		return UserDetailsMapper.mapToUserDetailsResponseDTO(userDetailsRepository.findAllInterviewer());
	}

	/**
	 * View all human resource.
	 *
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public List<UserDetailsResponseDTO> viewAllHumanResource() throws ResourceNotFoundException {

		return UserDetailsMapper.mapToUserDetailsResponseDTO(userDetailsRepository.findAllHumanResource());
	}

	/**
	 * View all manager.
	 *
	 * @return the list
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public List<UserDetailsResponseDTO> viewAllManager() throws ResourceNotFoundException {

		return UserDetailsMapper.mapToUserDetailsResponseDTO(userDetailsRepository.findAllManager());
	}

	/**
	 * Gets the user details from user id.
	 *
	 * @param userId the user id
	 * @return the user details from user id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public UserDetailsResponseDTO getUserDetailsFromUserId(long userId) throws ResourceNotFoundException {
		User user = userRepository.findByUserId(userId);

		if (!Objects.isNull(user)) {
			UserDetails userDetails = userDetailsRepository.findByUser(user);

			return UserDetailsMapper.mapToUserResponseDTO(userDetails);

		}
		throw new ResourceNotFoundException();
	}

	/**
	 * Update admin profile.
	 *
	 * @param userId             the user id
	 * @param updateAdminProfile the update admin profile
	 * @return the user details response DTO
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@Override
	public UserDetailsResponseDTO updateAdminProfile(long userId, UserDetailsDTO updateAdminProfile)
			throws ResourceNotFoundException {
		User user = userRepository.findByUserId(userId);

		if (!Objects.isNull(user)) {
			UserDetails userDetails = userDetailsRepository.findByUser(user);
			BeanUtils.copyProperties(updateAdminProfile, userDetails);
			userDetails.setActiveFlag(ConstantProperties.FLAG_ONE);

			userDetailsRepository.save(userDetails);

			return UserDetailsMapper.mapToUserResponseDTO(userDetails);
		}
		throw new ResourceNotFoundException();
	}

}
