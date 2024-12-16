package com.example.ticket_booking_system.Services;


import com.example.ticket_booking_system.Entities.Event;
import com.example.ticket_booking_system.Models.EventRequest;
import com.example.ticket_booking_system.Repositories.EventRepository;
import com.example.ticket_booking_system.Repositories.OrderRepository;
import com.example.ticket_booking_system.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Event getEventById(Long eventId) {
        return eventRepository.findById(Math.toIntExact(eventId)).orElse(null);
    }

    public boolean updateEvent(Long id, EventRequest updatedEventRequest) {
        Optional<Event> optionalEvent = eventRepository.findById(Math.toIntExact(id));

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            // Update fields
            event.setEvent_name(updatedEventRequest.getEventName());
            event.setLocation(updatedEventRequest.getLocation());
            event.setTime(updatedEventRequest.getTime());
            event.setNo_of_tickets(updatedEventRequest.getNoOfTickets());
            event.setCost_price(updatedEventRequest.getCostPrice());
            event.setAvailabilty(updatedEventRequest.isAvailabilty());

            // Save updated event
            eventRepository.save(event);
            return true;
        }
        return false;
    }
    public boolean deleteEventById(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(Math.toIntExact(id));

        if (optionalEvent.isPresent()) {
            eventRepository.deleteById(Math.toIntExact(id));
            return true;
        }
        return false;
    }
    public void markExpiredEventsAsUnavailable() {
        int updatedCount = eventRepository.updateAvailabilityForExpiredEvents();
        System.out.println("Updated availability for " + updatedCount + " expired events.");
    }


    public List<Event> getAllAvailableEvents() {
        return eventRepository.findAllAvailableEvents();
    }
}
