package com.smengs.metier;

import java.util.List;

import com.smengs.entite.Candidat;

public interface CandidatMetier {
public Candidat creerCandidat(Candidat c, String password);
public List<Candidat> listeCandidat();
}
