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
    private String eventName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int noOfTickets;

    @Column(nullable = false)
    private int costPrice;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private boolean availability = true;

    @ManyToOne
    @JoinColumn(name = "vendorID", nullable = false)
    private User vendor;
}
