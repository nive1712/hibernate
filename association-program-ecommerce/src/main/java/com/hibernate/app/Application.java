package com.hibernate.app;

import com.hibernate.config.AppConfig;
import com.hibernate.controller.CustomerController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerController controller = context.getBean(CustomerController.class);
        controller.start();        
    }
}
