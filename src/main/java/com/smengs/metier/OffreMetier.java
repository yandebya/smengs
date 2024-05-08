package com.smengs.metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.smengs.entite.Offre;
import com.smengs.entite.Utilisateur;

public interface OffreMetier {
	
	public Offre creerOffre(Offre a , Long id_cat, Long id_domaine,HttpServletRequest hsr);
	public List<Offre> listeOffre();
	public Utilisateur getLogedUserUc(HttpServletRequest hsr);
	public void SendEmail(String to,  String sujet,  String body ,HttpServletRequest hsr);

}
