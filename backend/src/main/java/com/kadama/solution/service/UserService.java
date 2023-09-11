package com.kadama.solution.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kadama.solution.model.Utilisateur;
import com.kadama.solution.repository.UtilisateurRepository;

import org.springframework.security.core.userdetails.User;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    
    @Autowired
    private PasswordEncoder passwordEncoder; // Autowire PasswordEncoder
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }
        return new org.springframework.security.core.userdetails.User(
            utilisateur.getEmail(),
            utilisateur.getPassword(),
            new ArrayList<>()
        );
    }
    
    
    public boolean validateUser(String email, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null) {
            // Use your preferred method for password validation here, e.g., bcrypt comparison
            return passwordMatches(password, utilisateur.getPassword());
        }
        return false;
    }
    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        // Implement your password comparison logic here
        // For example, you can use the PasswordEncoder.matches() method
        // provided by Spring Security
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
    public Utilisateur getUserByEmail(String email) {
        // Implement logic to retrieve a user by email from your database
        // For example:
    	
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

        // Set the password to null (or any desired value)
        if (utilisateur != null) {
            utilisateur.setPassword(null); // Set password to null
        }

        return utilisateur;
    }
    
}
