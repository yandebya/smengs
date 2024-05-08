package com.smengs.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smengs.entite.question;

public interface QuestionRepository extends JpaRepository<question, Long> {

	
	@Query("select p from question p where p.email_q like :x")
	public Page<question> questionParMotCle(@Param("x")String mc, Pageable pageable);
}
