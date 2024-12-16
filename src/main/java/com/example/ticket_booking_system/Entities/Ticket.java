package com.example.ticket_booking_system.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "eventID", nullable = false)
    private Event event;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int availabilty;
}