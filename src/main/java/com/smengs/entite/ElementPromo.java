package com.smengs.entite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ElementPromo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_elt;
	private String Libelle_elt;
	public ElementPromo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ElementPromo(Long id_elt, String libelle_elt) {
		super();
		this.id_elt = id_elt;
		Libelle_elt = libelle_elt;
	}
	public Long getId_elt() {
		return id_elt;
	}
	public void setId_elt(Long id_elt) {
		this.id_elt = id_elt;
	}
	public String getLibelle_elt() {
		return Libelle_elt;
	}
	public void setLibelle_elt(String libelle_elt) {
		Libelle_elt = libelle_elt;
	}
	
	
}
