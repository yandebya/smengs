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

@Controller
public class CategorieController {
	
	@Autowired
	private UtilisateurController uc;
	@Autowired
	private CategoriRepository cr;
	@Autowired
	private DomaineRepository dr;

	@RequestMapping(value = "creationCategorie")
	public String creationCat(Categorie c,Long id_domaine) {
		DomaineDemploi id = dr.getOne(id_domaine);
		c.setDomaineDemploi(id);
		cr.save(c);
		return"redirect:creerCategorie";
	}

	@RequestMapping(value = "creerCategorie")
	public String creerCat(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		Categorie c = new Categorie();
		model.addAttribute("categorie", c);
		
		DomaineDemploi dm = new DomaineDemploi();
		model.addAttribute("domaine", dm);
		List<DomaineDemploi> lst = dr.findAll();
		model.addAttribute("listeDomaine", lst);
		return"/categorie/creerCategorie";
	}
	
	@RequestMapping(value = "listeCategorie")
	public String Liste(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<Categorie> lc = cr.findAll();
		model.addAttribute("listeCategorie", lc);
		
		return"/categorie/listeCategorie";
	}
	@RequestMapping(value = "ListeCatDom")
	public String ListeCateDomaine(Model model,HttpServletRequest hsr,Long id_domaine) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<Categorie> lc = cr.listeCatParDomaine(id_domaine);
		model.addAttribute("listeCategorie", lc);
		
		return"/categorie/listeCategorie";
	}

	
	@RequestMapping(value="supprimerCategorie")
	public String supprimer(Model model,Long id_cat,HttpServletRequest hsr) {
		
		cr.deleteById(id_cat);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		return "redirect:listeCategorie";
		
	}
	
	
	@RequestMapping(value="editerCat")
	public String editerDOmaine(Long id_cat, Model model,HttpServletRequest hsr) {
		
		Categorie d = cr.getOne(id_cat);
		model.addAttribute("categorie", d);
		
		DomaineDemploi dm = new DomaineDemploi();
		model.addAttribute("domaine", dm);
		List<DomaineDemploi> lst = dr.findAll();
		model.addAttribute("listeDomaine", lst);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		return"/categorie/creerCategorie";
	}
	
}
