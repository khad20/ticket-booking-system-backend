package com.example.ticket_booking_system.Controller;


import com.example.ticket_booking_system.Entities.Event;
import com.example.ticket_booking_system.Models.EventRequest;
import com.example.ticket_booking_system.Models.EventResponse;
import com.example.ticket_booking_system.Security.JWTUtil;
import com.example.ticket_booking_system.Services.EventService;
import com.example.ticket_booking_system.Services.TicketService;
import com.example.ticket_booking_system.Services.VendorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TicketService ticketService;

    private ResponseEntity<Map<String, String>> createResponse(String status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

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
    public ResponseEntity<Map<String, String>> createEvent(@RequestHeader("Authorization") String token, @RequestBody EventRequest event) {
        // Extract the username from the JWT token
        String jwt = token.substring(7); // Remove "Bearer " prefix

        String username = jwtUtil.extractUsername(jwt);

        System.out.println("Request from vendor: " + username);

        // Create the event
        vendorService.createEvent( event, username);

        return createResponse("success", "Event created successfully.");
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long id) {
        Event event = eventServices.getEventById(id);

        EventResponse response = new EventResponse();
        response.setEvent_name(event.getEvent_name());
        response.setLocation(event.getLocation());
        response.setTime(event.getTime());
        response.setNo_of_tickets(event.getNo_of_tickets());
        response.setCost_price(event.getCost_price());
        response.setAvailabilty(event.isAvailabilty());
        response.setVendor(event.getVendor().getUsername());


        // Fetch ticket availability and selling price separately
        int ticketAvailability = ticketService.getTicketAvailability(id);
        double sellingPrice = ticketService.getSellingPrice(id);

        response.setTicketavailable(ticketAvailability);
        response.setSellingprice(sellingPrice);


        return ResponseEntity.ok(response);
    }

    @PutMapping("/events/edit/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody EventRequest event) {
        System.out.println(event);
        eventServices.updateEvent(id, event);

        return ResponseEntity.ok("Event updated successfully");
    }

    @DeleteMapping("/event/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        boolean isDeleted = eventServices.deleteEventById(id);

        if (isDeleted) {
            return ResponseEntity.ok("Event deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found.");
        }
    }

}