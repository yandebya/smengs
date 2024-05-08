package com.smengs.metier;

import com.smengs.entite.DomaineDemploi;

public interface DomaineMetier {
	

	   public void supprimerDomaine(Long id_domaine);
	   public DomaineDemploi editerDomoaine(Long id_domaine);

}
