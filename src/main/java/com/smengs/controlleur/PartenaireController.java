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

import com.smengs.dao.PartenaireRepository;
import com.smengs.entite.Partenaire;
import com.smengs.entite.Utilisateur;

@Controller
public class PartenaireController {
	
	@Autowired
	private UtilisateurController uc;
	@Autowired
	private PartenaireRepository pr;

	@Value("${imageUtilisateur}")
	private String photoSer;
	@RequestMapping(value = "creationPartenaire")
	public String creationPartenaire(Partenaire p, @RequestParam(name = "picture") MultipartFile file) throws IOException{
		
			if (!(file.isEmpty())) {
			p.setPhoto_part(file.getOriginalFilename());
		}
		
		pr.save(p);
		
		if (!(file.isEmpty())) {
			p.setPhoto_part(file.getOriginalFilename());
			file.transferTo(new File(photoSer + p.getPhoto_part()));
		}
		
		return "redirect:creerPartenaire";
	}
	
	@RequestMapping(value = "/getPhoto_part", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto_part(String photo_part) throws IOException {
		File f = new File(photoSer + photo_part);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value = "creerPartenaire")
	public String creerPar(Model model,HttpServletRequest hsr) {
		Partenaire p = new Partenaire();
		model.addAttribute("partenaire", p);
		
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		return "/partenaire/creerPartenaire";
	}
	
	

	@RequestMapping(value = "listePartenaire")
	public String listePar(Model model,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		List<Partenaire> p = pr.findAll();
		model.addAttribute("listePartenaire", p);
		return "/partenaire/listePartenaire";
	}
	@RequestMapping(value = "editerPartenaire")
	public String editerPartenaire(Model model,Long id_part,HttpServletRequest hsr) {
		Utilisateur uu = uc.getLogedUserUc(hsr);
		model.addAttribute("utilisateur", uu);
		
		Partenaire p = pr.getOne(id_part);
		model.addAttribute("partenaire", p);
		
		return "/partenaire/creerPartenaire";
	}

@RequestMapping(value = "supprimerPartenaire")
public String supprimerPartenaire(Model model,Long id_part,HttpServletRequest hsr) {
	Utilisateur uu = uc.getLogedUserUc(hsr);
	model.addAttribute("utilisateur", uu);
	
	pr.deleteById(id_part);
	
	return "redirect:listePartenaire";
}

}
