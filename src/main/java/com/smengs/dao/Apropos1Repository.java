package com.smengs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smengs.entite.Apropos1;


public interface Apropos1Repository extends JpaRepository<Apropos1, Long> {

	 	@Query("select r from Apropos1 r where r.id_propos1=(select MAX(r.id_propos1)from Apropos1 r)")
	List<Apropos1> dernierPosteApropos1();
	 
	
	
}
