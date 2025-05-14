package com.example.dao;

import com.example.model.Ticket;
import com.example.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TicketDao {
    
    private SessionFactory sessionFactory;

    public TicketDao() {
    	sessionFactory =HibernateUtil.getSessionFactory();
        
    	//sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void saveTicket(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket); //persistent -2nd
        transaction.commit();
        session.close();//detached -3rd 
    }

    public Ticket getTicketById(Long id) {
        Session session = sessionFactory.openSession();
        Ticket ticket = session.get(Ticket.class, id);
        session.close();
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        Session session = sessionFactory.openSession();
        List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).list();
        session.close();
        return tickets;
    }

    public void updateTicket(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(ticket);
        transaction.commit();
        session.close();
    }

    public void deleteTicket(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        if (ticket != null) {
            session.delete(ticket);
        }
        transaction.commit();
        session.close();
    }
}
