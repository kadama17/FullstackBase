package com.kadama.solution.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kadama.solution.model.Contacts;
import com.kadama.solution.service.ContactsService;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactsService contactsService;

    @GetMapping("/{id}")
    public ResponseEntity<Contacts> getContactById(@PathVariable int id) {
        Contacts contact = contactsService.getContactById(id);
        if (contact != null) {
            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contacts>> getAllContacts() {
        List<Contacts> contacts = contactsService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    
    @PostMapping("/create")
    public ResponseEntity<Contacts> createContact(@RequestBody Contacts contact) {
        Contacts createdContact = contactsService.createContact(contact);
        if (createdContact != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Contacts> updateContact(@PathVariable int id, @RequestBody Contacts updatedContact) {
        contactsService.updateContact(id, updatedContact);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        boolean deleted = contactsService.deleteContact(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add endpoints for creating, updating, and deleting Contacts
}
