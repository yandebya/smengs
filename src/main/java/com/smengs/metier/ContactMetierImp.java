package com.smengs.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smengs.dao.ContactRepository;
import com.smengs.entite.contact;

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
