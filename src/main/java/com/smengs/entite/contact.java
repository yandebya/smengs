package com.smengs.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class contact implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_cont;
	private String nom_cont;
	private String email_cont;
	private String phone_cont;
	private String message_cont;
	public contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public contact(Long id_cont, String nom_cont, String email_cont, String phone_cont, String message_cont) {
		super();
		this.id_cont = id_cont;
		this.nom_cont = nom_cont;
		this.email_cont = email_cont;
		this.phone_cont = phone_cont;
		this.message_cont = message_cont;
	}
	public Long getId_cont() {
		return id_cont;
	}
	public void setId_cont(Long id_cont) {
		this.id_cont = id_cont;
	}
	public String getNom_cont() {
		return nom_cont;
	}
	public void setNom_cont(String nom_cont) {
		this.nom_cont = nom_cont;
	}
	public String getEmail_cont() {
		return email_cont;
	}
	public void setEmail_cont(String email_cont) {
		this.email_cont = email_cont;
	}
	public String getPhone_cont() {
		return phone_cont;
	}
	public void setPhone_cont(String phone_cont) {
		this.phone_cont = phone_cont;
	}
	public String getMessage_cont() {
		return message_cont;
	}
	public void setMessage_cont(String message_cont) {
		this.message_cont = message_cont;
	}
	
	
	
	
	

}
