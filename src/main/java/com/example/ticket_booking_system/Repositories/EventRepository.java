package com.example.ticket_booking_system.Repositories;

import com.example.ticket_booking_system.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByVendorId(int id);
}
