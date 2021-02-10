package com.gessco.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import com.gessco.dao.Apropos2SectionRepository;
import com.gessco.dao.AproposRepository;
import com.gessco.entite.apropos;
import com.gessco.entite.apropos2Section;
import com.gessco.metier.AproposMetier;

@Controller
@RequestMapping(value = "index")
public class Apropos {
	@Autowired
	private ServiceController sc;
	@Autowired
	private AproposMetier am;
	@Autowired
	private AproposRepository ar;
	@Autowired
	 private Apropos2SectionRepository a2r;
	
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
		return "redirect:listeAppSite";
	}
	
	
	@RequestMapping(value = "/getImage_app", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage_app(String image_app) throws IOException {
		File f = new File(photoApp + image_app);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value = "creationApropos")
	public String creationApp(Model model) {
	apropos a = new apropos();
	model.addAttribute("apropos", a);
	
	return "apropos/NewApropos";
	}
	
	@RequestMapping(value = "listeApp")
	public String ListeApp(Model model) {
	
		List<apropos> lapp = am.listeApp();
		model.addAttribute("listeApp", lapp);
	return "apropos/tableApropos";
}
	@RequestMapping(value = "listeAppSite")
	public String ListeAppSite(Model model) {
	
		List<apropos> lapp = am.listeApp();
		model.addAttribute("listeApp", lapp);
		/* liste deuxieme section */
		List<apropos2Section> lapp2 = a2r.findAll();
		model.addAttribute("listeApp2", lapp2);
		/*fin listre*/
		
		sc.listeSer(model);
	return "site/apropos/apropos";
}
	
	
	/* deuxieme section*/
	
	@RequestMapping(value = "listeApp2")
	public String liste2App(Model model) {
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
		return "redirect:listeAppSite";
	}
	
	@RequestMapping(value = "/getImage_app2", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage_app2(String image_app2) throws IOException {
		File f = new File(photoApp + image_app2);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	
	@RequestMapping(value = "creationApropos2")
	public String creationApp2(Model model) {
		apropos2Section a2 = new apropos2Section();
	model.addAttribute("apropos2", a2);
	
	return "apropos/NewApropos2";
	}
	
	@RequestMapping(value="supappropos2")
	public String supprimer(Long id_app2) {	
		a2r.deleteById(id_app2);
		return"redirect:listeApp2";
	}
	
	@RequestMapping(value="supEquipe")
	public String supprimerEquipe(Long id_app) {	
		ar.deleteById(id_app);
		return"redirect:tableApropos";
	}
}
