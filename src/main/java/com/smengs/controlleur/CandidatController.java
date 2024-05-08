package com.smengs.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.dao.PromotionRepository;
import com.smengs.entite.Candidat;
import com.smengs.entite.Categorie;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Promotion;
import com.smengs.entite.Utilisateur;
import com.smengs.metier.CandidatMetier;

@Controller
@RequestMapping(value = "index")
public class CandidatController {
	@Autowired
	private DomaineRepository  dr;
	@Autowired
	private UtilisateurController uc;

	 @Autowired
	private CandidatMetier cm;
	 @Autowired
	private CategoriRepository cr;
		@Autowired
		private PromotionRepository pr;
	
	@RequestMapping(value = "creationCandidat")
	public String creationCandidat(Candidat a,String password) {
		
		cm.creerCandidat(a,password);
		return "redirect:creerCandidat";
	}
	
	@RequestMapping(value = "/creerCandidat")
	public String creerCandidat(Model model,HttpServletRequest hsr) {
		
		Categorie dd = new Categorie();
		List<Categorie> ld=cr.findAll();
		model.addAttribute("categorie", dd);
		model.addAttribute("listeCategorie", ld);
		Candidat a = new Candidat();
		model.addAttribute("candidat", a);
		
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		return "login/creerCompte";
	}
	
	@RequestMapping(value = "listeCandidat")
	public String listeCandidat(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		List<Candidat> la = cm.listeCandidat();
		model.addAttribute("listeCandidat", la);
		return "candidat/listeCandidat";
	}
	

	 
}
