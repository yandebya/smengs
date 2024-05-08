package com.smengs.entite;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Categorie implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_cat;
	private String libelle_cat;
	private String description_cat;

	/*@ManyToOne
	 @JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;*/
	
	@ManyToOne
	 @JoinColumn(name = "DomaineDemploi_id")
	private DomaineDemploi domaineDemploi;
	
	@OneToMany(mappedBy="categorie", cascade=CascadeType.REMOVE)
	 private Collection<Offre> off;
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorie(Long id_cat, String libelle_cat, String description_cat, DomaineDemploi domaineDemploi) {
		super();
		this.id_cat = id_cat;
		this.libelle_cat = libelle_cat;
		this.description_cat = description_cat;
		this.domaineDemploi = domaineDemploi;
	}
	public Long getId_cat() {
		return id_cat;
	}
	public void setId_cat(Long id_cat) {
		this.id_cat = id_cat;
	}
	public String getLibelle_cat() {
		return libelle_cat;
	}
	public void setLibelle_cat(String libelle_cat) {
		this.libelle_cat = libelle_cat;
	}
	public String getDescription_cat() {
		return description_cat;
	}
	public void setDescription_cat(String description_cat) {
		this.description_cat = description_cat;
	}
	public DomaineDemploi getDomaineDemploi() {
		return domaineDemploi;
	}
	public void setDomaineDemploi(DomaineDemploi domaineDemploi) {
		this.domaineDemploi = domaineDemploi;
	}
	public Collection<Offre> getOff() {
		return off;
	}
	public void setOff(Collection<Offre> off) {
		this.off = off;
	}

}
