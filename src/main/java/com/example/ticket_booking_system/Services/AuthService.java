package com.example.ticket_booking_system.Services;


import com.example.ticket_booking_system.Entities.User;
import com.example.ticket_booking_system.Models.SignupRequest;
import com.example.ticket_booking_system.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Map<String, String>> signup(SignupRequest request) {
        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Create a new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // Hash the password
        newUser.setEmail(request.getEmail());
        newUser.setType(request.getType());

        // Save the user to the database
        userRepository.save(newUser);

        return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully!"));

    }
}
