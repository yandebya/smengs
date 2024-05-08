package com.smengs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smengs.entite.contact;

public interface ContactRepository extends JpaRepository<contact, Long>{

}
