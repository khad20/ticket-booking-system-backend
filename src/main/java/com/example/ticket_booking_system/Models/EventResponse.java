package com.example.ticket_booking_system.Models;

import com.example.ticket_booking_system.Entities.Event;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponse {
    public String event_name;
    public String location;
    public LocalDateTime time;
    public int no_of_tickets;
    public int cost_price;
    public boolean availabilty;
    public String vendor;

    public EventResponse(Event event) {
        this.event_name = event.getEvent_name();
        this.location = event.getLocation();
        this.time = event.getTime();
        this.no_of_tickets = event.getNo_of_tickets();
        this.cost_price = event.getCost_price();
        this.availabilty = event.isAvailabilty();
        this.vendor = event.getVendor().getUsername();
    }
}
