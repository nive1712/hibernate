package com.example.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.entity.Product;
import com.example.util.HibernateUtil;

public class ProductService {

	    public void batchInsertProducts(List<Product> products) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction transaction = null;

	        try {
	            transaction = session.beginTransaction();
	            for (int i = 0; i < products.size(); i++) {
	                session.save(products.get(i));
	                if (i % 20 == 0) { // Flush a batch of inserts and release memory.
	                    session.flush();
	                    session.clear();
	                }
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }

    public void batchUpdateProducts(List<Product> products) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            for (int i = 0; i < products.size(); i++) {
                session.update(products.get(i));
                if (i % 20 == 0) { // Flush a batch of updates and release memory.
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
