package org.source.comunicapa.test.mail;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import model.Comune;
import model.Impostazioni;
import model.Email;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Folder;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import javax.mail.Multipart;
import javax.mail.Part;

import com.sun.mail.smtp.SMTPMessage;
import com.sun.mail.smtp.SMTPSSLTransport;

import net.suberic.crypto.*;

public class MailSender {

	public void sendMail_SMTP(Email e) throws Exception {
		String destinatario, oggetto,  body,  destinatariCC = null,  destinatariBCC = null;
		destinatario = e.getAddressTo();
		oggetto = e.getSubject();
		body = e.getBody();
		
		try{
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.host", Impostazioni.smtp_server);
	        props.put("mail.smtp.socketFactory.port", Impostazioni.portaSend);
	        props.put("mail.smtp.auth", "false"); 
	        props.put("mail.smtp.connectiontimeout", Impostazioni.timeout); 
	        props.put("mail.smtp.timeout", Impostazioni.timeout); 
	        Session session = Session.getDefaultInstance(props, null);
	        session.setDebug(true);
	        
	        Message message = new MimeMessage(session);
			Address from = new InternetAddress(Impostazioni.indirizzo_mittente, Impostazioni.nome);
			message.setFrom(from);
			message.setSubject(oggetto);
			message.setSentDate(new java.util.Date());
			message.setContent(body, Impostazioni.contentType);
			
			Address to = new InternetAddress(destinatario);
			message.setRecipient(Message.RecipientType.TO, to);
			
			if(destinatariCC != null){
				Address toCC[] = InternetAddress.parse(destinatariCC);
				message.setRecipients(Message.RecipientType.CC, toCC);
			}
			
			if(destinatariBCC != null){
				Address toBCC[] = InternetAddress.parse(destinatariBCC);
				message.setRecipients(Message.RecipientType.BCC, toBCC);
			}
	        
	        Transport transport = session.getTransport("smtp");        
	        transport.connect(Impostazioni.smtp_server, Impostazioni.username, Impostazioni.password);
			message.saveChanges();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception exception) {
			throw exception;
		}
	}
	
	public void sendMail_SMTPS(Email e) throws Exception {
		String destinatario, oggetto,  body,  destinatariCC = null,  destinatariBCC = null;
		destinatario = e.getAddressTo();
		oggetto = e.getSubject();
		body = e.getBody();
		
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtp.host", Impostazioni.smtp_server);
			props.put("mail.smtp.socketFactory.port", Impostazioni.portaSend);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.connectiontimeout", Impostazioni.timeout);
			props.put("mail.smtp.timeout", Impostazioni.timeout);
	
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);

			Message message = new MimeMessage(session);
			Address from = new InternetAddress(Impostazioni.indirizzo_mittente, Impostazioni.nome);
			message.setFrom(from);
			message.setSubject(oggetto);
			message.setSentDate(new java.util.Date());
			message.setContent(body, Impostazioni.contentType);
			
			Address to = new InternetAddress(destinatario);
			message.setRecipient(Message.RecipientType.TO, to);
			
			if(destinatariCC != null){
				Address toCC[] = InternetAddress.parse(destinatariCC);
				message.setRecipients(Message.RecipientType.CC, toCC);
			}
			
			if(destinatariBCC != null){
				Address toBCC[] = InternetAddress.parse(destinatariBCC);
				message.setRecipients(Message.RecipientType.BCC, toBCC);
			}
		
			SMTPSSLTransport transport = (SMTPSSLTransport) session.getTransport("smtps");
			transport.connect(Impostazioni.smtp_server, Impostazioni.username, Impostazioni.password);
			message.saveChanges();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception exception) {
			throw exception;
		}
	}
	
	public void sendMail_SMIME(Email e) throws Exception{		
		String destinatario, oggetto,  body,  destinatariCC = null,  destinatariBCC = null;
		destinatario = e.getAddressTo();
		oggetto = e.getSubject();
		body = e.getBody();
		
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtp.host", Impostazioni.smtp_server);
			props.put("mail.smtp.socketFactory.port", Impostazioni.portaSend);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.connectiontimeout", Impostazioni.timeout);
			props.put("mail.smtp.timeout", Impostazioni.timeout);			
			
			Session session = Session.getDefaultInstance(props, null);

			session.setDebug(true);
			SMTPMessage message = new SMTPMessage(session);
			Address from = new InternetAddress(Impostazioni.indirizzo_mittente, Impostazioni.nome);
			message.setFrom(from);
			message.setSubject(oggetto);
			message.setAllow8bitMIME(true);
			message.setNotifyOptions(SMTPMessage.NOTIFY_FAILURE);
			message.setSentDate(new java.util.Date());
			message.setContent(body, Impostazioni.contentType);
			message.saveChanges();
			
			Address to = new InternetAddress(destinatario);
			message.setRecipient(Message.RecipientType.TO, to);
			
			if(destinatariCC != null){
				Address toCC[] = InternetAddress.parse(destinatariCC);
				message.setRecipients(Message.RecipientType.CC, toCC);
			}
			
			if(destinatariBCC != null){
				Address toBCC[] = InternetAddress.parse(destinatariBCC);
				message.setRecipients(Message.RecipientType.BCC, toBCC);
			}

			SMTPSSLTransport transport = (SMTPSSLTransport) session.getTransport("smtps");
			transport.connect(Impostazioni.smtp_server, Impostazioni.username, Impostazioni.password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		     
		} catch (Exception exception) {
			throw exception;
		}
	}
}
