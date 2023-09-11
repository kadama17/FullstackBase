package com.kadama.solution.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kadama.solution.model.Appareils;
import com.kadama.solution.model.Utilisateur;
import com.kadama.solution.service.AppareilsService;

import java.util.List;

@RestController
@RequestMapping("/api/appareils")
public class AppareilsController {

@Autowired
private AppareilsService appareilsService;

@PostMapping("/create")
public ResponseEntity<Appareils> createAppareil(@RequestBody Appareils appareil) {
	System.out.println("kdjkdjkdj");
    Appareils createdAppareil = appareilsService.createAppareil(appareil);
    if (createdAppareil != null) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppareil);
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
@GetMapping("/{id}")
public ResponseEntity<Appareils> getAppareilById(@PathVariable int id) {
    Appareils appareil = appareilsService.getAppareilById(id);
    if (appareil != null) {
        return ResponseEntity.ok(appareil);
    } else {
        return ResponseEntity.notFound().build();
    }
}

@GetMapping("/all")
public ResponseEntity<List<Appareils>> getAllAppareils() {
    List<Appareils> appareils = appareilsService.getAllAppareils();
    return ResponseEntity.ok(appareils);
}

@GetMapping("/byUtilisateur/{utilisateurId}")	
public ResponseEntity<List<Appareils>> getAppareilsByUtilisateur(@PathVariable Long utilisateurId) {
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setId(utilisateurId);
    List<Appareils> appareils = appareilsService.getAppareilsByUtilisateur(utilisateur);
    return ResponseEntity.ok(appareils);
}

@PutMapping("/{id}")
public ResponseEntity<Appareils> updateAppareil(@PathVariable int id, @RequestBody Appareils updatedAppareil) {
    Appareils appareil = appareilsService.updateAppareil(id, updatedAppareil);
    if (appareil != null) {
        return ResponseEntity.ok(appareil);
    } else {
        return ResponseEntity.notFound().build();
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteAppareil(@PathVariable int id) {
    appareilsService.deleteAppareil(id);
    return ResponseEntity.noContent().build();
}

// Add endpoints for creating, updating, and deleting Appareils
}
