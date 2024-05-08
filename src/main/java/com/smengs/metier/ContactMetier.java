package com.smengs.metier;

import java.util.List;

import com.smengs.entite.contact;

public interface ContactMetier {
	
	public contact creerContact(contact c);
	public List<contact> listeContact();

}
