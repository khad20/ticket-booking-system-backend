package com.example.ticket_booking_system.Repositories;

import com.example.ticket_booking_system.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
