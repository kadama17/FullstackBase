package com.kadama.solution.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadama.solution.model.Message;
import com.kadama.solution.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    public Message getMessageById(int id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.orElse(null);
    }
    
    public Message createMessage(Message message) {
        // Vous pouvez implémenter ici la logique de création du message
        // Par exemple, pour enregistrer le message dans la base de données :
        return messageRepository.save(message);
    }

    public Message updateMessage(int id, Message updatedMessage) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            // Mettez à jour les propriétés du message avec celles de updatedMessage
            message.setNumberOfReceiver(updatedMessage.getNumberOfReceiver());
            message.setState(updatedMessage.getState());
            // Ajoutez d'autres propriétés au besoin
            // Enregistrez les modifications dans la base de données
            return messageRepository.save(message);
        }
        return null; // Retournez null si le message n'est pas trouvé
    }

    public boolean deleteMessage(int id) {
        // Supprimez le message en fonction de son ID
        try {
            messageRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false; // Retournez false si le message n'a pas pu être supprimé
        }
    }
    // Ajoutez ici des méthodes de service pour la gestion des messages
}
