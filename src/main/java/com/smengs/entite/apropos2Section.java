package com.smengs.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class apropos2Section implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_app2;
	private String titre_app2;
	private String libelle_app2;
	private String image_app2;
	public apropos2Section() {
		super();
		// TODO Auto-generated constructor stub
	}
	public apropos2Section(Long id_app2, String titre_app2, String libelle_app2, String image_app2) {
		super();
		this.id_app2 = id_app2;
		this.titre_app2 = titre_app2;
		this.libelle_app2 = libelle_app2;
		this.image_app2 = image_app2;
	}
	public Long getId_app2() {
		return id_app2;
	}
	public void setId_app2(Long id_app2) {
		this.id_app2 = id_app2;
	}
	public String getTitre_app2() {
		return titre_app2;
	}
	public void setTitre_app2(String titre_app2) {
		this.titre_app2 = titre_app2;
	}
	public String getLibelle_app2() {
		return libelle_app2;
	}
	public void setLibelle_app2(String libelle_app2) {
		this.libelle_app2 = libelle_app2;
	}
	public String getImage_app2() {
		return image_app2;
	}
	public void setImage_app2(String image_app2) {
		this.image_app2 = image_app2;
	}

	

}
