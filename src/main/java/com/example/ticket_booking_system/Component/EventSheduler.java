package com.example.ticket_booking_system.Component;

import com.example.ticket_booking_system.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventSheduler {
    @Autowired
    private EventService eventService;

    @Scheduled(fixedRate = 60000) // Runs every minute
    public void updateEventAvailability() {
        System.out.println("Running scheduled task to update expired events...");
        eventService.markExpiredEventsAsUnavailable();
    }
}
