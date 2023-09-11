package com.kadama.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kadama.solution.model.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    // Ajoutez ici des méthodes de requête personnalisées si nécessaire
}
