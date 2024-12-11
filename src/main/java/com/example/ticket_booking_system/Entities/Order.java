package com.example.ticket_booking_system.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int noOfTickets;

    @Column(nullable = false)
    private int totalAmount;

    @ManyToOne
    @JoinColumn(name = "eventID", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "ticketID", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;
}
