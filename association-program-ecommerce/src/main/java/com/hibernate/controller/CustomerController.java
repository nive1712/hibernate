package com.hibernate.controller;

import com.hibernate.model.Customer;
import com.hibernate.model.Product;
import com.hibernate.model.Profile;
import com.hibernate.model.Review;
import com.hibernate.service.CustomerService;
import com.hibernate.service.ProductService;
import com.hibernate.service.ProfileService;
import com.hibernate.service.ReviewService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Controller
public class CustomerController {
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	 private static final String REVIEW_LITERAL = "Review: {}";
	    private static final String CUSTOMER_NOT_FOUND_LITERAL = "Customer not found.";
	    private static final String ENTER_CUSTOMER_ID_LITERAL = "Enter customer ID: ";
	 private CustomerService customerService;
	    private ProductService productService;
	    private ReviewService reviewService;
	    private ProfileService profileService;

	    @Autowired
	    public void setCustomerService(CustomerService customerService) {
	        this.customerService = customerService;
	    }

	    @Autowired
	    public void setProductService(ProductService productService) {
	        this.productService = productService;
	    }

	    @Autowired
	    public void setReviewService(ReviewService reviewService) {
	        this.reviewService = reviewService;
	    }

	    @Autowired
	    public void setProfileService(ProfileService profileService) {
	        this.profileService = profileService;
	    }

    private Scanner scanner = new Scanner(System.in);

    public void start() {
        try {
            while (true) {
                logger.info("1. Add Customer");
                logger.info("2. Add Review");
                logger.info("3. Buy Product");
                logger.info("4. View Reviews for Product");
                logger.info("5. Display Product Purchases and Reviews");
                logger.info("6. View Reviews and Ratings by Customer");
                logger.info("7. Add/Update Profile");
                logger.info("8. View Profile");
                logger.info("9. Exit");
                logger.info("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        addReview();
                        break;
                    case 3:
                        buyProduct();
                        break;
                    case 4:
                        viewReviewsForProduct();
                        break;
                    case 5:
                        displayProductPurchasesAndReviews();
                        break;
                    case 6:
                        viewReviewsByCustomer();
                        break;
                    case 7:
                        addOrUpdateProfile();
                        break;
                    case 8:
                        viewProfile();
                        break;
                    case 9:
                        logger.info("Exiting...");
                        return; // Exit the method and stop the program execution
                    default:
                        logger.info("Invalid choice. Try again.");
                }
            }
        } finally {
            scanner.close(); // Ensure scanner resource is properly closed
        }
    }

    private void addCustomer() {
    	logger.info("Enter customer name: ");
        String name = scanner.nextLine();
        logger.info("Enter customer email: ");
        String email = scanner.nextLine();
        logger.info("Enter customer contact number: ");
        String contactNumber = scanner.nextLine();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setContactNumber(contactNumber);

        customerService.saveCustomer(customer);
        logger.info("Customer added successfully.");
    }

    private void addReview() {
    	logger.info("Enter review text: ");
        String reviewText = scanner.nextLine();
        logger.info("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();  

        //for display purpose:
        List<Product> products = productService.getAllProducts();
        logger.info("Available Products:");
        for (Product product : products) {
        	logger.info("{}. {} - {}", product.getId(), product.getName(), product.getCategoryName());

        }

        logger.info("Enter product ID for review: ");
        Long productId = scanner.nextLong();
        scanner.nextLine(); 

        logger.info("Enter customer ID for review: ");
        Long customerId = scanner.nextLong();
        scanner.nextLine(); 
        Review review = new Review();
        review.setReviewText(reviewText);
        review.setRating(rating);

        Product product = productService.getProductById(productId);

        if (product != null) {
            review.setProduct(product);

            Customer customer = customerService.getCustomerById(customerId);
            if (customer != null) {
                review.setCustomer(customer);

                reviewService.saveReview(review);
                logger.info("Review added successfully.");
            } else {
            	logger.info("Customer not found. Review not added.");
            }
        } else {
        	logger.info("Product not found. Review not added.");
        }
    }

    private void buyProduct() {
    	logger.info(ENTER_CUSTOMER_ID_LITERAL);
        Long customerId = scanner.nextLong();
        scanner.nextLine();

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
        	 logger.info(CUSTOMER_NOT_FOUND_LITERAL);
            return;
        }

        logger.info("Enter product category name: ");
        String categoryName = scanner.nextLine();
        logger.info("Enter product name: ");
        String productName = scanner.nextLine();

        Product product = productService.getProductByNameAndCategory(productName, categoryName);
        if (product == null) {
            
            product = new Product();
            product.setName(productName);
            product.setCategoryName(categoryName);
            productService.saveProduct(product);
        }

        customer.getPurchasedProducts().add(product);

        customerService.updateCustomer(customer);

        logger.info("Product purchased successfully.");
    }


    private void viewReviewsForProduct() {
    	logger.info("Enter product ID: ");
        Long productId = scanner.nextLong();
        scanner.nextLine();  

        Product product = productService.getProductById(productId);
        if (product == null) {
        	logger.info("Product not found.");
            return;
        }

        List<Review> reviews = reviewService.getReviewsByProduct(productId);

        if (reviews.isEmpty()) {
        	logger.info("No reviews found for this product.");
        } else {
        	logger.info("Reviews for Product{} '" , product.getName() + "':");
            for (Review review : reviews) {
            	logger.info("---------");
            	logger.info("Customer: {}" ,review.getCustomer().getName());
            	logger.info("Rating: {}" , review.getRating());
            	logger.info(REVIEW_LITERAL , review.getReviewText());
            	logger.info("--------");
            }
        }
    }

    private void displayProductPurchasesAndReviews() {
    	logger.info(	ENTER_CUSTOMER_ID_LITERAL);
        Long customerId = scanner.nextLong();
        scanner.nextLine(); 

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
        	 logger.info(CUSTOMER_NOT_FOUND_LITERAL);
            return;
        }

        logger.info("Purchased Products:");
        Set<Product> purchasedProducts = customer.getPurchasedProducts();
        for (Product product : purchasedProducts) {
        	logger.info("**********************");
        	logger.info(product.getName() ,"{} - {}" , product.getCategoryName());
        	logger.info("****************************");
        }

        logger.info("Reviews and Ratings:");
        List<Review> reviews = reviewService.getReviewsByCustomer(customerId);
        for (Review review : reviews) {
        	logger.info("-------------------**");
        	logger.info("Product: {}" ,review.getProduct().getName());
        	logger.info("Rating: {}" , review.getRating());
        	logger.info(REVIEW_LITERAL ,review.getReviewText());
        	logger.info("**********************");
        }
    }

    private void addOrUpdateProfile() {
    	logger.info(ENTER_CUSTOMER_ID_LITERAL);
        Long customerId = scanner.nextLong();
        scanner.nextLine();  

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
        	logger.info(CUSTOMER_NOT_FOUND_LITERAL);
            return;
        }

        Profile profile = customer.getProfile();
        if (profile == null) {
            profile = new Profile();
            profile.setCustomer(customer);
        }
        logger.info("Kindly fill it out as soon as possible!, to view your complete profile");
        logger.info("Enter new name (leave empty to keep current): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            customer.setName(name);
        }

        logger.info("Enter new email (leave empty to keep current): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) {
            customer.setEmail(email);
        }

        logger.info("Enter new address (leave empty to keep current): ");
        String address = scanner.nextLine().trim();
        if (!address.isEmpty()) {
            profile.setAddress(address);
        }

        logger.info("Enter new date of birth (leave empty to keep current): ");
        String dateOfBirth = scanner.nextLine().trim();
        if (!dateOfBirth.isEmpty()) {
            profile.setDateOfBirth(dateOfBirth);
        }

        profileService.saveOrUpdateProfile(profile);
        customerService.updateCustomer(customer);

        logger.info("Profile updated successfully!");
    }


    private void viewProfile() {
    	 logger.info(ENTER_CUSTOMER_ID_LITERAL);
        Long customerId = scanner.nextLong();
        scanner.nextLine();  

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            logger.info(CUSTOMER_NOT_FOUND_LITERAL);
            return;
        }

        Profile profile = customer.getProfile();
        if (profile == null) {
        	logger.info("The profile has not been fully set up by the customer.");
        	logger.info("Kindly add/update mandatory details to view your profile - (select 7)!");
            return;
        }
        
        logger.info("WELCOME{} ",customer.getName());
        logger.info("--------*****----------");
        logger.info("Customer Name: {}" ,customer.getName());
        logger.info("Customer Email: {}" , customer.getEmail());
        logger.info("Customer Contact Number: {}" ,customer.getContactNumber());
        logger.info("Address:{} " ,profile.getAddress());
        logger.info("Date of Birth: {}",  profile.getDateOfBirth());
        logger.info("*************************");
    }

    private void viewReviewsByCustomer() {
    	logger.info(ENTER_CUSTOMER_ID_LITERAL);
        Long customerId = scanner.nextLong();
        scanner.nextLine();  

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
        	logger.info(CUSTOMER_NOT_FOUND_LITERAL);
            return;
        }

        List<Review> reviews = reviewService.getReviewsByCustomer(customerId);

        if (reviews.isEmpty()) {
        	logger.info("No reviews found for this customer.");
        } else {
        	logger.info("Reviews and Ratings by Customer {}'" , customer.getName() + "':");
            for (Review review : reviews) {
            	logger.info("-----------------------------");
            	logger.info("Product: {}" , review.getProduct().getName());
                logger.info("Rating:{} " ,review.getRating());
                logger.info(REVIEW_LITERAL , review.getReviewText());
                logger.info("********************************");
            }
        }
    }

}
