package com.example.main;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Product;
import com.example.service.ProductService;

public class MainApp {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        // Create a list of products to insert
        List<Product> productsToInsert = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setDescription("Description for product " + i);
            product.setPrice(100.0 + i);
            productsToInsert.add(product);
        }

        // Batch insert products
        System.out.println("Batch inserting products:");
        productService.batchInsertProducts(productsToInsert);

        // Create a list of products to update
        List<Product> productsToUpdate = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setName("Updated Product " + i);
            product.setDescription("Updated description for product " + i);
            product.setPrice(200.0 + i);
            productsToUpdate.add(product);
        }

        // Batch update products
        System.out.println("Batch updating products:");
        productService.batchUpdateProducts(productsToUpdate);
    }
}
