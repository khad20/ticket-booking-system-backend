package com.example.ticket_booking_system.Models;

import lombok.Data;

@Data
public class BookRequest {
    private long eventId;
    private int numberOfTickets;
    private int totalPrice;
    private int userId;
}
