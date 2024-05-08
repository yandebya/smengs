package com.smengs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smengs.entite.Categorie;
import com.smengs.entite.Offre;

public interface OffreRepository extends JpaRepository<Offre, Long>{
 @Query("SELECT count(*) FROM Offre c")
	 Long nombreOffre();
 
 @Query("select d from Offre d where d.categorie.id_cat=:x")
 public List<Offre> listeCatParOffre(@Param("x")Long id_cat);
 @Query("select do from Offre do where do.domaineDemploi.id_domaine=:z")
 public List<Offre> listeOffreDomaine(@Param("z")Long id_domaine);
 @Query("select o from Offre o where o.id_off=:y")
 public List<Offre> listeOffreDetail(@Param("y")Long id_off);
 
 /*@Query("select c from Offre c where c.domaineDemploi.id_domaine=:za and c.utilisateur.categorie.id_cat=:zb")
 public List<Offre> OffreParCat(@Param("za")Long id_domaine,@Param("zb")Long id_categorie);*/
}
