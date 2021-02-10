package com.gessco.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gessco.entite.contact;
import com.gessco.metier.ContactMetier;

@Controller
@RequestMapping("index")
public class ContactController {
	@Autowired
	private ServiceController sc;
	@Autowired
	private ContactMetier cc;
	
	
	@RequestMapping(value="creerContact", method = RequestMethod.POST)
	public String creerContact(contact con) {
		
		cc.creerContact(con);
		return"redirect:listeContact";
	}
	
	@RequestMapping(value="contact")
	public String creationContact(Model model) {
		contact contact = new contact();
		model.addAttribute("creationContact", contact);	
		sc.listeSer(model);
		return "site/contact/contact";
	}
	
	@RequestMapping(value = "listeContact")
	public String ListeContact(Model model) {
		List<contact> listeContact = cc.listeContact();
		model.addAttribute("listeContact", listeContact);
		return "index/table";
	}

}
