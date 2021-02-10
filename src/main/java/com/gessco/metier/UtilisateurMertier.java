package com.gessco.metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gessco.entite.Utilisateur;



public interface UtilisateurMertier {

	
	public Utilisateur saveUtilsateur(Utilisateur p,String motDePasse);
	public Utilisateur saveUsanProfil(Utilisateur p);
	public Utilisateur SessionU(String username);
	public Utilisateur GetUser(Long id_utilisateur);
	public List<Utilisateur> listeUtilisateur();
	/*public void activation(Long id_utilisateur,HttpServletRequest hsr);*/
}
