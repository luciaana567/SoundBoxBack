package com.SoundBox.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilSecuriry {

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private JavaMailSender mailSender;
	
	@Value("${apikey}")
	private String apikey;
	
	@Value("${secretkey}")
	private String secretkey;
	
//	@Value("${spring.mail.username}")
//	private String sender;

//	public boolean sendEmail(String email, String senha) throws IOException {
//		 MailjetRequest request;
//	     MailjetResponse response;
//	     
//	     try {
//	         ClientOptions options = ClientOptions.builder()
//	               .apiKey(apikey)
//	               .apiSecretKey(secretkey)
//	               .build();
//	         
//	         MailjetClient client = new MailjetClient(options);
//
//	         request = new MailjetRequest(Emailv31.resource)
//	               .property(Emailv31.MESSAGES, new JSONArray()
//	                   .put(new JSONObject()
//	                       .put(Emailv31.Message.FROM, new JSONObject()
//	                           .put("Email", sender)
//	                           .put("Name", "Cecilia's Care"))
//	                       .put(Emailv31.Message.TO, new JSONArray()
//	                           .put(new JSONObject()
//	                               .put("Email", email)
//	                               .put("Name", "You")))
//							 .put(Emailv31.Message.SUBJECT, SUBJECT)
//							 .put(Emailv31.Message.TEXTPART, TEXTBODY + " :senha")
//							 .put(Emailv31.Message.HTMLPART,HTMLBODY + senha)));
//	         response = client.post(request);
//	         System.out.println(response.getStatus());
//	         System.out.println(response.getData());
//	    	 
//			return true;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//	}

	public String enconde(String password) {
		return passwordEncoder.encode(password);
	}

	public boolean match(String passwordDTOs, String passwordEntity) {
		return passwordEncoder.matches(passwordDTOs, passwordEntity);
	}

	// Replace sender@example.com with your "From" address.
	// This address must be verified with Amazon SES.
	static final String FROM = "email-smtp.sa-east-1.amazonaws.com";

	// Replace recipient@example.com with a "To" address. If your account
	// is still in the sandbox, this address must be verified.
	static final String TO = "recipient@example.com";

	// The configuration set to use for this email. If you do not want to use a
	// configuration set, comment the following variable and the
	// .withConfigurationSetName(CONFIGSET); argument below.
	static final String CONFIGSET = "sendEmail";

	// The subject line for the email.
	static final String SUBJECT = "Alteração de senha";

	// The HTML body for the email.
	static final String HTMLBODY = "<h1>Solicitação de alteração de senha</h1>"
			+ "Esse email foi enviado por SoundBox sua nova senha é: ";

	// The email body for recipients with non-HTML email clients.
	static final String TEXTBODY = "Esse email foi enviado por SoundBox " + "sua nova senha é: ";

}
