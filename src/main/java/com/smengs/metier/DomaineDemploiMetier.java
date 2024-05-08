package com.smengs.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.entite.Categorie;
import com.smengs.entite.DomaineDemploi;

@Service
public class DomaineDemploiMetier implements DomaineMetier {

	
	@Autowired
	private DomaineRepository dr;
	@Override
	public void supprimerDomaine(Long id_domaine) {
		
		dr.deleteById(id_domaine);
		
	}
	@Override
	public DomaineDemploi editerDomoaine(Long id_domaine) {
		 
		return dr.getOne(id_domaine);
	}



}
