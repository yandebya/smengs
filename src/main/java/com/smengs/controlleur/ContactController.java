package com.smengs.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smengs.dao.DomaineRepository;
import com.smengs.dao.PromotionRepository;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Promotion;
import com.smengs.entite.Utilisateur;
import com.smengs.entite.contact;
import com.smengs.metier.ContactMetier;

@Controller
@RequestMapping("index")
public class ContactController {
	@Autowired
	private ServiceController sc;
	@Autowired
	private PromotionRepository pr;
	@Autowired
	private ContactMetier cc;
	@Autowired
	private UtilisateurController uc;
	@Autowired
	private DomaineRepository  dr;
	@RequestMapping(value="creerContact", method = RequestMethod.POST)
	public String creerContact(contact con) {
		
		cc.creerContact(con);
		return"redirect:contact";
	}
	
	@RequestMapping(value="contact")
	public String creationContact(Model model) {
		contact contact = new contact();
		model.addAttribute("creationContact", contact);	
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		sc.listeSer(model);
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		return "site/contact/contact";
	}
	
	@RequestMapping(value = "listeContact")
	public String ListeContact(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		List<contact> listeContact = cc.listeContact();
		model.addAttribute("listeContact", listeContact);
		return "index/table";
	}


}
