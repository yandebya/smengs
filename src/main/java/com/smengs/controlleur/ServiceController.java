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

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.dao.OffreRepository;
import com.smengs.dao.PromotionRepository;
import com.smengs.dao.ServiceRepository;
import com.smengs.dao.UtilisateurRepository;
import com.smengs.entite.Categorie;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Offre;
import com.smengs.entite.Promotion;
import com.smengs.entite.Service;
import com.smengs.entite.Utilisateur;
import com.smengs.entite.apropos;
import com.smengs.metier.OffreMetier;
import com.smengs.metier.homeServiceMetier;



@Controller
@RequestMapping(value = "index")
public class ServiceController {
	
	@Autowired
	private PromotionRepository pr;
	@Autowired
	private DomaineRepository dr;
	@Autowired
	private OffreRepository or;
	@Autowired
	private ServiceRepository sr;
	@Autowired
	private homeServiceMetier hsm;
	@Autowired
	 public OffreMetier senderService;
	@Autowired
	private CategoriRepository cr;

	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	UtilisateurController uc;
	
	
	@Value("${imageUtilisateur}")
	private String photoSer;
	@RequestMapping(value = "creerService")
	public String creerApp(Model model,Service app ,Long id_cat,  HttpServletRequest hsr, @RequestParam(name = "picture") MultipartFile file) throws IOException {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		if (!(file.isEmpty())) {
			app.setPhotiService(file.getOriginalFilename());
		}
		hsm.creerService(app, id_cat, hsr);
		//sr.save(app);
		if (!(file.isEmpty())) {
			app.setPhotiService(file.getOriginalFilename());
			file.transferTo(new File(photoSer + app.getPhotiService()));
		}
		return "redirect:listeSerSite";
	}
	
	@RequestMapping(value = "/getPhotiService", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhotiService(String photiService) throws IOException {
		File f = new File(photoSer + photiService);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	
	@RequestMapping(value = "creationSerive")
	public String creationApp(Model model) {
		Service a = new Service();
	model.addAttribute("service", a);
	
	
	
	Utilisateur u = new Utilisateur();
	model.addAttribute("Utilisateur", u);
	
	Categorie cat = new Categorie();
	model.addAttribute("categorie", cat);
	
	List<Categorie> lc = cr.findAll();
	model.addAttribute("listeCategorie", lc);
	
	
	return "service/NewService";
	}
/**/
	@RequestMapping(value = "listeSerSite")
	public String ListeServiceSite(Model model,Long id_ser,Long id_domaine,HttpServletRequest hsr) {
		//Utilisateur uu = uc.getLogedUserUc(hsr);
		List<Service> lapp = sr.findAll();
		model.addAttribute("listeSer", lapp);
		footerService( model,id_ser);
		
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		List<Offre> ss = or.listeOffreDomaine(id_domaine);
		model.addAttribute("listeOffre", ss);
		Long a  = cr.nombreCategorieParDomaine(id_domaine);
		model.addAttribute("nombreCategorieDomaine", a);
		
	return "site/service/service";
	}
	
	//@EventListener(ApplicationReadyEvent.class)
	@RequestMapping(value = "sendMail")
	public String sendMail(Model model,HttpServletRequest hsr,Long id_utilisateur) {
		
		Utilisateur u = ur.getOne(id_utilisateur);
		
		String emailDestinateur=u.getEmail_utilisateur();
		String body;
		String sujet;
		
	
		senderService.SendEmail(emailDestinateur, "zerty", "uyez", hsr);	
		
		return "redirect:listeSerSite";
	}
	
	
	@RequestMapping(value = "listeOffreDomaine")
	public String ListeoffreParDomaine(Model model,Long id_domaine,HttpServletRequest hsr) {
		//Utilisateur uu = uc.getLogedUserUc(hsr);
		List<Offre> lapp = or.listeOffreDomaine(id_domaine);

		model.addAttribute("listeOffre", lapp);
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		List<Offre> vide = or.findAll();
		if(vide.isEmpty()) {
			model.addAttribute("vide", vide);
		}
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		
	return "site/service/offre";
	
}
	@RequestMapping(value = "listeService")
	public String ListeService(Model model,Long id_ser) {
	
		List<Service> lapp = sr.findAll();
		model.addAttribute("listeServ", lapp);
		
		
		
	return "service/tableService";
}
	public List<Service> listeSer(Model m) {
		List<Service> lapp = sr.findAll();
		m.addAttribute("listeSer", lapp);
	return sr.findAll();
}
	
	@RequestMapping(value="footerService")
	public String footerService(Model model, Long id_ser) {
		List<Service> lapp =sr.ServiceParId(id_ser);
		model.addAttribute("service", lapp);
		
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		
		listeSer(model);
		return"site/footer/service/service";
	}
	

	public List<Service> listeDert(Model model, Long id_ser) {

		return sr.ServiceParId(id_ser);
	}
	
}
