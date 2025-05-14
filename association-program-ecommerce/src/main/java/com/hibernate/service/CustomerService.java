package com.hibernate.service;

import com.hibernate.dao.CustomerDAO;
import com.hibernate.model.Customer;
import com.hibernate.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

	 private CustomerDAO customerDAO;

	    @Autowired
	    public void setCustomerDAO(CustomerDAO customerDAO) {
	        this.customerDAO = customerDAO;
	    }

    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerDAO.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(Long id) {
        customerDAO.deleteCustomer(id);
    }

    public void addProductToCustomer(Long customerId, Product product) {
        customerDAO.addProductToCustomer(customerId, product);
    }


    public List<Product> getPurchasedProductsByCustomerId(Long customerId) {
        return customerDAO.getPurchasedProductsByCustomerId(customerId);
    }
}
