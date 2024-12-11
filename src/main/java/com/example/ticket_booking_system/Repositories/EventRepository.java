package com.example.ticket_booking_system.Repositories;

import com.example.ticket_booking_system.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
