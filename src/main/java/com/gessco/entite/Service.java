package com.gessco.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_ser;
	private String titre_ser;
	private String libelle_ser;
	private String photiService;
	private String detail_ser;
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Service(Long id_ser, String titre_ser, String libelle_ser,String photiService) {
		super();
		this.id_ser = id_ser;
		this.titre_ser = titre_ser;
		this.libelle_ser = libelle_ser;
		this.photiService= photiService;
	}
	public Long getId_ser() {
		return id_ser;
	}
	public void setId_ser(Long id_ser) {
		this.id_ser = id_ser;
	}
	public String getTitre_ser() {
		return titre_ser;
	}
	public void setTitre_ser(String titre_ser) {
		this.titre_ser = titre_ser;
	}
	public String getLibelle_ser() {
		return libelle_ser;
	}
	public void setLibelle_ser(String libelle_ser) {
		this.libelle_ser = libelle_ser;
	}
	public String getPhotiService() {
		return photiService;
	}
	public void setPhotiService(String photiService) {
		this.photiService = photiService;
	}
	public String getDetail_ser() {
		return detail_ser;
	}
	public void setDetail_ser(String detail_ser) {
		this.detail_ser = detail_ser;
	}
	
	
}
