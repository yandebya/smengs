package com.smengs.metier;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.dao.OffreRepository;
import com.smengs.dao.UtilisateurRepository;
import com.smengs.entite.Categorie;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Offre;
import com.smengs.entite.Utilisateur;

@Service
public class OffreMetierImp implements OffreMetier {
	
	@Autowired
	private OffreRepository offreRepository;
	@Autowired
	private CategoriRepository cr;
	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private DomaineRepository ddr;

	@Override
	public Offre creerOffre(Offre o, Long id_cat,Long id_domaine,HttpServletRequest hsr) {
		
		Utilisateur ut = getLogedUserUc(hsr);
		o.setUtilisateur(ut);
		
		Categorie c = cr.getOne(id_cat);
		DomaineDemploi dd = ddr.getOne(id_domaine);
		o.setDomaineDemploi(dd);
	
		o.setDate_off(new Date());
		o.setCategorie(c);
		
		return offreRepository.save(o);
	}

	@Override
	public List<Offre> listeOffre() {
		return offreRepository.findAll();
	}
	@Override
	public Utilisateur getLogedUserUc(HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=sc.getAuthentication().getName();
	    return ur.sessionEnCours(username);
		
	}
	
	
	
	
	
	
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void SendEmail(String to,  String sujet,String body,HttpServletRequest hsr) {
		
		Utilisateur ut = getLogedUserUc(hsr);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(ut.getEmail_utilisateur());
		message.setTo(to);
		message.setSubject(sujet);
		message.setText(body);

		
		mailSender.send(message);
		System.out.print("envoi d'email");
	}
}
