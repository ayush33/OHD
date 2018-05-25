package services;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	public static void sendMail(String email,String subject,String information) throws UnsupportedEncodingException{
		final String username="";
		final String password="";	
		Properties props=new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");//for yahoo it is smtp.mail.yahoo.com
		props.put("mail.smtp.auth","true");
		props.put("mail.debug","true");
		props.put("mail.smtp.port","465");
		props.put("mail.smtp.socketFactory.port","465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback","false");
		Session s=Session.getInstance(props,new Authenticator()
		  {   protected PasswordAuthentication getPasswordAuthentication()
		  	 {
		  		return new PasswordAuthentication(username,password);		
		  	 }
		  			
		  });
		try
		   {			  
		  		Message message=new MimeMessage(s);
		  		message.setFrom(new InternetAddress(username));
		  		    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
		  		    message.setFrom(new InternetAddress("mail2anmolmadaan@gmail.com", "OHD"));
		  		    message.setSubject(subject);
		  		    message.setText(information);
		  		    Transport.send(message);
		   }
			catch(MessagingException e)
			{
				e.printStackTrace();
			
			}	
	}
}
