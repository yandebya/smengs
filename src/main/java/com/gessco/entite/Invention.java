package com.gessco.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Invention implements Serializable{
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
private Long id;
private String titre;
private String libelle;
private String image;

	public Invention() {
		super();

	}

	public Invention(Long id, String titre, String libelle) {
		super();
		this.id = id;
		this.titre = titre;
		this.libelle = libelle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
