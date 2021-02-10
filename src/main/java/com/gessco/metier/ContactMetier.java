package com.gessco.metier;

import java.util.List;

import com.gessco.entite.contact;

public interface ContactMetier {
	
	public contact creerContact(contact c);
	public List<contact> listeContact();

}
