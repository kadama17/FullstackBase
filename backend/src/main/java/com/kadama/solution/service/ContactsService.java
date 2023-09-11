package com.kadama.solution.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kadama.solution.model.Contacts;
import com.kadama.solution.repository.ContactsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;
    public List<Contacts> getAllContacts() {
        return contactsRepository.findAll();
    }
    public Contacts getContactById(int id) {
        Optional<Contacts> optionalContact = contactsRepository.findById(id);
        return optionalContact.orElse(null);
    }
    public Contacts createContact(Contacts contact) {
        // Implement your logic for creating a new Contacts resource here
        // You can use the ContactsRepository to save the new resource
        // For example, you can save the new Contacts instance like this:
        return contactsRepository.save(contact);
    }
    
    public void updateContact(int id, Contacts updatedContact) {
        // Implement your logic for updating an existing contact here
        // You can use the ContactsRepository to save the updated contact
        Optional<Contacts> optionalContact = contactsRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contacts existingContact = optionalContact.get();
            // Update the fields of existingContact with the data from updatedContact
            existingContact.setNom(updatedContact.getNom());
            existingContact.setNumero(updatedContact.getNumero());
            // Save the updated contact
            contactsRepository.save(existingContact);
        }
    }

    public boolean deleteContact(int id) {
        // Implement your logic for deleting a contact by ID here
        // You can use the ContactsRepository to delete the contact
        try {
            contactsRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            // Contact with the given ID doesn't exist
            return false;
        }
    }

    // Ajoutez ici des méthodes de service pour la gestion des contacts
}
