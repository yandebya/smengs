package com.smengs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smengs.entite.Categorie;

public interface CategoriRepository extends JpaRepository<Categorie, Long> {

	  @Query("SELECT count(*) FROM Categorie c")
	 Long nombreCategorie();
	  @Query("select d from Categorie d where d.domaineDemploi.id_domaine=:x")
	  public List<Categorie> listeCatParDomaine(@Param("x")Long id_domaine);
	  
	  @Query("SELECT count(*) FROM Categorie cd where cd.domaineDemploi.id_domaine=:y")
		 Long nombreCategorieParDomaine(@Param("y")Long id_domaine);
	  
	 /* @Query("select c from Categorie c where c.utilisateur.categorie.id_cat=:z")
	  public List<Categorie> OffreParCat(@Param("z")Long id_cat);*/
	
}
