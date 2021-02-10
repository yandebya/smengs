package com.gessco.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gessco.dao.ContactRepository;
import com.gessco.entite.contact;

@Service
public class ContactMetierImp implements ContactMetier{
	
	@Autowired
	private ContactRepository cr;

	@Override
	public contact creerContact(contact c) {
		
		return cr.save(c);
	}

	@Override
	public List<contact> listeContact() {
		
		return cr.findAll();
	}

}
