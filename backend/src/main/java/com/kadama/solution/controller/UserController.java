package com.kadama.solution.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.kadama.solution.model.Utilisateur;
import com.kadama.solution.repository.UtilisateurRepository;
import com.kadama.solution.service.UserService;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Autowire PasswordEncoder

    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscriptionSubmit(@RequestBody Utilisateur utilisateur) {
        String rawPassword = utilisateur.getPassword();
        if (rawPassword == null) {
            // Gérer le cas où le mot de passe est null, par exemple, renvoyer une réponse d'erreur.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        utilisateur.setPassword(passwordEncoder.encode(rawPassword)); // Utilisez passwordEncoder
        utilisateurRepository.save(utilisateur);

        utilisateur.setPassword(null);
        // Renvoyer l'utilisateur créé avec un code 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateur);
    }


    @PostMapping("/connexion")
    public ResponseEntity<Utilisateur> loginUser(@RequestBody Utilisateur utilisateur) {
        String email = utilisateur.getEmail();
        String password = utilisateur.getPassword();
        
        // Validate the login credentials here (e.g., check against your database)
        boolean loginSuccessful = userService.validateUser(email, password);

        if (loginSuccessful) {
            Utilisateur loggedInUser = userService.getUserByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Unauthorized (failure)
        }
    }

    
    @GetMapping("/user/{id}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(utilisateur.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/user")
    public ResponseEntity<Utilisateur> getUserByEmail(@RequestParam String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null) {
            return ResponseEntity.status(HttpStatus.OK).body(utilisateur);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
