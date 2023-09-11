package com.kadama.solution.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadama.solution.model.Appareils;
import com.kadama.solution.model.Utilisateur;
import com.kadama.solution.repository.AppareilsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppareilsService {

    @Autowired
    private AppareilsRepository appareilsRepository;
    
    public List<Appareils> getAllAppareils() {
        return appareilsRepository.findAll();
    }

    public Appareils getAppareilById(int id) {
        Optional<Appareils> optionalAppareil = appareilsRepository.findById(id);
        return optionalAppareil.orElse(null);
    }
    
    public Appareils createAppareil(Appareils appareil) {
        // Implement your logic for creating a new Appareils resource here
        // You can use the AppareilsRepository to save the new resource
        // For example, you can save the new Appareils instance like this:
        return appareilsRepository.save(appareil);
    }
    
    public List<Appareils> getAppareilsByUtilisateur(Utilisateur utilisateur) {
        return appareilsRepository.findByUtilisateur(utilisateur);
    }
    
    public Appareils updateAppareil(int id, Appareils updatedAppareil) {
        Optional<Appareils> optionalAppareil = appareilsRepository.findById(id);
        if (optionalAppareil.isPresent()) {
            Appareils appareil = optionalAppareil.get();
            // Update the properties of the appareil object with values from updatedAppareil
            appareil.setNom(updatedAppareil.getNom());
            appareil.setStatus(updatedAppareil.getStatus());
            // Set other properties as needed
            return appareilsRepository.save(appareil);
        }
        return null; // Return null if the appareil is not found
    }
    public void deleteAppareil(int id) {
        appareilsRepository.deleteById(id);
    }


    // Ajoutez ici des méthodes de service pour la gestion des appareils
}
