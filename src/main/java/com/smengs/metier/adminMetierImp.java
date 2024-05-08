package com.smengs.metier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smengs.dao.AdminRepository;
import com.smengs.dao.RoleRepository;
import com.smengs.entite.Admin;
import com.smengs.entite.Role;

@Service
public class adminMetierImp implements AdminMetier {

	@Autowired
	private AdminRepository ar;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Admin creerAdmin(Admin a, String password) {
		
		Role r = roleRepository.findByRole("ADMIN");
		a.setRoles(new HashSet<Role> (Arrays.asList(r)));
		a.setPassword(new BCryptPasswordEncoder().encode(a.getPassword()));
		
		return ar.save(a);
	}

	@Override
	public List<Admin> listeAdmin() {
		
		return ar.findAll();
	}

}
