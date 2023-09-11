package com.kadama.solution.model;



import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Data
@Entity
public class Campaign {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nom;
    
    @OneToOne(mappedBy = "campaign")
    @JsonIgnore
    private Message message; // A campaign has only one message

    @ManyToOne
    @JsonIgnore // Ignorer la sérialisation JSON de cette relation
    private Appareils appareil; // A campaign is associated with one apparatus

    @ManyToOne
    @JsonIgnore
    private Utilisateur utilisateur; // Many campaigns can belong to one utilisateur

    
    @OneToMany(mappedBy = "campaign")
    @JsonIgnore
    private List<Liste> listes;

 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + id;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        // Exclure les entités liées de la génération de hachage
        return result;
    }
    
    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
