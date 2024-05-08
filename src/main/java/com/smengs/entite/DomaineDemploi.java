package com.smengs.entite;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DomaineDemploi implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_domaine;
	private String libelle_domaine;
	private String description_domaine;

	@OneToMany(mappedBy="domaineDemploi", cascade=CascadeType.REMOVE)
	 private Collection<Categorie> Cat;
	
	@OneToMany(mappedBy="domaineDemploi", cascade=CascadeType.REMOVE)
	 private Collection<Offre> off;
	
	public DomaineDemploi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DomaineDemploi(Long id_domaine, String libelle_domaine, String description_domaine) {
		super();
		this.id_domaine = id_domaine;
		this.libelle_domaine = libelle_domaine;
		this.description_domaine = description_domaine;
	}

	public Long getId_domaine() {
		return id_domaine;
	}

	public void setId_domaine(Long id_domaine) {
		this.id_domaine = id_domaine;
	}

	public String getLibelle_domaine() {
		return libelle_domaine;
	}

	public void setLibelle_domaine(String libelle_domaine) {
		this.libelle_domaine = libelle_domaine;
	}

	public String getDescription_domaine() {
		return description_domaine;
	}

	public void setDescription_domaine(String description_domaine) {
		this.description_domaine = description_domaine;
	}

	public Collection<Categorie> getCat() {
		return Cat;
	}

	public void setCat(Collection<Categorie> cat) {
		Cat = cat;
	}

	public Collection<Offre> getOff() {
		return off;
	}

	public void setOff(Collection<Offre> off) {
		this.off = off;
	}
	
	
}
