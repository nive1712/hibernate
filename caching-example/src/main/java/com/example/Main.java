package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
    	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        ShoppingCart cart = new ShoppingCart("Nive", "Laptop", "nive19@example.com");
        session.saveOrUpdate(cart);

        session.getTransaction().commit();
        session.close();
        
        
        //2nd session
        
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        ShoppingCart retrievedCart = session1.get(ShoppingCart.class, cart.getId());
        logger.info("First retrieval: {} bought {} with email {}", 
        	    retrievedCart.getBuyerName(), retrievedCart.getProduct(), retrievedCart.getEmail());

        
        retrievedCart.setBuyerName("Niku");
        retrievedCart.setProduct("Smartphone");
        retrievedCart.setEmail("nikuchan@example.com");
       
        
        session1.getTransaction().commit();
        
        ShoppingCart updatedCart = session1.get(ShoppingCart.class, cart.getId());
        logger.info("Second retrieval: {} bought {} with email {}", 
        	    updatedCart.getBuyerName(), updatedCart.getProduct(), updatedCart.getEmail());


        session1.close();
        sessionFactory.close();
    }
}
