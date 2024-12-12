package com.example.ticket_booking_system.Controller;


import com.example.ticket_booking_system.Entities.Event;
import com.example.ticket_booking_system.Models.EventRequest;
import com.example.ticket_booking_system.Models.EventResponse;
import com.example.ticket_booking_system.Security.JWTUtil;
import com.example.ticket_booking_system.Services.EventService;
import com.example.ticket_booking_system.Services.VendorServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:4200") // Allow frontend requests
public class VendorController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private VendorServices vendorService;

    @Autowired
    private EventService eventServices;

    @GetMapping("/event")
    public List<Event> getVendorEvents(@RequestHeader("Authorization") String token) {
        // Extract the username from the JWT token
        String jwt = token.substring(7); // Remove "Bearer " prefix

        String username = jwtUtil.extractUsername(jwt);

        System.out.println("Request from vendor: " + username);

        // Get events created by the vendor
        return vendorService.getVendorEvents(username);
    }

    @PostMapping("/events/create")
    public ResponseEntity<String> createEvent(@RequestHeader("Authorization") String token, @RequestBody EventRequest event) {
        // Extract the username from the JWT token
        String jwt = token.substring(7); // Remove "Bearer " prefix

        String username = jwtUtil.extractUsername(jwt);

        System.out.println("Request from vendor: " + username);

        // Create the event
        vendorService.createEvent( event, username);

        return ResponseEntity.status(HttpStatus.CREATED).body("Event created successfully");
    }

    @GetMapping("/events/edit/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long id) {
        Event event = eventServices.getEventById(id);

        return ResponseEntity.ok(new EventResponse(event));
    }

    @PutMapping("/events/edit/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody EventRequest event) {
        eventServices.updateEvent(id, event);

        return ResponseEntity.ok("Event updated successfully");
    }

}
