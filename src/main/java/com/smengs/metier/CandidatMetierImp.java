package com.smengs.metier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smengs.dao.CandidatRepository;
import com.smengs.dao.CategoriRepository;
import com.smengs.dao.DomaineRepository;
import com.smengs.dao.RoleRepository;
import com.smengs.entite.Candidat;
import com.smengs.entite.Categorie;
import com.smengs.entite.DomaineDemploi;
import com.smengs.entite.Role;
@Service
public class CandidatMetierImp implements CandidatMetier {

	@Autowired
	private CandidatRepository cr;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CategoriRepository categorieRepository;
	
	@Override
	public Candidat creerCandidat(Candidat c, String password) {
		
			
		Role r = roleRepository.findByRole("CANDIDAT");
		c.setRoles(new HashSet<Role> (Arrays.asList(r)));
		c.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()));
		return cr.save(c);
	}

	@Override
	public List<Candidat> listeCandidat() {
		
		return cr.findAll();
	}

}
