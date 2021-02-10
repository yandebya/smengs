package com.gessco.dao;

import com.gessco.entite.home;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface HomeRepository extends JpaRepository<home, Long>{

	
	
	@Query("select r from home r where r.id_home=(select MAX(r.id_home)from home r)")
	List<home> dernierPosteHome();
}
