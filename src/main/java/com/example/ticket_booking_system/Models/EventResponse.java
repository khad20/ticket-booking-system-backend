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
    private int ticketavailable;
    private double  sellingprice;

}
