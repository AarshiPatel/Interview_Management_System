package com.ims.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ims.properties.MailProperties;

@Service
public class Mail {
	private static final Logger log = LoggerFactory.getLogger(Mail.class);

	public void forgotPasswordMail(String recepient, String authCode) throws MessagingException {

		Properties properties = new Properties();
		properties.put(MailProperties.SMTP_AUTH, MailProperties.TRUE_VALUE);
		properties.put(MailProperties.SMTP_STARTTLS, MailProperties.TRUE_VALUE);
		properties.put(MailProperties.SMTP_HOST, MailProperties.SMTP_HOST_NAME);
		properties.put(MailProperties.SMTP_HOST_NAME, MailProperties.PORT);

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(MailProperties.EMAILID, MailProperties.PASSWORD);
			}
		});

		Message message = prepareMessage(session, MailProperties.EMAILID, recepient, authCode);
		Transport.send(message);

	}

	private Message prepareMessage(Session session, String myAccountEmail, String recepient, String authCode) {

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			String htmlCode = "<font size='3' color='black'>Hi</font><br><br>"
					+ "<font size='3' color='black'>We received a request to reset your password for your VAHAK account:"
					+ recepient + ". We are here to help!</font><br><br>"
					+ "<font size='3' color='black'>Use the link below to set up a new password for your account.</font><br><br>"
					+ "<a id='userAuth' href='http://localhost:8080/user/authcode?authCode=" + authCode + "&emailId="
					+ recepient + "'>Click Here</a><br><br>"
					+ "<font size='3' color='black'>If you did not request to reset your password, then simply ignore this mail.</font><br><br>"
					+ "<font size='3' color='black'>We love hearing from you.</font><br><br>"
					+ "<font size='3' color='black'>Email us at vahak.ims@gmail.com if you have any other questions!</font><br><br>"
					+ "<font size='3' color='black'>Best,</font><br>" + "<font size='3' color='black'>VAHAK</font><br>";
			message.setSubject("Forgot Password");
			message.setContent(htmlCode, "text/html");
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

			return message;

		} catch (Exception exception) {
			log.info("Errpr", exception);
		}
		return null;

	}

}
