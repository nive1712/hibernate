package com.hibernate.dao;

import com.hibernate.model.Review;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Repository
public class ReviewDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveReview(Review review) {
        entityManager.persist(review);
    }

    @Transactional
    public Review getReviewById(Long id) {
        return entityManager.find(Review.class, id);
    }

    @Transactional
    public List<Review> getAllReviews() {
        return entityManager.createQuery("FROM Review", Review.class).getResultList();
    }

    @Transactional
    public List<Review> getReviewsByProductId(Long productId) {
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.product.id = :productId", Review.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Transactional
    public List<Review> getReviewsByCustomerId(Long customerId) {
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.customer.id = :customerId", Review.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Transactional
    public void updateReview(Review review) {
        entityManager.merge(review);
    }
    
    @Transactional
    public void deleteReview(Long id) {
        Review review = getReviewById(id);
        if (review != null) {
            entityManager.remove(review);
        }
    }
}
