package com.hibernate.dao;

import com.hibernate.model.Customer;
import com.hibernate.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("FROM Customer", Customer.class).getResultList();
    }

    public void updateCustomer(Customer customer) {
        entityManager.merge(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }

    public void addProductToCustomer(Long customerId, Product product) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if (customer != null) {
            customer.getPurchasedProducts().add(product);
            entityManager.merge(customer);
        }
    }

    public List<Product> getPurchasedProductsByCustomerId(Long customerId) {
        String jpql = "SELECT DISTINCT p FROM Product p JOIN FETCH p.customers c WHERE c.id = :customerId";
        return entityManager.createQuery(jpql, Product.class)
                            .setParameter("customerId", customerId)
                            .getResultList();
    }
}
