package com.smengs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smengs.entite.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

	 @Query("select s from Utilisateur s where s.username=:x")
		public Utilisateur sessionEnCours(@Param("x") String username);
}
