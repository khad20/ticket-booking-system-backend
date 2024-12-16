package com.example.ticket_booking_system.Repositories;

import com.example.ticket_booking_system.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findByEventId(int id);

    @Query("SELECT t.availabilty FROM Ticket t WHERE t.event.id = :eventId")
    Optional<Integer> findAvailabilityByEventId(@Param("eventId") Long eventId);

    @Query("SELECT t.price FROM Ticket t WHERE t.event.id = :eventId")
    Optional<Double> findSellingPriceByEventId(@Param("eventId") Long eventId);



    @Query("SELECT COUNT(t) > 0 FROM Ticket t WHERE t.event.id = :eventId")
    boolean existsByEventId(@Param("eventId") Long eventId);

    @Query("SELECT t FROM Ticket t WHERE t.event.id = :eventId")
    Optional<Ticket> findByEventIds(@Param("eventId") Long eventId);
}
