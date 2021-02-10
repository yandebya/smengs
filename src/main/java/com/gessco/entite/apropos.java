package com.gessco.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class apropos implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_app;
	private String titre_app;
	private String libelle_app;
	private String titre_bloc_app;
	private String image_app;
	private String infos_app;
	public Long getId_app() {
		return id_app;
	}
	public void setId_app(Long id_app) {
		this.id_app = id_app;
	}
	public String getTitre_app() {
		return titre_app;
	}
	public void setTitre_app(String titre_app) {
		this.titre_app = titre_app;
	}
	public String getLibelle_app() {
		return libelle_app;
	}
	public void setLibelle_app(String libelle_app) {
		this.libelle_app = libelle_app;
	}
	public String getTitre_bloc_app() {
		return titre_bloc_app;
	}
	public void setTitre_bloc_app(String titre_bloc_app) {
		this.titre_bloc_app = titre_bloc_app;
	}
	public String getImage_app() {
		return image_app;
	}
	public void setImage_app(String image_app) {
		this.image_app = image_app;
	}
	public String getInfos_app() {
		return infos_app;
	}
	public void setInfos_app(String infos_app) {
		this.infos_app = infos_app;
	}
	public apropos(Long id_app, String titre_app, String libelle_app, String titre_bloc_app, String image_app,
			String infos_app) {
		super();
		this.id_app = id_app;
		this.titre_app = titre_app;
		this.libelle_app = libelle_app;
		this.titre_bloc_app = titre_bloc_app;
		this.image_app = image_app;
		this.infos_app = infos_app;
	}
	public apropos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
