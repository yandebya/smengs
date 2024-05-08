package com.smengs.metier;

import com.smengs.entite.Demande;

public interface DemandeMetier {

	public Demande creerDemande(Demande demande, Long id_cat, Long id_utilisateur);
	
}
