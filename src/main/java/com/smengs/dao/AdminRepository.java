package com.smengs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smengs.entite.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	  @Query("SELECT count(*) FROM Admin c")
	 Long nombreAdmin();
	
}
