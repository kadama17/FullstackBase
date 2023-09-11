package com.kadama.solution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kadama.solution.model.Campaign;


@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    // Ajoutez ici des méthodes de requête personnalisées si nécessaire
	
    List<Campaign> findByUtilisateurId(Long userId);

}
