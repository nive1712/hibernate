package com.hibernate.dao;

import com.hibernate.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveProduct(Product product) {
        entityManager.persist(product);
    }

    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> getAllProducts() {
        return entityManager.createQuery("FROM Product", Product.class).getResultList();
    }

    public void updateProduct(Product product) {
        entityManager.merge(product);
    }
    
    public Product getProductByNameAndCategory(String name, String category) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :name AND p.categoryName = :category";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("name", name);
        query.setParameter("category", category);
        List<Product> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public void deleteProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}
