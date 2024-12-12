package com.example.ticket_booking_system.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String event_name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int no_of_tickets;

    @Column(nullable = false)
    private int cost_price;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private boolean availabilty = true;

    @ManyToOne
    @JoinColumn(name = "vendorid", nullable = false)
    private User vendor;




}
