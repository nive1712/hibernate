package com.example.service;

import com.example.entity.User;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;

import java.util.List;

public class UserService {
	
	 private List<User> users = new ArrayList<>();
	
	  public void createUser(String name, String email) {
	        User newUser = new User();
	        newUser.setName(name);
	        newUser.setEmail(email);
	        users.add(newUser);
	    }
	  
	  public List<User> getUsers(int pageNumber, int pageSize) {
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    List<User> userList = null; // Renamed variable to avoid hiding the field

		    try {
		        String hql = "FROM User";
		        Query<User> query = session.createQuery(hql, User.class);
		        query.setFirstResult((pageNumber - 1) * pageSize);
		        query.setMaxResults(pageSize);
		        userList = query.list();
		    } catch (Exception e) {
		        e.printStackTrace(); // Consider replacing this with proper logging
		    } finally {
		        session.close();
		    }

		    return userList;
		}

}
