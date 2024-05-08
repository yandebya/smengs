package com.smengs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smengs.entite.DomaineDemploi;

public interface DomaineRepository extends JpaRepository<DomaineDemploi, Long> {

}
