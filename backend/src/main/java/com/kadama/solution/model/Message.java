package com.kadama.solution.model;



import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int numberOfReceiver;
    
    private String state;

    @OneToOne
    @JsonIgnore
    private Campaign campaign; // A message is associated with one campaign

 
    // Other fields and methods...
}
