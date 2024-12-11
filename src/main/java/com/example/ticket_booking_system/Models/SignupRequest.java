package com.example.ticket_booking_system.Models;


import lombok.Data;

@Data
public class SignupRequest {
    public String username;
    public String password;
    public String email;
    public String type;
}
