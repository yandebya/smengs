package com.smengs.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smengs.dao.ElementPromotionRepository;
import com.smengs.entite.ElementPromo;
import com.smengs.entite.Utilisateur;

@Controller
public class ElementPromoController {

	@Autowired
	private UtilisateurController uc;
	@Autowired
	private ElementPromotionRepository epr;
	
	@RequestMapping(value = "creationeltPromotion")
	private String creation(ElementPromo e) {
		epr.save(e);
		return "redirect:creerEltPromotion";
	}
	@RequestMapping(value = "creerEltPromotion")
	private String creer(Model model,HttpServletRequest hsr) {
		ElementPromo e = new ElementPromo();
		model.addAttribute("elementPromo", e);
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
	return "elemtnPromotion/NewElemnt";
}
	@RequestMapping(value = "editerEltPromotion")
	private String editer(Model model,HttpServletRequest hsr,Long id_elt) {
		ElementPromo e = epr.getOne(id_elt);
		model.addAttribute("elementPromo", e);
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
	return "elemtnPromotion/NewElemnt";
}
	@RequestMapping(value = "supprimerEltPromotion")
	private String supprimer(Long id_elt) {
		epr.deleteById(id_elt);
	return "redirect:listeElementPromotion";
}
	@RequestMapping(value = "listeElementPromotion")
	private String LaListes(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		List<ElementPromo> el = epr.findAll();
		model.addAttribute("listeEltPromotion", el);
		return "elemtnPromotion/ListeElement";
	}
}
