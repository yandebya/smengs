package com.smengs.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partenaire implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_part;
	private String photo_part;
	private String libelle_part;
	/**
	 * 
	 */
	public Partenaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id_part
	 * @param photo_part
	 * @param libelle_part
	 */
	public Partenaire(Long id_part, String photo_part, String libelle_part) {
		super();
		this.id_part = id_part;
		this.photo_part = photo_part;
		this.libelle_part = libelle_part;
	}
	public Long getId_part() {
		return id_part;
	}
	public void setId_part(Long id_part) {
		this.id_part = id_part;
	}
	public String getPhoto_part() {
		return photo_part;
	}
	public void setPhoto_part(String photo_part) {
		this.photo_part = photo_part;
	}
	public String getLibelle_part() {
		return libelle_part;
	}
	public void setLibelle_part(String libelle_part) {
		this.libelle_part = libelle_part;
	}
	
	

}
