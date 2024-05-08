package com.smengs.metier;

import java.util.List;

import com.smengs.entite.Admin;

public interface AdminMetier {

	public Admin creerAdmin(Admin a, String password);
	public List<Admin> listeAdmin();
}
