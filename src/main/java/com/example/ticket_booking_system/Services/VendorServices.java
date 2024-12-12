package com.example.ticket_booking_system.Services;


import com.example.ticket_booking_system.Entities.Event;
import com.example.ticket_booking_system.Entities.User;
import com.example.ticket_booking_system.Models.EventRequest;
import com.example.ticket_booking_system.Repositories.EventRepository;
import com.example.ticket_booking_system.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getVendorEvents(String username) {
        // Fetch the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Ensure the user is a vendor
        if (!user.getType().equalsIgnoreCase("vendor")) {
            throw new IllegalArgumentException("User is not authorized to access vendor events");
        }

        // Fetch events created by the vendor (userID == vendorID)
        return eventRepository.findByVendorId(user.getId());
    }

    public void createEvent(EventRequest request, String username) {
        // Fetch the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Ensure the user is a vendor
        if (!user.getType().equalsIgnoreCase("vendor")) {
            throw new IllegalArgumentException("User is not authorized to create events");
        }

        // Create a new event
        Event newEvent = new Event();
        newEvent.setEvent_name(request.getEventName());
        newEvent.setLocation(request.getLocation());
        newEvent.setTime(request.getTime());
        newEvent.setVendor(user);
        newEvent.setCost_price(request.getCostPrice());
        newEvent.setAvailabilty(request.isAvailabilty());
        newEvent.setNo_of_tickets(request.getNoOfTickets());

        System.out.println(newEvent);
        // Save the event to the database
        eventRepository.save(newEvent);
    }
}
