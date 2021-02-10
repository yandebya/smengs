package com.gessco.entite;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.gessco.entite.Role;


@Entity
public class Utilisateur implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_utilisateur;
	private String Nom_utiliseur;
	private String prenom_utilisateur;
	private String email_utilisateur;
	private String motDePasse;
	
	private String username;
	private String password;
	@ManyToMany
	@JoinTable(name="USERS_ROLES",joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	
	
	

	public Utilisateur(Long id_utilisateur, String nom_utiliseur, String prenom_utilisateur, String email_utilisateur,
			String motDePasse, String username, String password, Set<com.gessco.entite.Role> roles) {
		super();
		this.id_utilisateur = id_utilisateur;
		Nom_utiliseur = nom_utiliseur;
		this.prenom_utilisateur = prenom_utilisateur;
		this.email_utilisateur = email_utilisateur;
		this.motDePasse = motDePasse;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(Long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getNom_utiliseur() {
		return Nom_utiliseur;
	}
	public void setNom_utiliseur(String nom_utiliseur) {
		Nom_utiliseur = nom_utiliseur;
	}
	public String getPrenom_utilisateur() {
		return prenom_utilisateur;
	}
	public void setPrenom_utilisateur(String prenom_utilisateur) {
		this.prenom_utilisateur = prenom_utilisateur;
	}
	public String getEmail_utilisateur() {
		return email_utilisateur;
	}
	public void setEmail_utilisateur(String email_utilisateur) {
		this.email_utilisateur = email_utilisateur;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
