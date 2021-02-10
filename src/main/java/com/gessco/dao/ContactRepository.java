package com.gessco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gessco.entite.contact;

public interface ContactRepository extends JpaRepository<contact, Long>{

}
