package com.example.controller;

import com.example.model.Ticket;
import com.example.service.TicketService;

import java.util.List;

public class TicketController {
    
    private TicketService ticketService;

    public TicketController() {
        this.ticketService = new TicketService();
    }

    public void createTicket(String movieName, String seatNumber, String buyerName) {
        Ticket ticket = new Ticket(movieName, seatNumber, buyerName);
        ticketService.saveTicket(ticket);
    }

    public void updateTicket(Long id, String movieName, String seatNumber, String buyerName) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket != null) {
            ticket.setMovieName(movieName);
            ticket.setSeatNumber(seatNumber);
            ticket.setBuyerName(buyerName);
            ticketService.updateTicket(ticket);
        }
    }


    public Ticket getTicket(Long id) {
        return ticketService.getTicketById(id);
    }

    public List<Ticket> listTickets() {
        return ticketService.getAllTickets();
    }

   

    public void deleteTicket(Long id) {
        ticketService.deleteTicket(id);
    }
}
