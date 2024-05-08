package com.smengs.controlleur;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smengs.dao.AdminRepository;
import com.smengs.dao.CandidatRepository;
import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.dao.FaqRepository;
import com.smengs.dao.HomeRepository;
import com.smengs.dao.InventionRepository;
import com.smengs.dao.OffreRepository;
import com.smengs.dao.PartenaireRepository;
import com.smengs.dao.PromotionRepository;
import com.smengs.dao.QuestionRepository;
import com.smengs.dao.ServiceRepository;
import com.smengs.entite.Candidat;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Invention;
import com.smengs.entite.Offre;
import com.smengs.entite.Partenaire;
import com.smengs.entite.Promotion;
import com.smengs.entite.Service;
import com.smengs.entite.Utilisateur;
import com.smengs.entite.apropos;
import com.smengs.entite.faq;
import com.smengs.entite.home;
import com.smengs.entite.question;
import com.smengs.metier.AproposMetier;

@Controller
@RequestMapping(value="/")
public class Index {
	@Autowired
	private OffreRepository or;

	@Autowired
	private PartenaireRepository par;
	@Autowired
	private PromotionRepository pr;
	@Autowired
	private AproposMetier am;
	@Autowired
	private HomeRepository hr;
	@Autowired
	private FaqRepository fr;
	@Autowired
	private InventionRepository ir;
	
	@Autowired
	private QuestionRepository qr;
	
	@Autowired
	private ServiceController sc;
	
	@Autowired
	private CategoriRepository  cre;
	@Autowired
	private DomaineRepository  dr;
	@Autowired
	private CandidatRepository  candidaRe;
	@Autowired
	private ServiceRepository  sss;
	@Autowired
	private AdminRepository  are;
	
	@Autowired
	private UtilisateurController uc;
	
	@RequestMapping(value="index")
	public String index(Model model) {
		
		return"index/index";
	}
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="home")
	public String home(Model model,HttpServletRequest hsr) {

		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Long a  = cre.nombreCategorie();
		model.addAttribute("nombreCategorie", a);
		
		Long ns = pr.count();
		model.addAttribute("ns", ns);
		
		Long add  = are.nombreAdmin();
		model.addAttribute("nombreAdmin", add);
		Long cadd  = candidaRe.nombreCandidat();
		model.addAttribute("nombreAbonne", cadd);
		/*Long add  = are.nombreAdmin();
		model.addAttribute("nombreAdmin", add);*/
		Long se  = sss.nombreService();
		model.addAttribute("nombreService", se);
		
		return"index/home";
	}
	@Secured(value={"ROLE_CANDIDAT"})
	@RequestMapping(value="homeCandidat")
	public String homeCandidat(Model model,HttpServletRequest hsr) {

		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Long a  = cre.nombreCategorie();
		model.addAttribute("nombreCategorie", a);
		Long add  = are.nombreAdmin();
		model.addAttribute("nombreAdmin", add);
		Long cadd  = candidaRe.nombreCandidat();
		model.addAttribute("nombreAbonne", cadd);
		/*Long add  = are.nombreAdmin();
		model.addAttribute("nombreAdmin", add);*/
		Long se  = sss.nombreService();
		model.addAttribute("nombreService", se);
		
		Long off  = or.count();
		model.addAttribute("nombreOffre", off);
		Long d  = dr.count();
		model.addAttribute("nombreDomaine", d);
		return"index/homeCandidat";
	}
	@Secured(value={"ROLE_SUPERADMIN"})
	@RequestMapping(value="homeSuper")
	public String homeSuper(Model model,HttpServletRequest hsr) {
Long a  = cre.nombreCategorie();
model.addAttribute("nombreCategorie", a);
Long add  = are.nombreAdmin();
model.addAttribute("nombreAdmin", add);
Long cadd  = candidaRe.nombreCandidat();
model.addAttribute("nombreAbonne", cadd);
/*Long add  = are.nombreAdmin();
model.addAttribute("nombreAdmin", add);*/
Long se  = sss.nombreService();
model.addAttribute("nombreService", se);
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		Long off  = or.count();
		model.addAttribute("nombreOffre", off);
		Long d  = dr.count();
		model.addAttribute("nombreDomaine", d);
		Long ns = pr.count();
		model.addAttribute("ns", ns);
		return"index/homeSuper";
	}
	@RequestMapping(value="table")
	public String table(Model model) {

		return"index/table";
	}
	
	
	@Value("${imageUtilisateur}")
	private String photoApp;
	@Value("${imageUtilisateur}")
	private String photoH;
	@RequestMapping(value="homeSiteP")
	public String homeSiteP(home h, @RequestParam(name = "picture") MultipartFile file) throws IOException {
		if (!(file.isEmpty())) {
			h.setImage_home(file.getOriginalFilename());
		}
		
		h.setDate_home(new Date());
		
		hr.save(h);
		if (!(file.isEmpty())) {
			h.setImage_home(file.getOriginalFilename());
			file.transferTo(new File(photoH + h.getImage_home()));
		}
		
		return"redirect:Baniere";
	}
	
	@RequestMapping(value = "/getImage_home", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage_home(String image_home) throws IOException {
		File f = new File(photoH + image_home);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="creerhomeSite")
	public String homeSite(Model model,HttpServletRequest hsr) {
		
		  Utilisateur uu = uc.getLogedUserUc(hsr);
		  model.addAttribute("utilisateur",uu); 
		
		home ho = new home();
		model.addAttribute("home", ho);
		
		return"home/NewHome";
	}
	@RequestMapping(value="modifierhomeSite")
	public String modifierhomeSite(Model model,HttpServletRequest hsr,Long id_home) {
		
		  Utilisateur uu = uc.getLogedUserUc(hsr);
		  model.addAttribute("utilisateur",uu); 
		
		home ho = hr.getOne(id_home);
		model.addAttribute("home", ho);
		
		return"home/NewHome";
	}
	
	@RequestMapping(value = "/")
	public String liste(Model model,
			@RequestParam(name="motCle",defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int p) {
		
		
		Page<question> c =  qr.questionParMotCle("%"+mc+"%",PageRequest.of(p,3));
	    //Page<Categorie> c =  categorieMetier.findAll(PageRequest.of(page, p));
		int pcount=c.getTotalPages();
		int[] pages=new int[pcount];
		for(int ii=0;ii<pcount;ii++) pages[ii]=ii;
		model.addAttribute("pages", pages);
		model.addAttribute("listeQuestion", c);		 
		model.addAttribute("motCle", mc);
		model.addAttribute("pageCourant", p);
		
		
		/*slider infos*/
		List<home> lh = hr.premierPosteHome();
		model.addAttribute("home", lh);
		List<home> hm = hr.dernierPosteHome();
		model.addAttribute("hm", hm);

		
		/*question*/
		//List<question> q = qr.findAll();
		
		//model.addAttribute("listeQuestion", q);
		question qa = new question();
		model.addAttribute("question", qa);
		/*invention*/
		List<Invention> i = ir.dernierPosteinvention();
		model.addAttribute("invention", i);
		
		sc.listeSer(model);
		
		

		
		List<Partenaire> pa = par.findAll();
		model.addAttribute("listePartenaire", pa);
		
		
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		return "site/home/index";
	}

	@RequestMapping(value = "listeOffreDetail")
	public String listeOffreDetail(Model model, Long id_off,HttpServletRequest hsr) {
		
		//Utilisateur uu = uc.getLogedUserUc(hsr);
		/*model.addAttribute("utilisateur", uu);*/
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		List<Offre> lc = or.listeOffreDetail(id_off);
		model.addAttribute("listeOffre", lc);
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		return"site/service/detailOffre";
		
	}
  	@RequestMapping(value = "/getImage_propos1", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage_propos1(String image_propos1) throws IOException {
		File f = new File(photoApp + image_propos1);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	/*@RequestMapping(value = "postuler")
	public String postuler(Model model, Long id_off,HttpServletRequest hsr) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		List<Offre> lc = or.listeOffreDetail(id_off);
		model.addAttribute("listeOffre", lc);
		
		return"site/service/postuler";
		
	}*/
	
	@RequestMapping(value="apropos")
	public String apropos(Model model) {
		
		return"site/apropos/apropos";
	}
	
	@RequestMapping(value="blog")
	public String blog(Model model) {
		
		return"site/blog/blog";
	}
	
	@RequestMapping(value = "can")
	public String can(Model model,HttpServletRequest hsr) {
		
		  Utilisateur uu = uc.getLogedUserUc(hsr);
		  model.addAttribute("utilisateur",uu); 
	
		 
		return "/site/service/can";
	}
	
	@RequestMapping(value="equipe")
	public String equipe(Model model) {
		
		return"site/equipe/equipe";
	}
	
	@RequestMapping(value="promotion")
	public String promotion(Model model) {
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		sc.listeSer(model);
		List<DomaineDemploi> la = dr.findAll();
		model.addAttribute("listeDomaine", la);
		return"site/promotion/promotion";
	}
	
	@RequestMapping(value="service")
	public String service(Model model) {
		
		return"site/service/service";
	}
	/*question*/
	
	@RequestMapping(value="question")
	public String question(question q) {
		q.setDate_q(new Date());
		qr.save(q);
		
		return"redirect:homeSite";
	}
	@RequestMapping(value="creerQ")
	public String creerQ(Model model) {
		question q = new question();
		model.addAttribute("question", q);
		return"site/home/index";
	}
	@RequestMapping(value="listeQuestion")
	public String ListeQuestion(Model model) {
		List<question> q = qr.findAll();
		model.addAttribute("listeQuestion", q);
		return"site/home/index";
	}
	/*invention*/
	
	@RequestMapping(value="inventionCreation")
	public String inventionCreation(Invention i, @RequestParam(name = "picture") MultipartFile file) throws IOException {
		if (!(file.isEmpty())) {
			i.setImage(file.getOriginalFilename());
		}
	
		ir.save(i);
		if (!(file.isEmpty())) {
			i.setImage(file.getOriginalFilename());
			file.transferTo(new File(photoH +i.getImage()));
		}
		
		return"redirect:homeSite";
	}
	
	

	@RequestMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage(String image) throws IOException {
		File f = new File(photoH + image);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}

	@RequestMapping(value="invention")
	public String invention(Model model) {
		Invention q = new Invention();
		model.addAttribute("invention", q);
		return"invention/NewInvention";
	}
	
	
	/*
	 * @RequestMapping(value = "listeInvention") public String listeI(Model model) {
	 * List<Invention> i = ir.findAll(); model.addAttribute("invention", i); return
	 * "site/home/index"; }
	 * 
	 */
	
	
	@RequestMapping(value="footerEquipe")
	public String footA(Model model) {
		List<apropos> lapp = am.listeApp();
		model.addAttribute("listeApp", lapp);
		sc.listeSer(model);
		return"site/footer/equipe/equipe";
	}
	
	@RequestMapping(value = "/getImage_app", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage_app(String image_app) throws IOException {
		File f = new File(photoApp + image_app);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	@RequestMapping(value="footerRencontre")
	public String footN(Model model) {
		List<apropos> lapp = am.listeApp();
		model.addAttribute("listeApp", lapp);
		sc.listeSer(model);
		return"site/footer/rencontrer/rencontre";
	}
	
	@RequestMapping(value="footerFaq")
	public String footFaq(Model model) {
		List<Invention> i = ir.dernierPosteinvention();
		model.addAttribute("invention", i);
		sc.listeSer(model);
		List<question> q = qr.findAll();
		/*question*/
		model.addAttribute("listeQuestion", q);
		question qa = new question();
		model.addAttribute("question", qa);
		
		List<faq> faqs = fr.findAll();
		model.addAttribute("faq", faqs);
		return"site/footer/faq/faq";
	}
	@RequestMapping(value="questionFaq")
	public String questionFaq(question q) {
		q.setDate_q(new Date());
		qr.save(q);
		
		return"redirect:footerFaq";
	}
	
	
	
	@RequestMapping(value="footerForum")
	public String footFor(Model model) {
		List<apropos> lapp = am.listeApp();
		model.addAttribute("listeApp", lapp);
		sc.listeSer(model);
		return"site/footer/forum/forum";
	}
	@RequestMapping(value="footerDocument")
	public String footDoc(Model model) {
		List<apropos> lapp = am.listeApp();
		model.addAttribute("listeApp", lapp);
		sc.listeSer(model);
		List<Promotion> lp = pr.findAll();
		model.addAttribute("ListePromotion", lp);
		return"site/footer/document/document";
	}
	
	
	@RequestMapping(value="ListeInvention")
	public String ListeInvention(Model model) {
		/*invention*/
		List<Invention> i = ir.findAll();
		model.addAttribute("invention", i);
		return "invention/ListeInvention";
	}
	
	
	@RequestMapping(value="supInvitation")
	public String supprimer(Long id) {	
		ir.deleteById(id);
		return"redirect:ListeInvention";
	}
	
	
	@RequestMapping(value="inventionEditer")
	public String inventionEditeur(Invention i, @RequestParam(name = "picture") MultipartFile file) throws IOException {
		if (!(file.isEmpty())) {
			i.setImage(file.getOriginalFilename());
		}
	
		ir.save(i);
		if (!(file.isEmpty())) {
			i.setImage(file.getOriginalFilename());
			file.transferTo(new File(photoH +i.getImage()));
		}
		
		return"redirect:ListeInvention";
	}
	
	@RequestMapping(value="editInvitation")
	public String editInvitation(Long id,Model model) {	
		Invention i =ir.getOne(id);
		model.addAttribute("invention", i);
		return"invention/EditerInvention";
	}
	
	@RequestMapping(value="ListeQuestion")
	public String question(Model model) {
		/*question*/
		List<question> q = qr.findAll();
		model.addAttribute("listeQuestion", q);
		question qa = new question();
		model.addAttribute("question", qa);
		return "question/ListeQuestion";
	}
	
	@RequestMapping(value="Baniere")
	public String banniere(Model model,HttpServletRequest hsr) {
		
		  Utilisateur uu = uc.getLogedUserUc(hsr);
		  model.addAttribute("utilisateur",uu); 
		
		List<home> lh = hr.findAll();
		model.addAttribute("home", lh);
		return "home/ListeBaniere";
	}
	
	@RequestMapping(value="supBaniere")
	public String supprimerBaniere(Long id_home) {	
		hr.deleteById(id_home);
		return"redirect:Baniere";
	}
	
	@RequestMapping(value="monCompte")
	public String Moncompte(Model model,HttpServletRequest hsr) {
		
		  Utilisateur uu = uc.getLogedUserUc(hsr);
		  model.addAttribute("utilisateur",uu); 
		
		return "utilisateur/monCompte";
	}
}
