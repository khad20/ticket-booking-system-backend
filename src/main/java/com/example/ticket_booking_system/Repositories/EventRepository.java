package com.example.ticket_booking_system.Repositories;

import com.example.ticket_booking_system.Entities.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByVendorId(int id);


    @Query("SELECT e FROM Event e WHERE e.availabilty = true")
    List<Event> findAllAvailableEvents();

    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.availabilty = false WHERE e.time < CURRENT_TIMESTAMP AND e.availabilty = true")
    int updateAvailabilityForExpiredEvents();
}
