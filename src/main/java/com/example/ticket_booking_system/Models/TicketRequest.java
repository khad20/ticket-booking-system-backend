package com.example.ticket_booking_system.Models;

import lombok.Data;

@Data
public class TicketRequest {
    private long eventid;
    private int price;
    private int availability;
}
