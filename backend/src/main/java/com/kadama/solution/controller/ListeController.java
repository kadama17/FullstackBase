package com.kadama.solution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kadama.solution.model.Contacts;
import com.kadama.solution.model.Liste;
import com.kadama.solution.service.ListeService;

import java.util.List;

@RestController
@RequestMapping("/api/liste")
public class ListeController {

    @Autowired
    private ListeService listeService;

    // Create a new list
    @PostMapping("/create")
    public Liste createList(@RequestBody Liste liste) {
        return listeService.createListe(liste);
    }

    // Get a list by ID
    @GetMapping("/{id}")
    public Liste getListById(@PathVariable int id) {
        return listeService.getListById(id);
    }

    // Get all lists
    @GetMapping("/all")
    public List<Liste> getAllLists() {
        return listeService.getAllLists();
    }

    // Update a list
    @PutMapping("/{id}")
    public Liste updateList(@PathVariable int id, @RequestBody Liste updatedList) {
        return listeService.updateList(id, updatedList);
    }

    // Delete a list
    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable int id) {
        listeService.deleteList(id);
    }
    
    @GetMapping("/byUtilisateur/{utilisateurId}")
    public ResponseEntity<List<Liste>> getListeByUtilisateur(@PathVariable int utilisateurId) {
        List<Liste> listes = listeService.getListeByUtilisateur(utilisateurId);
        if (!listes.isEmpty()) {
            return ResponseEntity.ok(listes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<Contacts>> getContactsByListeId(@PathVariable int id) {
        Liste liste = listeService.getListById(id);
        if (liste != null) {
            List<Contacts> contacts = liste.getContactsList();
            return ResponseEntity.ok(contacts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
