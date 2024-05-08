package com.smengs.entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Offre implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_off;
	private String libelle_off;
	private String photo_off;
	private String description_off;
	private Date date_off;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;
	
	@ManyToOne
	 @JoinColumn(name = "categorie_id")
	private Categorie categorie;
	
	@ManyToOne
	 @JoinColumn(name = "domaine_id")
	private DomaineDemploi domaineDemploi;
	

	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}

	













	/**
	 * @param id_off
	 * @param libelle_off
	 * @param photo_off
	 * @param description_off
	 * @param date_off
	 * @param utilisateur
	 * @param categorie
	 * @param domaineDemploi
	 */
	public Offre(Long id_off, String libelle_off, String photo_off, String description_off, Date date_off,
			Utilisateur utilisateur, Categorie categorie, DomaineDemploi domaineDemploi) {
		super();
		this.id_off = id_off;
		this.libelle_off = libelle_off;
		this.photo_off = photo_off;
		this.description_off = description_off;
		this.date_off = date_off;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.domaineDemploi = domaineDemploi;
	}















	public Long getId_off() {
		return id_off;
	}

	public void setId_off(Long id_off) {
		this.id_off = id_off;
	}

	public String getLibelle_off() {
		return libelle_off;
	}

	public void setLibelle_off(String libelle_off) {
		this.libelle_off = libelle_off;
	}

	public String getDescription_off() {
		return description_off;
	}

	public void setDescription_off(String description_off) {
		this.description_off = description_off;
	}

	public Date getDate_off() {
		return date_off;
	}

	public void setDate_off(Date date_off) {
		this.date_off = date_off;
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

	public DomaineDemploi getDomaineDemploi() {
		return domaineDemploi;
	}

	public void setDomaineDemploi(DomaineDemploi domaineDemploi) {
		this.domaineDemploi = domaineDemploi;
	}







	public String getPhoto_off() {
		return photo_off;
	}







	public void setPhoto_off(String photo_off) {
		this.photo_off = photo_off;
	}
	
	
}
