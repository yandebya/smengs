package com.gessco.controlleur;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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

import com.gessco.dao.HomeRepository;
import com.gessco.dao.InventionRepository;
import com.gessco.dao.QuestionRepository;
import com.gessco.entite.Invention;
import com.gessco.entite.Service;
import com.gessco.entite.apropos;
import com.gessco.entite.home;
import com.gessco.entite.question;
import com.gessco.metier.AproposMetier;

@Controller
@RequestMapping(value="index")
public class Index {
	@Autowired
	private AproposMetier am;
	@Autowired
	private HomeRepository hr;
	@Autowired
	private InventionRepository ir;
	
	@Autowired
	private QuestionRepository qr;
	
	@Autowired
	private ServiceController sc;
	
	@RequestMapping(value="index")
	public String index(Model model) {
		
		return"index/index";
	}
	
	@RequestMapping(value="home")
	public String home(Model model) {

		return"index/home";
	}
	@RequestMapping(value="table")
	public String table(Model model) {

		return"index/table";
	}
	
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
	public String homeSite(Model model) {
		home ho = new home();
		model.addAttribute("home", ho);
		
		return"home/NewHome";
	}
	
	@RequestMapping(value = "homeSite")
	public String liste(Model model) {
		/*slider infos*/
		List<home> lh = hr.findAll();
		model.addAttribute("home", lh);
		List<home> hm = hr.dernierPosteHome();
		model.addAttribute("hm", hm);

		
		/*question*/
		List<question> q = qr.findAll();
		
		model.addAttribute("listeQuestion", q);
		question qa = new question();
		model.addAttribute("question", qa);
		/*invention*/
		List<Invention> i = ir.dernierPosteinvention();
		model.addAttribute("invention", i);
		
		sc.listeSer(model);
		
		return "site/home/index";
	}
	
	@RequestMapping(value="apropos")
	public String apropos(Model model) {
		
		return"site/apropos/apropos";
	}
	
	@RequestMapping(value="blog")
	public String blog(Model model) {
		
		return"site/blog/blog";
	}
	

	
	@RequestMapping(value="equipe")
	public String equipe(Model model) {
		
		return"site/equipe/equipe";
	}
	
	@RequestMapping(value="promotion")
	public String promotion(Model model) {
		
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
	public String banniere(Model model) {
		List<home> lh = hr.findAll();
		model.addAttribute("home", lh);
		return "home/ListeBaniere";
	}
	
	@RequestMapping(value="supBaniere")
	public String supprimerBaniere(Long id_home) {	
		hr.deleteById(id_home);
		return"redirect:Baniere";
	}
}
