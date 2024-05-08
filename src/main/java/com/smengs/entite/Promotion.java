package com.smengs.entite;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Promotion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_promo;
	private String prix_promo;
	private String libelle_promo;
	private String description_promo;
	
	@ManyToMany
	@JoinTable(name="promo_elmtpromo", joinColumns=@JoinColumn(name="promo"),inverseJoinColumns=@JoinColumn(name="elmtPromo"))
	private Set<ElementPromo> ElementPromo;

	public Promotion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Promotion(Long id_promo, String prix_promo, String libelle_promo,
			Set<com.smengs.entite.ElementPromo> elementPromo) {
		super();
		this.id_promo = id_promo;
		this.prix_promo = prix_promo;
		this.libelle_promo = libelle_promo;
		ElementPromo = elementPromo;
	}

	public Long getId_promo() {
		return id_promo;
	}

	public void setId_promo(Long id_promo) {
		this.id_promo = id_promo;
	}

	public String getPrix_promo() {
		return prix_promo;
	}

	public void setPrix_promo(String prix_promo) {
		this.prix_promo = prix_promo;
	}

	public String getLibelle_promo() {
		return libelle_promo;
	}

	public void setLibelle_promo(String libelle_promo) {
		this.libelle_promo = libelle_promo;
	}

	public Set<ElementPromo> getElementPromo() {
		return ElementPromo;
	}

	public void setElementPromo(Set<ElementPromo> elementPromo) {
		ElementPromo = elementPromo;
	}

	public String getDescription_promo() {
		return description_promo;
	}

	public void setDescription_promo(String description_promo) {
		this.description_promo = description_promo;
	}
	

}
