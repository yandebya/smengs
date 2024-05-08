package com.smengs.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DemandeRepository;
import com.smengs.dao.UtilisateurRepository;
import com.smengs.entite.Categorie;
import com.smengs.entite.Demande;
import com.smengs.entite.Utilisateur;

@Service
public class DemandeMetierImp implements DemandeMetier{

	@Autowired
	private DemandeRepository dmdR;
	@Autowired
	private UtilisateurRepository utlR;
	@Autowired
	private CategoriRepository catR;
	
	@Override
	public Demande creerDemande(Demande demande, Long id_cat, Long utilisateur_id) {
		Utilisateur u = utlR.getOne(utilisateur_id);
		Categorie cat = catR.getOne(id_cat);
		demande.setCategorie(cat);
		demande.setUtilisateur(u);
		return dmdR.save(demande);
	}

}
