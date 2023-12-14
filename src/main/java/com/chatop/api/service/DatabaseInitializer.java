package com.chatop.api.service;

import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseInitializer {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DatabaseInitializer(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent ignoredEvent) {
        // Find the user
        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals("test@test.com"))
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Hash the password and update the user
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }
}