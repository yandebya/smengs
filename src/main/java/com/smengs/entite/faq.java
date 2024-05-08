package com.smengs.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class faq implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_f;
	private String Titre_f;
	private String Libelle_f;
	public faq() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public faq(Long id_f, String titre_f, String libelle_f) {
		super();
		this.id_f = id_f;
		Titre_f = titre_f;
		Libelle_f = libelle_f;
	}


	public Long getId_f() {
		return id_f;
	}
	public void setId_f(Long id_f) {
		this.id_f = id_f;
	}
	public String getTitre_f() {
		return Titre_f;
	}
	public void setTitre_f(String titre_f) {
		Titre_f = titre_f;
	}
	public String getLibelle_f() {
		return Libelle_f;
	}
	public void setLibelle_f(String libelle_f) {
		Libelle_f = libelle_f;
	}
	
	
	

}
