package com.smengs.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smengs.dao.Apropos1Repository;
import com.smengs.dao.Apropos2SectionRepository;
import com.smengs.dao.AproposRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.dao.PromotionRepository;
import com.smengs.entite.Apropos1;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Partenaire;
import com.smengs.entite.Promotion;
import com.smengs.entite.Utilisateur;
import com.smengs.entite.apropos;
import com.smengs.entite.apropos2Section;
import com.smengs.metier.AproposMetier;

@Controller
@RequestMapping(value = "apropos")
public class Apropos {
	@Autowired
	private PromotionRepository pr;
	@Autowired
	private ServiceController sc;
	@Autowired
	private AproposMetier am;
	@Autowired
	private AproposRepository ar;
	@Autowired
	private UtilisateurController uc;
	@Autowired
	private Apropos1Repository a1r;
	@Autowired
	 private Apropos2SectionRepository a2r;
	@Autowired
	private DomaineRepository  dr;
	@Value("${imageUtilisateur}")
	private String photoApp;
	@RequestMapping(value = "creerApropos")
	public String creerApp(apropos app, @RequestParam(name = "picture") MultipartFile file) throws IOException {
		if (!(file.isEmpty())) {
			app.setImage_app(file.getOriginalFilename());
		}
		am.creerApp(app);
		if (!(file.isEmpty())) {
			app.setImage_app(file.getOriginalFilename());
			file.transferTo(new File(photoApp + app.getImage_app()));
		}
		return "redirect:listeApp";
	}
	
	
	@RequestMapping(value = "/getImage_app", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage_app(String image_app) throws IOException {
		File f = new File(photoApp + image_app);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value = "creationApropos")
	public String creationApp(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
	apropos a = new apropos();
	model.addAttribute("apropos", a);
	
	return "apropos/NewApropos";
	}
	@RequestMapping(value = "editApp")
	public String editEquipe(Model model,HttpServletRequest hsr,Long id_app) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
	apropos a =ar.getOne(id_app);
	model.addAttribute("apropos", a);
	
	return "apropos/NewApropos";
	}
	/*liste equipes*/
	@RequestMapping(value = "listeApp")
	public String ListeApp(Model model,HttpServletRequest hsr) {
	
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<apropos> lapp = am.listeApp();
		model.addAttribute("listeApp", lapp);
	return "apropos/tableApropos";
}
	@RequestMapping(value = "listeAppSite")
	public String ListeAppSite(Model model,HttpServletRequest hsr) {
	
		//Utilisateur uu = uc.getLogedUserUc(hsr);
		//model.addAttribute("utilisateur", uu);
		
		List<apropos> lapp = am.listeApp();
		model.addAttribute("listeApp", lapp);
		/* liste deuxieme section */
		List<apropos2Section> lapp2 = a2r.findAll();
		model.addAttribute("listeApp2", lapp2);
		/*fin listre*/
		
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		List<Apropos1> la1 = a1r.dernierPosteApropos1();
		model.addAttribute("listeApropos1", la1);

		sc.listeSer(model);
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
	return "site/apropos/apropos";
}
	
	
	/* deuxieme section*/
	
	@RequestMapping(value = "creationApropos2")
	public String creationApp2(Model model,HttpServletRequest hsr) {
		apropos2Section a2 = new apropos2Section();
	model.addAttribute("apropos2", a2);
	
	Utilisateur uu = uc.getLogedUserUc(hsr);
	model.addAttribute("utilisateur", uu);
	return "apropos/NewApropos2";
	}

  	@RequestMapping(value = "/getImage_propos1", produces = MediaType.IMAGE_JPEG_VALUE)
@ResponseBody
public byte[] getImage_propos1(String image_propos1) throws IOException {
	File f = new File(photoApp + image_propos1);


	return IOUtils.toByteArray(new FileInputStream(f));
}
	
	@RequestMapping(value = "listeApp2")
	public String liste2App(Model model,HttpServletRequest hsr) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<apropos2Section> lapp2 = a2r.findAll();
		model.addAttribute("listeApp2", lapp2);
		return "apropos/tableApropos2";
	}
	@Value("${imageUtilisateur}")
	private String photoApp2;
	@RequestMapping(value = "creerApropos2")
	public String creerApp2(apropos2Section app, @RequestParam(name = "picture") MultipartFile file) throws IOException {
		if (!(file.isEmpty())) {
			app.setImage_app2(file.getOriginalFilename());
		}
		a2r.save(app);
		if (!(file.isEmpty())) {
			app.setImage_app2(file.getOriginalFilename());
			file.transferTo(new File(photoApp + app.getImage_app2()));
		}
		return "redirect:listeApp2";
	}
	
	@RequestMapping(value = "editerApp2")
	public String editerPartenaire(Model model,Long id_app2,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		apropos2Section a = a2r.getOne(id_app2);
		model.addAttribute("apropos2", a);
		
		return "apropos/NewApropos2";
	}

@RequestMapping(value = "supprimerApp2")
public String supprimerPartenaire(Model model,Long id_app2,HttpServletRequest hsr) {
	Utilisateur uu = uc.getLogedUserUc(hsr);
	model.addAttribute("utilisateur", uu);
	
	a2r.deleteById(id_app2);
	
	return "redirect:listeApp2";
}

/*fin deuxieme section*/
	
	@RequestMapping(value = "/getImage_app2", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage_app2(String image_app2) throws IOException {
		File f = new File(photoApp + image_app2);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	
	
	
	
	@RequestMapping(value="supEquipe")
	public String supprimerEquipe(Long id_app) {	
		ar.deleteById(id_app);
		return"redirect:listeApp";
	}
}
