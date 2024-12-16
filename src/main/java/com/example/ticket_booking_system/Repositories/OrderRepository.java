package com.example.ticket_booking_system.Repositories;

import com.example.ticket_booking_system.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByEventId(int id);
}
