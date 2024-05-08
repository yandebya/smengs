package com.smengs.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.dao.OffreRepository;
import com.smengs.dao.UtilisateurRepository;
import com.smengs.entite.Categorie;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Offre;
import com.smengs.entite.Utilisateur;
import com.smengs.metier.OffreMetier;

@Controller
@RequestMapping(value = "/offre")
public class OffreController {
	
	
	@Autowired
	private CategoriRepository cr;
	
	@Autowired
	private OffreRepository or;
	@Autowired
	private DomaineRepository dr;
	@Autowired
	private OffreMetier om;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private UtilisateurController uc;
	

	@Value("${imageUtilisateur}")
	private String photoSer;
	@RequestMapping(value = "/creationOffre")
	public String creationOffre(Offre o, Long id_cat, Long id_utilisateur,Long id_domaine, @RequestParam(name = "picture") MultipartFile file,HttpServletRequest hsr) throws IOException{
		
		if (!(file.isEmpty())) {
			o.setPhoto_off(file.getOriginalFilename());
		}
		
		om.creerOffre(o,id_cat,id_domaine,hsr);
		
		if (!(file.isEmpty())) {
			o.setPhoto_off(file.getOriginalFilename());
			file.transferTo(new File(photoSer + o.getPhoto_off()));
		}
		return"redirect:creerOffre";
	}
	
		@RequestMapping(value = "/getPhoto_off", produces = MediaType.ALL_VALUE)
	@ResponseBody
	public byte[] getPhoto_off(String photo_off) throws IOException {
		File f = new File(photoSer + photo_off);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	

	@RequestMapping(value = "creerOffre")
	public String creerOffre(Model model,HttpServletRequest hsr) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Offre c = new Offre();
		model.addAttribute("Offre", c);
		
		Utilisateur u = new Utilisateur();
		model.addAttribute("Utilisateur", u);
		
		Categorie cat = new Categorie();
		model.addAttribute("categorie", cat);
		
		List<Categorie> lc = cr.findAll();
		model.addAttribute("listeCategorie", lc);
		
		List<Utilisateur> lu = ur.findAll();
		model.addAttribute("listeUtilisateur", lu);
		
		DomaineDemploi dm = new DomaineDemploi();
		model.addAttribute("domaine", dm);
		List<DomaineDemploi> lst = dr.findAll();
		model.addAttribute("listeDomaine", lst);
		
		
		return"/offre/creerOffre";
	}
	
	@RequestMapping(value = "/listeOffre")
	public String Liste(Model model,HttpServletRequest hsr,Long id_domaine) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<Offre> lc = om.listeOffre();
		model.addAttribute("listeOffre", lc);
		
		
		return"/offre/listeOffre";
	}
	
	@RequestMapping(value = "listeOffreCat")
	public String listeOffreCat(Model model, Long id_cat,HttpServletRequest hsr) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<Offre> lc = or.listeCatParOffre(id_cat);
		model.addAttribute("listeOffre", lc);
		
		return"/offre/listeOffre";
	}
	
	@RequestMapping(value = "listeOffreDetail")
	public String listeOffreDetail(Model model, Long id_off,HttpServletRequest hsr) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<Offre> lc = or.listeOffreDetail(id_off);
		model.addAttribute("listeOffre", lc);
		
		return"/offre/detailOffre";
	}

	
	
	@RequestMapping(value="supprimerOffre")
	public String supprimerOffre(Model model,Long id_off,HttpServletRequest hsr) {
		
		or.deleteById(id_off);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		return "redirect:listeOffre";
		
	}
	
	
	@RequestMapping(value="editerOffre")
	public String editerDOmaine(Long id_off, Model model,HttpServletRequest hsr) {
		
		Offre d = or.getOne(id_off);
		model.addAttribute("Offre", d);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		
		
		Utilisateur u = new Utilisateur();
		model.addAttribute("Utilisateur", u);
		
		Categorie cat = new Categorie();
		model.addAttribute("categorie", cat);
		
		List<Categorie> lc = cr.findAll();
		model.addAttribute("listeCategorie", lc);
		
		List<Utilisateur> lu = ur.findAll();
		model.addAttribute("listeUtilisateur", lu);
		
		DomaineDemploi dm = new DomaineDemploi();
		model.addAttribute("domaine", dm);
		List<DomaineDemploi> lst = dr.findAll();
		model.addAttribute("listeDomaine", lst);
		
		return"/offre/creerOffre";
	}
	
	
	
	
	

	
}
