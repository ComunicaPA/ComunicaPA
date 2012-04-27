package src.org.source.comunicapa.test.mail;

import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;


import model.Impostazioni;
import model.Email;

public class MailReader {

	public List<Email> readMail_IMAP_SSL1() {
		Store store = null;

		Message[] messages = null;
		

		Properties props = new Properties();

		props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback", "false");
		props.setProperty("mail.imap.socketFactory.port", Impostazioni.imap_port);

	

		try {
			
			Session session = javax.mail.Session.getInstance(props, null);
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", "...@gmail.com", "465");
			
			Folder inbox = store.getFolder("Inbox");
			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			
			messages = inbox.getMessages();


		
			inbox.close(false);
			store.close();
			
		} catch (Exception e) {
			System.out.println("caught exception: " + e);
			e.printStackTrace();
			
		}
		return null;
	}
	
	public List<Email> readMail_IMAP(){return null;}
	
	public List<Email> readInbox_IMAPS() {
		List<Email> emails = new ArrayList<Email>();
		Store store = null;
		Message message = null;
		Message[] messages = null;
		Object messagecontentObject = null;
		Multipart multipart = null;
		Part part = null;
		String contentType = null;
		
		String sender = null;
		String subject = null;
		
		Properties props = new Properties();

		props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback", "false");
		props.setProperty("mail.imap.socketFactory.port", Impostazioni.imap_port);
	 
		try {
			
			Session session = javax.mail.Session.getInstance(props, null);
			store = session.getStore("imap");
			store.connect(Impostazioni.imap_server, Impostazioni.username, Impostazioni.password);
			
		
			Folder folder = store.getFolder("INBOX");
			
			folder.open(Folder.READ_ONLY);
	
			messages = folder.getMessages();
			for (int messageNumber = messages.length -1; messageNumber > messages.length - 10; messageNumber--) {
				Email email = new Email();
				message = messages[messageNumber];
				messagecontentObject = message.getContent();
	           if (messagecontentObject instanceof Multipart) {
	               
	               sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
	 
			       if (sender == null) {
				 	   sender = ((InternetAddress) message.getFrom()[0]).getAddress();
			       }
			       email.setAddressFrom(sender);
			       email.setSubject(message.getSubject());
	 
	               multipart = (Multipart) message.getContent();
	               for (int i = 0; i < multipart.getCount(); i++) {
	                    part = multipart.getBodyPart(i);

	                    email.setContentType(part.getContentType());
	                   
	               }
			   } 
	           else /
	           {
				   sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
		 
				   if (sender == null) {
					   sender = ((InternetAddress) message.getFrom()[0]).getAddress();
				   }
				   email.setAddressFrom(sender);
			       email.setSubject(message.getSubject());
			       email.setContentType(part.getContentType());
			   }
	           emails.add(email);
			}
			folder.close(false);
			store.close();
		}
		catch (Exception e) 
		{
			System.out.println("caught exception: " + e);
			e.printStackTrace();
			
		}
		return emails;
	}
	
	
	public List<Email> readSent_IMAPS(){
		List<Email> emails = new ArrayList<Email>();
		Store store = null;
		Message message = null;
		Message[] messages = null;
		Object messagecontentObject = null;
		Multipart multipart = null;
		Part part = null;
		String contentType = null;
		
		String sender = null;
		String subject = null;
		
		Properties props = new Properties();

		props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback", "false");
		props.setProperty("mail.imap.socketFactory.port", Impostazioni.imap_port);
	 
		try {
			
			Session session = javax.mail.Session.getInstance(props, null);
			store = session.getStore("imap");
			store.connect(Impostazioni.imap_server, Impostazioni.username, Impostazioni.password);
			
			Folder[] f = store.getDefaultFolder().list();
		    for(Folder fd:f)
		        System.out.println(">> "+fd.getName());
		
			Folder folder = store.getFolder("[Gmail]/Sent Mail");
			
			folder.open(Folder.READ_ONLY);
	
			messages = folder.getMessages();
			for (int messageNumber = messages.length -1; messageNumber > messages.length - 10; messageNumber--) {
				Email email = new Email();
				message = messages[messageNumber];
	           if (messagecontentObject instanceof Multipart) {
	               
	               sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
	 
			       if (sender == null) {
				 	   sender = ((InternetAddress) message.getFrom()[0]).getAddress();
			       }
			       email.setAddressFrom(sender);
			       email.setSubject(message.getSubject());
	 
	               multipart = (Multipart) message.getContent();
	               for (int i = 0; i < multipart.getCount(); i++) {
	                    part = multipart.getBodyPart(i);

	                    email.setContentType(part.getContentType());
	                   
	               }
			   } 
	           else 
	           {
				   sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
		 
				   if (sender == null) {
					   sender = ((InternetAddress) message.getFrom()[0]).getAddress();
					   System.out.println("sender in NULL. Printing Address:" + sender);
				   }
				   email.setAddressFrom(sender);
			       email.setSubject(message.getSubject());
			       email.setContentType(part.getContentType());
			   }
	           emails.add(email);
			}
			folder.close(false);
			store.close();
		}
		catch (Exception e) 
		{
			System.out.println("caught exception: " + e);
			e.printStackTrace();
			
		}
		return emails;	
	}
	public List<Email> readMail_POP3_SSL() throws Exception{
        
        Properties props = new Properties();
         
        props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port",  Impostazioni.pop3_port);
        props.setProperty("mail.pop3.socketFactory.port", Impostazioni.pop3_port);
        props.put("mail.pop3.host", Impostazioni.pop3_server);
         
        try {
	        Session session = Session.getInstance(props, null);
	        Store store = session.getStore("pop3"); //pop3s
	        store.connect(Impostazioni.pop3_server, Impostazioni.username, Impostazioni.password);
	        
	        Folder folder = store.getFolder("INBOX");
	        
	         
	        if (folder == null) {
	            throw new Exception("Invalid folder");
	        }
         
            folder.open(Folder.READ_ONLY);
            
            Message messages[] = folder.getMessages();
             
            for (int i=0, n=messages.length; i<n; i++) {
            	  System.out.println(i + ": " 
            	    + messages[i].getFrom()[0] + "\t" + messages[i].getSubject());
            	}
            folder.close(false);
            store.close();
             
        } catch (MessagingException ex) {
             
             
        }
        return null;
	}
}
