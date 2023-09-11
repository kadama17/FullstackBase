package com.kadama.solution.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Liste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String list_name;

    @OneToMany(mappedBy = "list")
    @JsonIgnore
    private List<Contacts> contactsList;

    
    @ManyToOne
    @JsonIgnore
    private Campaign campaign; // This property corresponds to the mappedBy value in Campaign

    @ManyToOne
    @JsonIgnore
    private Utilisateur utilisateur;
}
