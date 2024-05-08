package com.smengs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smengs.entite.home;


public interface HomeRepository extends JpaRepository<home, Long>{

	
	
	@Query("select r from home r where r.id_home=(select MAX(r.id_home)from home r)")
	List<home> dernierPosteHome();
	@Query("select r from home r where r.id_home=(select MIN(r.id_home)from home r)")
	List<home> premierPosteHome();
}
