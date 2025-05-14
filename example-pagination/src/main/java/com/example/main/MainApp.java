package com.example.main;

import com.example.entity.User;
import com.example.service.UserService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainApp {
	private static final Logger logger = LogManager.getLogger(MainApp.class);
    public static void main(String[] args) {
    	
        UserService userService = new UserService();

        for (int i = 1; i <= 50; i++) {
            userService.createUser("User" + i, "user" + i + "@example.com");
        }

        int pageNumber = 1;
        int pageSize = 5;
        List<User> users = userService.getUsers(pageNumber, pageSize);

        
        logger.info("Page {} of users:", pageNumber);

        for (User user : users) {
        	logger.info("{} - {} - {}", user.getId(), user.getName(), user.getEmail());
        }
    }
}
