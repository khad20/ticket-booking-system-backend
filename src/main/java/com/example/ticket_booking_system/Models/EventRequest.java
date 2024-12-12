package com.example.ticket_booking_system.Models;


import com.example.ticket_booking_system.Entities.Ticket;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
public class EventRequest {
    public String eventName;
    public String location;
    public LocalDateTime time;
    public int noOfTickets;
    public int costPrice;
    public boolean availabilty;
}
