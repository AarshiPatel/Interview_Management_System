package com.ims.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ims.properties.ConstantProperties;
import com.ims.service.impl.AdminServiceImpl;

/**
 * The Class PasswordUtil.
 */
@Service
public class PasswordUtil {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	/** The secretkeyfactory. */
	private SecretKeyFactory secretKeyFactory;

	/** The cipher. */
	private Cipher cipher;

	/** The array bytes. */
	byte[] arrayBytes;

	/** The secretkey. */
	private SecretKey secretkey;

	/**
	 * Instantiates a new password util.
	 *
	 * @throws Exception the exception
	 */
	public PasswordUtil() throws Exception {

		String myEncryptionKey = "ThisIsSpartaThisIsSparta";
		String myEncryptionScheme = ConstantProperties.DESEDE_ENCRYPTION_SCHEME;
		arrayBytes = myEncryptionKey.getBytes(ConstantProperties.UNICODE_FORMAT);
		secretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
		cipher = Cipher.getInstance(myEncryptionScheme);
		secretkey = secretKeyFactory.generateSecret(new DESedeKeySpec(arrayBytes));

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
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);
			byte[] plainText = unencryptedString.getBytes(ConstantProperties.UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {
			log.info("Error", e);

		}
		return encryptedString;
	}

}
