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
import com.smengs.entite.Apropos1;
import com.smengs.entite.Utilisateur;


@Controller
@RequestMapping(value = "apropos1")
public class Apropos1Controller {
	
	@Autowired
	private Apropos1Repository a1r;
	@Autowired
	private UtilisateurController uc;
	
	@Value("${imageUtilisateur}")
	private String photoApp;
	@RequestMapping(value = "creationApropos1")
	public String creationApp1(Apropos1 a1, @RequestParam(name = "picture") MultipartFile file) throws IOException {
		if (!(file.isEmpty())) {
			a1.setImage_propos1(file.getOriginalFilename());
		}
		a1r.save(a1);
		if (!(file.isEmpty())) {
			a1.setImage_propos1(file.getOriginalFilename());
			file.transferTo(new File(photoApp + a1.getImage_propos1()));
		}
		return "redirect:creerApropos1";
		
	}
	
	
	
	  	@RequestMapping(value = "/getImage_propos1", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImage_propos1(String image_propos1) throws IOException {
		File f = new File(photoApp + image_propos1);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	 
	
	
	@RequestMapping(value = "creerApropos1")
	public String creerApropos1(Model model, HttpServletRequest hsr) {
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Apropos1 a1 = new Apropos1();
		model.addAttribute("apropos1", a1);
		
		return "apropos/creerApropos1";
		
	}
	
	@RequestMapping(value = "listeApropos1")
	public String ListeApropos1(Model model,HttpServletRequest hsr) {
		List<Apropos1> la1 = a1r.findAll();
		model.addAttribute("listeApropos1", la1);
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		return "apropos/listeApropos1";
	}
	
	@RequestMapping(value = "listeApropos1Site")
	public String ListeApropos1Site(Model model,HttpServletRequest hsr) {
		List<Apropos1> la1 = a1r.dernierPosteApropos1();
		model.addAttribute("listeApropos1", la1);
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		return "site/apropos/apropos";
	}
	
	@RequestMapping(value = "editerApp2")
	public String editerPartenaire(Model model,Long id_propos1,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Apropos1 a = a1r.getOne(id_propos1);
		model.addAttribute("apropos1", a);
		
		return "apropos/creerApropos1";
	}

@RequestMapping(value = "supprimerApp2")
public String supprimerPartenaire(Model model,Long id_propos1,HttpServletRequest hsr) {
	Utilisateur uu = uc.getLogedUserUc(hsr);
	model.addAttribute("utilisateur", uu);
	
	a1r.deleteById(id_propos1);
	
	return "redirect:listeApropos1";
}
	
}
