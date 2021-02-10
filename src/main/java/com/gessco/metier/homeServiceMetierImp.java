package com.gessco.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gessco.dao.HomeRepository;

@Service
public class homeServiceMetierImp implements homeServiceMetier{

	
	@Autowired
	private HomeRepository hr;
}
