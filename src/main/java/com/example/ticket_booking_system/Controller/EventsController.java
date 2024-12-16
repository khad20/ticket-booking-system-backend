package com.example.ticket_booking_system.Controller;


import com.example.ticket_booking_system.Entities.Event;
import com.example.ticket_booking_system.Entities.Ticket;
import com.example.ticket_booking_system.Entities.User;
import com.example.ticket_booking_system.Models.BookRequest;
import com.example.ticket_booking_system.Models.HomeResponse;
import com.example.ticket_booking_system.Repositories.UserRepository;
import com.example.ticket_booking_system.Security.JWTUtil;
import com.example.ticket_booking_system.Services.EventService;
import com.example.ticket_booking_system.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventsController {

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    private ResponseEntity<Map<String, String>> createResponse(String status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/available")
    public ResponseEntity<HomeResponse> getAllAvailableEvents() {
        List<Event> availableEvents = eventService.getAllAvailableEvents();

        List<Ticket> allTickets = ticketService.getAll();

        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setEvent(availableEvents);
        homeResponse.setTicket(allTickets);


        return ResponseEntity.ok(homeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        Ticket ticket = ticketService.getTicketByEventId(id);



        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/book")
    public ResponseEntity<Map<String, String>> bookTicket(@RequestHeader("Authorization") String token, @RequestBody BookRequest booking) {
        try {
            System.out.println("Request to book ticket: " + booking);

            // Extract the username from the JWT token
            String jwt = token.substring(7); // Remove "Bearer " prefix
            String username = JWTUtil.extractUsername(jwt); // Decode the username from the token
            System.out.println("Decoded username: " + username);

            // Find the user by username (assuming username is unique)
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Set the user ID in the booking request
            booking.setUserId(Math.toIntExact(user.getId()));

            // Call the ticketService to process the booking
            ticketService.bookTicket(booking);

            return createResponse("success", "Event created successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            return createResponse("error", e.getMessage());
        }
    }

}
