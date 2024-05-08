package com.smengs.entite;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SuperAdmin")
public class SuperAdmin extends Utilisateur {

	public SuperAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuperAdmin(Long id_utilisateur, String nom_utiliseur, String prenom_utilisateur, String email_utilisateur,
			String motDePasse, String username, String password, Set<Role> roles) {
		super(id_utilisateur, nom_utiliseur, prenom_utilisateur, email_utilisateur, motDePasse, username, password, roles);
		// TODO Auto-generated constructor stub
	}

	
	
}
