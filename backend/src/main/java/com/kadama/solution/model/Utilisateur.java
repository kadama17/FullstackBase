package com.kadama.solution.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Utilisateur {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nom;
	    private String email;
	    private String password;

	    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	    @JsonIgnore
	    private Set<Appareils> appareils = new HashSet<>();

	    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	    @JsonIgnore
	    private Set<Campaign> campaigns = new HashSet<>();
	    
	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = super.hashCode();
	        result = prime * result + id.intValue();
	        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
	        // Exclure les entités liées de la génération de hachage
	        return result;
	    }
	    
	    @Override
	    public String toString() {
	        return "Utilisateur{" +
	                "id=" + id +
	                ", nom='" + nom + '\'' +
	                '}';
	    }
}