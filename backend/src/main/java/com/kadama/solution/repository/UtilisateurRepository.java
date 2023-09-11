package com.kadama.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kadama.solution.model.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
    
}