package com.smengs.entite;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Candidat")
public class Candidat extends Utilisateur implements Serializable{

	
 
	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param id_utilisateur
	 * @param nom_utiliseur
	 * @param prenom_utilisateur
	 * @param email_utilisateur
	 * @param motDePasse
	 * @param username
	 * @param password
	 * @param roles
	 */
	public Candidat(Long id_utilisateur, String nom_utiliseur, String prenom_utilisateur, String email_utilisateur,
			String motDePasse, String username, String password, Set<Role> roles) {
		super(id_utilisateur, nom_utiliseur, prenom_utilisateur, email_utilisateur, motDePasse, username, password, roles);
		// TODO Auto-generated constructor stub
	}




	
	
}
