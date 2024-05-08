package com.smengs.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailSenderService {
	
/*	@Autowired
	private JavaMailSender mailSender;
	
	public void SendEmail(String to,
						  String sujet,
						  String body
						  ) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("rotayandebya@gmail.com");
		message.setTo(to);
		message.setSubject(sujet);
		message.setText(body);

		
		mailSender.send(message);
		System.out.print("envoi d'email");
	}*/

}
