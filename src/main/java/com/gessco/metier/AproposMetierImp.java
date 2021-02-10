package com.gessco.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gessco.dao.AproposRepository;
import com.gessco.entite.apropos;

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
