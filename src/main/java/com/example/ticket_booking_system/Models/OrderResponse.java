package com.example.ticket_booking_system.Models;

import com.example.ticket_booking_system.Entities.Event;
import com.example.ticket_booking_system.Entities.Ticket;
import lombok.Data;

@Data
public class OrderResponse {
    private Event event;
    private Ticket ticket;
}
