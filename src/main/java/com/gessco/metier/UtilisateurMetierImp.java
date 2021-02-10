package com.gessco.metier;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gessco.dao.RoleRepository;
import com.gessco.dao.UtilisateurRepository;
import com.gessco.entite.Utilisateur;
import com.gessco.entite.Role;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UtilisateurMetierImp implements UtilisateurMertier {

	@Autowired
	private UtilisateurRepository ur;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Utilisateur saveUtilsateur(Utilisateur p, String password) {
		Role r = roleRepository.findByRole("SUPERADMIN");
		p.setRoles(new HashSet<Role> (Arrays.asList(r)));
		p.setPassword(new BCryptPasswordEncoder().encode(p.getPassword()));
		/*
		 * 
		rec.setRoles(new HashSet<Role> (Arrays.asList(r)));
		rec.setPassword(new BCryptPasswordEncoder().encode(rec.getPassword()));
		rec.setDate_inscription(new Date());*/
		return ur.save(p);
	}

	@Override
	public Utilisateur saveUsanProfil(Utilisateur p) {
		
		return ur.save(p);
	}

	@Override
	public Utilisateur SessionU(String username) {
		
		return ur.sessionEnCours(username);
	}

	@Override
	public Utilisateur GetUser(Long id_utilisateur) {
		
		return ur.getOne(id_utilisateur);
	}

	@Override
	public List<Utilisateur> listeUtilisateur() {
		
		return ur.findAll();
	}

	/*@Override
	public void activation(Long id_utilisateur, HttpServletRequest hsr) {
		
		
	}*/
}
