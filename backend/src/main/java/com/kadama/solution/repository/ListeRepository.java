package com.kadama.solution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kadama.solution.model.Liste;


@Repository
public interface ListeRepository extends JpaRepository<Liste, Integer> {
    List<Liste> findByUtilisateurId(int utilisateurId);
}
