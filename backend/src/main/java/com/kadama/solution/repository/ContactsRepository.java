package com.kadama.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kadama.solution.model.Contacts;


@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
    // Ajoutez ici des méthodes de requête personnalisées si nécessaire
}
