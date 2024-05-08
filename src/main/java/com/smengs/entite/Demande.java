package com.smengs.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Demande implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_dem;
	private String libelle_dem;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;
	
	@ManyToOne
	 @JoinColumn(name = "categorie_id")
	private Categorie categorie;

	public Demande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demande(Long id_dem, String libelle_dem, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.id_dem = id_dem;
		this.libelle_dem = libelle_dem;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}

	public Long getId_dem() {
		return id_dem;
	}

	public void setId_dem(Long id_dem) {
		this.id_dem = id_dem;
	}

	public String getLibelle_dem() {
		return libelle_dem;
	}

	public void setLibelle_dem(String libelle_dem) {
		this.libelle_dem = libelle_dem;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
}
