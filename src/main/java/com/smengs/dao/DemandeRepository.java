package com.smengs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smengs.entite.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Long>{

}
