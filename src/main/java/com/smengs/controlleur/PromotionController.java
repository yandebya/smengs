package com.smengs.controlleur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smengs.dao.ElementPromotionRepository;
import com.smengs.dao.PromotionRepository;
import com.smengs.entite.ElementPromo;
import com.smengs.entite.Promotion;
import com.smengs.entite.Utilisateur;
@Controller
public class PromotionController {

	@Autowired
	private UtilisateurController uc;
	@Autowired
	private PromotionRepository pr;
	@Autowired
	private ElementPromotionRepository epr;
	
	@RequestMapping(value = "creationPromotion")
	private String creation(Promotion p) {
		pr.save(p);
		
		return "redirect:creerPromotion";
	}
	
	@RequestMapping(value = "creerPromotion")
	private String creer(Model model ,HttpServletRequest hsr) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Promotion p = new Promotion();
		ElementPromo ep = new ElementPromo();
		List<ElementPromo> lep = epr.findAll();
		
		model.addAttribute("promotion", p);
		model.addAttribute("elementPromotion", ep);
		model.addAttribute("listeElementPromotion", lep);
		
		return "Promotion/NewPromotion";
	}
	
	@RequestMapping(value = "ListePromotion")
	private String listPro(Model model ,HttpServletRequest hsr) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		
		Long ns = pr.count();
		model.addAttribute("ns", ns);
		
		
		return "Promotion/ListePromotion";
	}
	
	
	@RequestMapping(value = "editerPromotion")
	private String Modifier(Model model ,HttpServletRequest hsr,Long id_promo) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Promotion pro = pr.getOne(id_promo);
		ElementPromo ep = new ElementPromo();
		List<ElementPromo> lep = epr.findAll();
		model.addAttribute("promotion", pro);
		model.addAttribute("elementPromotion", ep);
		model.addAttribute("listeElementPromotion", lep);
		
		return "Promotion/NewPromotion";
	}
	@RequestMapping(value = "supprimerPromotion")
	private String supprimer(Model model ,HttpServletRequest hsr,Long id_promo) {
		
		pr.deleteById(id_promo);
		
		return "redirect:ListePromotion";
	}
	
}
