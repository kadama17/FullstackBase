package com.kadama.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kadama.solution.model.Appareils;
import com.kadama.solution.model.Utilisateur;
import java.util.List;


@Repository
public interface AppareilsRepository extends JpaRepository<Appareils, Integer> {
    // Ajoutez ici des méthodes de requête personnalisées si nécessaire
	List<Appareils> findByUtilisateur(Utilisateur utilisateur);


}
