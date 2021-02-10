package com.gessco.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gessco.entite.question;

public interface QuestionRepository extends JpaRepository<question, Long> {

}
