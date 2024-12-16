package com.example.ticket_booking_system.Controller;


import com.example.ticket_booking_system.Models.TicketRequest;
import com.example.ticket_booking_system.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

    @Autowired
    private TicketService ticketService;


    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addTicket(@RequestBody TicketRequest ticketRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            ticketService.addTickets(ticketRequest);

            response.put("status", "success");
            response.put("message", "Tickets added successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to add tickets: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
