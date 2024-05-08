package com.smengs.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smengs.dao.DomaineRepository;
import com.smengs.dao.PromotionRepository;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Promotion;
import com.smengs.entite.Utilisateur;
import com.smengs.metier.UtilisateurMertier;

@ Controller
//@RequestMapping(value = "utilisateur")
public class UtilisateurController {

	@Autowired
	private PromotionRepository pr;
	
	@Autowired
	private UtilisateurMertier um;
	
	@Autowired
	private DomaineRepository  dr;
	@RequestMapping(value = "/login")
	public String Login(Model model) {
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		return "/login/login";
	}
	@RequestMapping(value = "creerComte")
	public String creerCompte(Model model) {
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		return "login/creerCompte";
	}
	
	@RequestMapping(value = "creerUtilisateur")
	public String CreerUtilisateur(Utilisateur u, String password) {
		 um.saveUtilsateur(u, password);
		return "redirect:listeUtilisateur";
	}
	
	@RequestMapping(value = "creationUtilisateur")
	public String creationUtilisateur(Model model,HttpServletRequest hsr) {
		Utilisateur u = new Utilisateur();
		model.addAttribute("utilisateur", u);
		Utilisateur uu = getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		return "utilisateur/NewUtilisateur";
	}
	
	@RequestMapping(value = "listeUtilisateur")
	public String Liste(Model model,HttpServletRequest hsr) {
		List<Utilisateur> lu = um.listeUtilisateur();
		model.addAttribute("listeUtilisateur", lu);
		Utilisateur u = getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "utilisateur/ListeUtilisateur";
	}
	@RequestMapping(value = "exemple")
	public String ex(Model model,HttpServletRequest hsr) {
		
		Utilisateur u = getLogedUserUc(hsr);
		model.addAttribute("utilisateur", u);
		return "utilisateur/exemple";
	}
	
	//methode a utiliser pour afficher le nom et la photo de l'utilisateur connecté
			public Utilisateur getLogedUserUc(HttpServletRequest hsr){ 
				HttpSession hs =hsr.getSession();
				SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT");
				String username=sc.getAuthentication().getName();
			  return um.SessionU(username);
			  }
}
