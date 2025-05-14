package com.hibernate.service;

import com.hibernate.dao.ReviewDAO;
import com.hibernate.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewService {

	 private ReviewDAO reviewDAO;

	    @Autowired
	    public void setReviewDAO(ReviewDAO reviewDAO) {
	        this.reviewDAO = reviewDAO;
	    }

    public void saveReview(Review review) {
        reviewDAO.saveReview(review);
    }

    public Review getReviewById(Long id) {
        return reviewDAO.getReviewById(id);
    }

    public List<Review> getReviewsByCustomer(Long customerId) {
        return reviewDAO.getReviewsByCustomerId(customerId);
    }

    public List<Review> getReviewsByProduct(Long productId) {
        return reviewDAO.getReviewsByProductId(productId);
    }

    public void updateReview(Review review) {
        reviewDAO.updateReview(review);
    }

    public void deleteReview(Long id) {
        reviewDAO.deleteReview(id);
    }
   

}
