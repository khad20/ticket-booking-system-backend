package com.example.ticket_booking_system.Services;


import com.example.ticket_booking_system.Entities.Order;
import com.example.ticket_booking_system.Entities.Ticket;
import com.example.ticket_booking_system.Entities.User;
import com.example.ticket_booking_system.Models.BookRequest;
import com.example.ticket_booking_system.Models.TicketRequest;
import com.example.ticket_booking_system.Repositories.EventRepository;
import com.example.ticket_booking_system.Repositories.OrderRepository;
import com.example.ticket_booking_system.Repositories.TicketRepository;
import com.example.ticket_booking_system.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void addTickets(TicketRequest ticketRequest) throws Exception {
        System.out.println(ticketRepository.existsByEventId(ticketRequest.getEventid()));
        if (ticketRepository.existsByEventId(ticketRequest.getEventid())) {
            // edit ticket
            Ticket ticket = ticketRepository.findByEventId(Math.toIntExact(ticketRequest.getEventid()));
            ticket.setPrice(ticketRequest.getPrice());
            ticket.setAvailabilty(ticketRequest.getAvailability());
            ticketRepository.save(ticket);
        }else {
            Ticket ticket = new Ticket();
            ticket.setEvent(eventRepository.findById(Math.toIntExact(ticketRequest.getEventid())).orElse(null));
            ticket.setPrice(ticketRequest.getPrice());
            ticket.setAvailabilty(ticketRequest.getAvailability());

            ticketRepository.save(ticket);
        }

    }


    public boolean checkEventTicket(Long eventId) {
        return ticketRepository.existsByEventId(eventId);
    }

    public int getTicketAvailability(Long eventId) {
        return ticketRepository.findAvailabilityByEventId(eventId).orElse(0);
    }

    public double getSellingPrice(Long eventId) {
        return ticketRepository.findSellingPriceByEventId(eventId).orElse(0.0);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketByEventId(Long id) {
        return ticketRepository.findByEventId(Math.toIntExact(id));
    }

    public void bookTicket(BookRequest bookingRequest) {
        int ticketsBooked = bookingRequest.getNoOfTickets();

        System.out.println(bookingRequest);

        Ticket ticket = ticketRepository.findByEventId(Math.toIntExact(bookingRequest.getEventid()));


        int availableTickets = ticket.getAvailabilty();

        // Check if there are enough tickets available
        if (availableTickets < ticketsBooked) {
            throw new IllegalArgumentException("Not enough tickets available");
        }

        ticket.setAvailabilty(availableTickets - ticketsBooked);
        ticketRepository.save(ticket);


        //get the user by the id
        User user = userRepository.findById(bookingRequest.getUserid())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));


        Order order = new Order();
        order.setEvent(eventRepository.findById(Math.toIntExact(bookingRequest.getEventid())).orElse(null));
        order.setNoOfTickets(ticketsBooked);
        order.setTotalAmount(ticket.getPrice());
        order.setUser(user);
        order.setTicket(ticket);

        //save the order
        orderRepository.save(order);
    }
}
