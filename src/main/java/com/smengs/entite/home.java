package com.smengs.entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class home implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_home;
	private String titreHome;
	private String sousTitreHome;
	private String image_home;
	private Date date_home;
	public home() {
		super();
		// TODO Auto-generated constructor stub
	}
	public home(Long id_home, String titreHome, String sousTitreHome) {
		super();
		this.id_home = id_home;
		this.titreHome = titreHome;
		this.sousTitreHome = sousTitreHome;
	}
	public Long getId_home() {
		return id_home;
	}
	public void setId_home(Long id_home) {
		this.id_home = id_home;
	}
	public String getTitreHome() {
		return titreHome;
	}
	public void setTitreHome(String titreHome) {
		this.titreHome = titreHome;
	}
	public String getSousTitreHome() {
		return sousTitreHome;
	}
	public void setSousTitreHome(String sousTitreHome) {
		this.sousTitreHome = sousTitreHome;
	}
	public String getImage_home() {
		return image_home;
	}
	public void setImage_home(String image_home) {
		this.image_home = image_home;
	}
	public Date getDate_home() {
		return date_home;
	}
	public void setDate_home(Date date_home) {
		this.date_home = date_home;
	}
	
	

}
