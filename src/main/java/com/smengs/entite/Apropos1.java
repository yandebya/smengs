package com.smengs.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Apropos1 implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_propos1;
	private String libelle_propos1;
	
	private String image_propos1;

	/**
	 * 
	 */
	public Apropos1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id_propos1
	 * @param libelle_propos1
	 * @param image_propos1
	 */
	public Apropos1(Long id_propos1, String libelle_propos1, String image_propos1) {
		super();
		this.id_propos1 = id_propos1;
		this.libelle_propos1 = libelle_propos1;
		this.image_propos1 = image_propos1;
	}

	public Long getId_propos1() {
		return id_propos1;
	}

	public void setId_propos1(Long id_propos1) {
		this.id_propos1 = id_propos1;
	}

	public String getLibelle_propos1() {
		return libelle_propos1;
	}

	public void setLibelle_propos1(String libelle_propos1) {
		this.libelle_propos1 = libelle_propos1;
	}

	public String getImage_propos1() {
		return image_propos1;
	}

	public void setImage_propos1(String image_propos1) {
		this.image_propos1 = image_propos1;
	}
	
	
	
	
}

