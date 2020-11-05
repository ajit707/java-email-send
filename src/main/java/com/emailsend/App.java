package com.emailsend;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String to = "kajit0579@gmail.com";
		String from = "ajitk433Gmail.com";
		String subject = "Testing Purpose with attachment";
		String message = "Hi, I am tesing mail code using java";

		// sendEmailWithoutAttachment(to, from, subject, message);

		sendEmailWithAttachment(to, from, subject, message);
	}

	private static void sendEmailWithoutAttachment(String to, String from, String subject, String message) {

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system property
		Properties properties = System.getProperties();

		System.out.println(properties);

		// setting importantat information to properties object
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1 create session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("kajit0579@gmail.com", "gmail@1234");
			}
		});

		session.setDebug(true);

		// step 2 compose the message [text, multimedia]
		MimeMessage m = new MimeMessage(session);

		try {

			m.setFrom(from);

			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			m.setSubject(subject);
			m.setText(message);

			// step 3 send message using Transport class
			Transport.send(m);

			System.out.println("mail send successfully !!!");

		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	private static void sendEmailWithAttachment(String to, String from, String subject, String message) {

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system property
		Properties properties = System.getProperties();

		System.out.println(properties);

		// setting importantat information to properties object
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1 create session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("kajit0579@gmail.com", "gmail@1234");
			}
		});

		session.setDebug(true);

		// step 2 compose the message [text, multimedia]
		MimeMessage m = new MimeMessage(session);

		try {

			m.setFrom(from);

			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			m.setSubject(subject);

			// attachment
			MimeMultipart mimeMultipart = new MimeMultipart();

			MimeBodyPart textMime = new MimeBodyPart();
			MimeBodyPart fileMime = new MimeBodyPart();

			String path = "C:\\Users\\Ajit Kumar\\Downloads\\photo.jpg";

			try {

				textMime.setText(message);

				File file = new File(path);
				fileMime.attachFile(file);

				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);

			} catch (Exception e) {

				e.printStackTrace();
			}

			m.setContent(mimeMultipart);

			// step 3 send message using Transport class
			Transport.send(m);

			System.out.println("mail send successfully !!!");

		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}
}
