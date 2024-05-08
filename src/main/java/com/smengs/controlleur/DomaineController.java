package com.smengs.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.entite.Categorie;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Utilisateur;
import com.smengs.metier.DomaineMetier;



@Controller
public class DomaineController {
	
	@Autowired
	private DomaineRepository dr;
	@Autowired
	private CategoriRepository cr;
	@Autowired
	private UtilisateurController uc;
	@Autowired
	private DomaineMetier dm;
	
 @RequestMapping(value = "creationDomaine")
	public String creationDomaine(Categorie c,DomaineDemploi a,String password,Long id_cat) {
		
		dr.save(a);
		return "redirect:creerDomaine";
	}
	
	@RequestMapping(value = "creerDomaine")
	public String creerDomaineDemploi(Model model,HttpServletRequest hsr) {
		
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		DomaineDemploi a = new DomaineDemploi();
		model.addAttribute("Domaine", a);
		return "domaine/creerDomaine";
	}
	
	@RequestMapping(value = "listeDomaine")
	public String listeDomaineDemploi(Model model,HttpServletRequest hsr) {
		
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		return "domaine/listeDomaine";
	}
	
	
	@RequestMapping(value="supprimerDomaine")
	public String supprimer(Model model,Long id_domaine,HttpServletRequest hsr) {
		
		dm.supprimerDomaine(id_domaine);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		return "redirect:listeDomaine";
	}
	
	@RequestMapping(value="editerDomaine")
	public String editerDOmaine(Long id_domaine, Model model,HttpServletRequest hsr) {
		
		DomaineDemploi d = dm.editerDomoaine(id_domaine);
		model.addAttribute("Domaine", d);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		return "domaine/creerDomaine";
	}
}
