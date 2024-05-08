package com.smengs.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.UtilisateurRepository;
import com.smengs.entite.Categorie;
import com.smengs.entite.Demande;
import com.smengs.entite.Utilisateur;
import com.smengs.metier.DemandeMetier;

@Controller
public class DemandeController {
@Autowired
	DemandeMetier dm;
@Autowired
	private UtilisateurRepository ur;
@Autowired
	private CategoriRepository cr;
	
	@RequestMapping(value="creationDemande")
	public String creationDemande(Demande demande, Long id_utilisateur, Long id_cat) {
		
		dm.creerDemande(demande,id_cat,id_utilisateur);
		return "redirect:creerDemande";
	}
	
	
	@RequestMapping(value="creerDemande")
	public String CreerDemande(Model model) {
		
		Demande d = new Demande();
		Utilisateur u = new Utilisateur();
		Categorie c = new Categorie();
		
		List<Utilisateur> lu = ur.findAll();
		List<Categorie> lc = cr.findAll();
		
		model.addAttribute("demande", d);
		model.addAttribute("utilisateur", u);
		model.addAttribute("categorie", c);
		
		model.addAttribute("listeUtilisateur", lu);
		model.addAttribute("listeCategorie", lc);
		
		return "demande/creerDemande";
	}
}
