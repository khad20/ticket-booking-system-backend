package com.example.ticket_booking_system.Controller;


import com.example.ticket_booking_system.Entities.User;
import com.example.ticket_booking_system.Models.AuthRequest;
import com.example.ticket_booking_system.Models.AuthResponse;
import com.example.ticket_booking_system.Models.SignupRequest;
import com.example.ticket_booking_system.Repositories.UserRepository;
import com.example.ticket_booking_system.Security.JWTUtil;
import com.example.ticket_booking_system.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    // Signup User

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Fetch user details from the database
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtUtil.generateToken(request.getUsername());

        return new AuthResponse(token, user.getType());
    }

    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

}
