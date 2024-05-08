package com.smengs.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smengs.dao.AproposRepository;
import com.smengs.entite.apropos;

@Service
public class AproposMetierImp implements AproposMetier {
	
	@Autowired
	private AproposRepository ar;

	@Override
	public apropos creerApp(apropos a) {
		return ar.save(a);
	}

	@Override
	public List<apropos> listeApp() {
		return ar.findAll();
	}

}
