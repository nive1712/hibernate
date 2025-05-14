package com.hibernate.service;

import com.hibernate.dao.CustomerDAO;
import com.hibernate.dao.ProductDAO;
import com.hibernate.model.Customer;
import com.hibernate.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

	  private ProductDAO productDAO;
	    private CustomerDAO customerDAO;

	    @Autowired
	    public void setProductDAO(ProductDAO productDAO) {
	        this.productDAO = productDAO;
	    }

	    @Autowired
	    public void setCustomerDAO(CustomerDAO customerDAO) {
	        this.customerDAO = customerDAO;
	    }

    public void saveProduct(Product product) {
        productDAO.saveProduct(product);
    }

    public Product getProductById(Long id) {
        return productDAO.getProductById(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }

    public void addProductToCustomer(Long customerId, Long productId) {
        Customer customer = customerDAO.getCustomerById(customerId);
        Product product = productDAO.getProductById(productId);
        if (customer != null && product != null) {
            customer.getPurchasedProducts().add(product);
            customerDAO.updateCustomer(customer);
        }
    }

    public void removeProductFromCustomer(Long customerId, Long productId) {
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer != null) {
            customer.getPurchasedProducts().removeIf(product -> product.getId().equals(productId));
            customerDAO.updateCustomer(customer);
        }
    }

    public List<Product> getPurchasedProductsByCustomerId(Long customerId) {
        return customerDAO.getPurchasedProductsByCustomerId(customerId);
    }

	public Product getProductByNameAndCategory(String productName, String categoryName) {
		
		return productDAO.getProductByNameAndCategory(productName , categoryName );
	}
}
