package com.gessco.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gessco.entite.Invention;
import com.gessco.entite.home;

public interface InventionRepository extends JpaRepository<Invention, Long>{

	@Query("select r from Invention r where r.id=(select MAX(r.id)from Invention r)")
	List<Invention> dernierPosteinvention();
}
