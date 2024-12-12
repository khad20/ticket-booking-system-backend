package com.example.ticket_booking_system.Services;


import com.example.ticket_booking_system.Entities.Event;
import com.example.ticket_booking_system.Models.EventRequest;
import com.example.ticket_booking_system.Repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

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

}
