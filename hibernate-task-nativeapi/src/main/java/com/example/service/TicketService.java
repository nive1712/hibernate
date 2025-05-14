package com.example.service;

import com.example.dao.TicketDao;
import com.example.model.Ticket;

import java.util.List;

public class TicketService {
    
    private TicketDao ticketDao;

    public TicketService() {
        this.ticketDao = new TicketDao();
    }

    public void saveTicket(Ticket ticket) {
        ticketDao.saveTicket(ticket);
    }

    public Ticket getTicketById(Long id) {
        return ticketDao.getTicketById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    public void updateTicket(Ticket ticket) {
        ticketDao.updateTicket(ticket);
    }

    public void deleteTicket(Long id) {
        ticketDao.deleteTicket(id);
    }
}
