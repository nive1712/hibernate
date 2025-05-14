package com.example.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
    	
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        Book book = new Book();
        book.setName("Nivedha");
        book.setAuthor("Hello world");
        book.setBookName("Life");

        session1.save(book);
        session1.getTransaction().commit();
        session1.close(); //1st close 
       

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        //fetches from database
        //stored in 1st and 2 nd
        Book retrievedBook = session2.get(Book.class, book.getId());
        logger.info("Retrieved Book Name:{} ", retrievedBook.getBookName());

        session2.getTransaction().commit();
        
        session2.clear();//1st got clear
        session2.close();//2nd still there

        Session session3 = sessionFactory.openSession();
        session3.beginTransaction();

        Book cachedBook = session3.get(Book.class, book.getId());
        //fetches from 2nd cache , not 1st because session has been closed
        //and because it gets from 2nd cache <stores in the 1st and 2nd >
        logger.info("Cached Book Name: {}" ,cachedBook.getBookName());//1st
        
        cachedBook = session3.get(Book.class, book.getId());
        logger.info("First: {}" ,cachedBook.getBookName());//1st
        session3.clear();

        cachedBook = session3.get(Book.class, book.getId());
        logger.info("After First clear Second: {}" ,cachedBook.getBookName());
        session3.clear();//cleared 
        Thread.sleep(25000);//sleep
        //database
        cachedBook = session3.get(Book.class, book.getId());
        logger.info("Second:{} ",cachedBook.getBookName());

        session3.getTransaction().commit();
        session3.close();

        sessionFactory.close();//entirely closed 
    }
}
package com.example.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
    	
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        Book book = new Book();
        book.setName("Nivedha");
        book.setAuthor("Hello world");
        book.setBookName("Life");

        session1.save(book);
        session1.getTransaction().commit();
        session1.close(); //1st close 
       

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        //fetches from database
        //stored in 1st and 2 nd
        Book retrievedBook = session2.get(Book.class, book.getId());
        logger.info("Retrieved Book Name:{} ", retrievedBook.getBookName());

        session2.getTransaction().commit();
        
        session2.clear();//1st got clear
        session2.close();//2nd still there

        Session session3 = sessionFactory.openSession();
        session3.beginTransaction();

        Book cachedBook = session3.get(Book.class, book.getId());
        //fetches from 2nd cache , not 1st because session has been closed
        //and because it gets from 2nd cache <stores in the 1st and 2nd >
        logger.info("Cached Book Name: {}" ,cachedBook.getBookName());//1st
        
        cachedBook = session3.get(Book.class, book.getId());
        logger.info("First: {}" ,cachedBook.getBookName());//1st
        session3.clear();

        cachedBook = session3.get(Book.class, book.getId());
        logger.info("After First clear Second: {}" ,cachedBook.getBookName());
        session3.clear();//cleared 
        Thread.sleep(25000);//sleep
        //database
        cachedBook = session3.get(Book.class, book.getId());
        logger.info("Second:{} ",cachedBook.getBookName());

        session3.getTransaction().commit();
        session3.close();

        sessionFactory.close();//entirely closed 
    }
}
