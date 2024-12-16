package com.example.ticket_booking_system.Models;

import lombok.Data;

@Data
public class BookRequest {
    private long eventid;
    private int noOfTickets;
    private int price;
    private int userid;
    private int ticketid;

}
