package com.example.dao;

import com.example.model.Ticket;
import com.example.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class TicketDao {

    private EntityManagerFactory entityManagerFactory;

    public TicketDao() {
        this.entityManagerFactory = JPAUtil.getEntityManagerFactory();
    }

    public void saveTicket(Ticket ticket) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(ticket);
            transaction.commit();
        } finally {
            entityManager.close();
        }
    }

    public Ticket getTicketById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Ticket.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Ticket> getAllTickets() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("from Ticket", Ticket.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void updateTicket(Ticket ticket) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(ticket);
            transaction.commit();
        } finally {
            entityManager.close();
        }
    }

    public void deleteTicket(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Ticket ticket = entityManager.find(Ticket.class, id);
            if (ticket != null) {
                entityManager.remove(ticket);
            }
            transaction.commit();
        } finally {
            entityManager.close();
        }
    }
}
