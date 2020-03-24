package com.ims.service.impl;

import java.security.spec.KeySpec;
import java.util.Objects;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.UserRepository;
import com.ims.dto.UserDTO;
import com.ims.dto.response.UserResponseDTO;
import com.ims.entity.User;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.exception.UnprocessableEntity;
import com.ims.mail.Mail;
import com.ims.mapper.UserMapper;
import com.ims.properties.ConstantProperties;
import com.ims.service.LoginServiceInterface;
import com.ims.util.NameUtil;

/**
 * The Class LoginServiceImpl.
 */
@Service
public class LoginServiceImpl implements LoginServiceInterface {
//	/** The Constant logger. */
//	private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NameUtil checkEmailId;
	@Autowired
	private Mail sendMail;
	/** The Constant UNICODE_FORMAT. */
	private static final String UNICODE_FORMAT = "UTF8";

	/** The Constant DESEDE_ENCRYPTION_SCHEME. */
	private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

	/** The ks. */
	private KeySpec ks;

	/** The skf. */
	private SecretKeyFactory skf;

	/** The cipher. */
	private Cipher cipher;

	/** The array bytes. */
	byte[] arrayBytes;

	/** The my encryption key. */
	private String myEncryptionKey;

	/** The my encryption scheme. */
	private String myEncryptionScheme;

	private SecretKey key;

	/**
	 * Instantiates a new login service impl.
	 *
	 * @throws Exception the exception
	 */
	public LoginServiceImpl() throws Exception {

		myEncryptionKey = "ThisIsSpartaThisIsSparta";
		myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		ks = new DESedeKeySpec(arrayBytes);
		skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		cipher = Cipher.getInstance(myEncryptionScheme);
		key = skf.generateSecret(ks);

	}

	/**
	 * Encrypt password.
	 *
	 * @param unencryptedString the unencrypted string
	 * @return the string
	 */
	public String encryptPassword(String unencryptedString) {
		String encryptedString = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {

		}
		return encryptedString;
	}

	/**
	 * Login.
	 *
	 * @param user the user
	 * @return the user
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 * @throws BadRequestException
	 */
	@Override
	public UserResponseDTO login(UserDTO user)
			throws UnprocessableEntity, ResourceNotFoundException, BadRequestException {

		if (Objects.isNull(user.getPassword()) || Objects.isNull(user.getUsername()) || user.getPassword().isEmpty()
				|| user.getUsername().isEmpty()) {
			throw new UnprocessableEntity(ConstantProperties.FEILDS_MANDATORY_ERROR);
		}
		if (!checkEmailId.isValidName(user.getUsername())) {
			throw new BadRequestException(ConstantProperties.ERROR_MESSAGE_INVALID_EMAILID);
		}
		String password = encryptPassword(user.getPassword());
		User isValidUser = userRepository.findByUsernameAndPassword(user.getUsername(), password);
		if (Objects.isNull(isValidUser)) {
			throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);

		}
		return UserMapper.mapToUserResponseDTO(isValidUser);

	}

	/**
	 * Forgot password mail.
	 *
	 * @param userName the user name
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public UserResponseDTO forgotPasswordMail(String userName) throws Exception {

		if (userName.isEmpty()) {
			throw new UnprocessableEntity(ConstantProperties.FEILDS_MANDATORY_ERROR);
		}
		if (!checkEmailId.isValidName(userName)) {
			throw new BadRequestException(ConstantProperties.ERROR_MESSAGE_INVALID_EMAILID);
		}

		User user = userRepository.findByUsername(userName);
		if (Objects.nonNull(user)) {
			UUID randomNumber = UUID.randomUUID();

			String authCode = randomNumber.toString().toUpperCase();
			user.setAuth_code(authCode);
			sendMail.forgotPasswordMail(userName, authCode);
			userRepository.save(user);
			return UserMapper.mapToUserResponseDTO(user);

		}

		throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);

	}

	/**
	 * Auth code verification.
	 *
	 * @param authCode the auth code
	 * @param emailId  the email id
	 * @return the long
	 */
	@Override
	public long authCodeVerification(String authCode, String emailId) {

		final long userNotFound = 0;
		User userResponse = userRepository.findByUsername(emailId);
		if (!Objects.isNull(userResponse) && authCode.equals(userResponse.getAuth_code())) {
			userResponse.setAuth_code(null);
			userRepository.save(userResponse);
			return userResponse.getUserId();

		}
		return userNotFound;

	}

	/**
	 * Rest password.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws UnprocessableEntity
	 * @throws ResourceNotFoundException
	 */
	@Override
	public UserResponseDTO restPassword(UserDTO user) throws UnprocessableEntity, ResourceNotFoundException {
		if (user.getPassword().isEmpty()) {
			throw new UnprocessableEntity(ConstantProperties.FEILDS_MANDATORY_ERROR);
		}

		User isUserFind = userRepository.findByUserId(user.getUserId());
		if (Objects.isNull(isUserFind)) {
			throw new ResourceNotFoundException(ConstantProperties.NOT_FOUND);

		}
		isUserFind.setPassword(encryptPassword(user.getPassword()));
		userRepository.save(isUserFind);
		return UserMapper.mapToUserResponseDTO(isUserFind);

	}

}
