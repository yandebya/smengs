package com.gessco.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gessco.entite.Service;



public interface ServiceRepository extends JpaRepository<Service, Long>{

	
	
	@Query("select e from Service e where e.id_ser=:xa")
	List<Service> ServiceParId(@Param("xa") Long id_ser);
}
