package com.gessco.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gessco.metier.UtilisateurMertier;
import com.gessco.entite.Utilisateur;

@ Controller
//@RequestMapping(value = "utilisateur")
public class UtilisateurController {

	
	
	@Autowired
	private UtilisateurMertier um;
	
	
	@RequestMapping(value = "login")
	public String Login() {
		
		return "login/login";
	}
	
	@RequestMapping(value = "creerUtilisateur")
	public String CreerUtilisateur(Utilisateur u, String password) {
		 um.saveUtilsateur(u, password);
		return "redirect:listeUtilisateur";
	}
	
	@RequestMapping(value = "creationUtilisateur")
	public String creationUtilisateur(Model model) {
		Utilisateur u = new Utilisateur();
		model.addAttribute("utilisateur", u);
		return "utilisateur/NewUtilisateur";
	}
	
	@RequestMapping(value = "listeUtilisateur")
	public String Liste(Model model) {
		List<Utilisateur> lu = um.listeUtilisateur();
		model.addAttribute("listeUtilisateur", lu);
		return "utilisateur/ListeUtilisateur";
	}
	
	
	//methode a utiliser pour afficher le nom et la photo de l'utilisateur connecté
			public Utilisateur getLogedUserUc(HttpServletRequest hsr){ 
				HttpSession hs =hsr.getSession();
				SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT");
				String username=sc.getAuthentication().getName();
			  return um.SessionU(username);
			  }
}
