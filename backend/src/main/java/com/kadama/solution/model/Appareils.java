package com.kadama.solution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Appareils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nom;
    private String status;

    @ManyToOne
    @JsonIgnore
    private Utilisateur utilisateur;

    @ManyToOne
    @JsonIgnore
    private Campaign campaign;
    
    @Override
    public String toString() {
        return "Appareils{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
