package com.smengs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smengs.entite.Candidat;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {

	
	 @Query("SELECT count(*) FROM Candidat c")
	 Long nombreCandidat();
}
