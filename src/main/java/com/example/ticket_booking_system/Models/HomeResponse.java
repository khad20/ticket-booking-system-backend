package com.example.ticket_booking_system.Models;

import com.example.ticket_booking_system.Entities.Event;
import com.example.ticket_booking_system.Entities.Ticket;
import lombok.Data;

import java.util.List;

@Data
public class HomeResponse {
    private List<Event> event;
    private List<Ticket> ticket;
}
