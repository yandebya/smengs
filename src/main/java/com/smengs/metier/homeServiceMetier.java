package com.smengs.metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.smengs.entite.Service;
import com.smengs.entite.Utilisateur;

public interface homeServiceMetier {

	public Service creerService(Service a , Long id_cat, HttpServletRequest hsr);
	public List<Service> listeService();
	public Utilisateur getLogedUserUc(HttpServletRequest hsr);
}
