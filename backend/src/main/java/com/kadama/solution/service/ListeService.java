package com.kadama.solution.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadama.solution.model.Liste;
import com.kadama.solution.repository.ListeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ListeService {

    @Autowired
    private ListeRepository listeRepository;

    public Liste createListe(Liste liste) {
        return listeRepository.save(liste);
    }
    
    public Liste getListById(int id) {
        Optional<Liste> optionalListe = listeRepository.findById(id);
        return optionalListe.orElse(null);
    }

    // Define a method to get all lists
    public List<Liste> getAllLists() {
        return listeRepository.findAll();
    }

    // Define a method to update a list
    public Liste updateList(int id, Liste updatedList) {
        Optional<Liste> optionalListe = listeRepository.findById(id);
        if (optionalListe.isPresent()) {
            Liste liste = optionalListe.get();
            // Update the properties of the liste object with values from updatedList
            liste.setList_name(updatedList.getList_name());
            // Add more properties as needed
            return listeRepository.save(liste);
        }
        return null; // Return null if the list is not found
    }

    // Define a method to delete a list by its ID
    public void deleteList(int id) {
        listeRepository.deleteById(id);
    }
    public List<Liste> getListeByUtilisateur(int utilisateurId) {
        return listeRepository.findByUtilisateurId(utilisateurId);
    }


    // Ajoutez ici des méthodes de service pour la gestion des listes
}

