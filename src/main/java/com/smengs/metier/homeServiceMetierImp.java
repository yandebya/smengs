package com.smengs.metier;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.HomeRepository;
import com.smengs.dao.ServiceRepository;
import com.smengs.dao.UtilisateurRepository;
import com.smengs.entite.Categorie;
import com.smengs.entite.Utilisateur;

@Service
public class homeServiceMetierImp implements homeServiceMetier{
	@Autowired
	private CategoriRepository cr;
	@Autowired
	private UtilisateurRepository ur;
	
	@Autowired
	private HomeRepository hr;
	@Autowired
	private ServiceRepository sr;

	@Override
	public com.smengs.entite.Service creerService(com.smengs.entite.Service o, Long id_cat,HttpServletRequest hsr) {
	
		Utilisateur ut = getLogedUserUc(hsr);
		o.setUtilisateur(ut);
		
		Categorie c = cr.getOne(id_cat);
		Utilisateur u = ur.getOne(ut.getId_utilisateur());
		o.setCategorie(c);
		o.setUtilisateur(u);
		return sr.save(o);
	}

	@Override
	public List<com.smengs.entite.Service> listeService() {
		
		return sr.findAll();
	}
	
	

	@Override
	public Utilisateur getLogedUserUc(HttpServletRequest hsr) {
		HttpSession hs =hsr.getSession();
		SecurityContext sc = (SecurityContext)hs.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=sc.getAuthentication().getName();
	    return ur.sessionEnCours(username);
		
	}
}
