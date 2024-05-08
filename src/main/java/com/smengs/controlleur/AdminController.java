package com.smengs.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smengs.dao.AdminRepository;
import com.smengs.entite.Admin;
import com.smengs.entite.Categorie;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Utilisateur;
import com.smengs.metier.AdminMetier;

@Controller
public class AdminController {

	@Autowired
	private AdminMetier am;
	@Autowired
	private AdminRepository ur;
	@Autowired
	private UtilisateurController uc;
	
	@RequestMapping(value = "creationAdmin")
	public String creationAdmin(Admin a,String password) {
		
		am.creerAdmin(a,password);
		return "redirect:creerAdmin";
	}
	
	@RequestMapping(value = "creerAdmin")
	public String creerAdmin(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		Admin a = new Admin();
		model.addAttribute("admin", a);
		return "admin/creerAdmin";
	}
	
	@RequestMapping(value = "listeAdmin")
	public String listeAdmin(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		List<Admin> la = am.listeAdmin();
		model.addAttribute("listeAdmin", la);
		return "admin/listeAdmin";
	}
	
	
	
	
		@RequestMapping(value="/supprimerUtilisateur")
	public String supprimer(Model model,Long id_utilisateur,HttpServletRequest hsr) {
		
		ur.deleteById(id_utilisateur);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		return "redirect:listeAdmin";
		
	}
	
	
	@RequestMapping(value="editerUtilisateur")
	public String editerUtilisateur(Long id_utilisateur, Model model,HttpServletRequest hsr) {
		
		Utilisateur d = ur.getOne(id_utilisateur);
		model.addAttribute("admin", d);
		
	
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
				return "admin/creerAdmin";
	}

}
