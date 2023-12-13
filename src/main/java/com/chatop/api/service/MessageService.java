package com.chatop.api.service;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.model.Message;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository, RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public void saveMessage(MessageDTO messageDTO) {
        // Convert the MessageDTO to a Message entity
        Message message = new Message();
        message.setMessage(messageDTO.getMessage());

        User user = userRepository.findById(messageDTO.getUser_id())
                .orElseThrow(() -> new NoSuchElementException("User not found with id : " + messageDTO.getUser_id()));
        message.setUser(user);

        Rental rental = rentalRepository.findById(messageDTO.getRental_id())
                .orElseThrow(() -> new NoSuchElementException("Rental not found with id : " + messageDTO.getRental_id()));
        message.setRental(rental);

        // Save the Message entity in the database
        messageRepository.save(message);
    }
}
