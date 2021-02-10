package com.gessco.entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class question implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_q;
	private String email_q;
	private String nom_q;
	private String question_q;
	private Date date_q;
	public question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public question(long id_q, String email_q, String nom_q, String question_q) {
		super();
		this.id_q = id_q;
		this.email_q = email_q;
		this.nom_q = nom_q;
		this.question_q = question_q;
	}
	public long getId_q() {
		return id_q;
	}
	public void setId_q(long id_q) {
		this.id_q = id_q;
	}
	public String getEmail_q() {
		return email_q;
	}
	public void setEmail_q(String email_q) {
		this.email_q = email_q;
	}
	public String getNom_q() {
		return nom_q;
	}
	public void setNom_q(String nom_q) {
		this.nom_q = nom_q;
	}
	public String getQuestion_q() {
		return question_q;
	}
	public void setQuestion_q(String question_q) {
		this.question_q = question_q;
	}
	public Date getDate_q() {
		return date_q;
	}
	public void setDate_q(Date date_q) {
		this.date_q = date_q;
	}
	
	
	
}
