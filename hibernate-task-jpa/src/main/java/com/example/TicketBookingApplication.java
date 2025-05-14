package com.example;

import com.example.controller.TicketController;
import com.example.model.Ticket;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketBookingApplication {
	private static final Logger logger = LogManager.getLogger(TicketBookingApplication.class);
    public static void main(String[] args) {
        TicketController ticketController = new TicketController();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            logger.info("Welcome to Ticket Booking System");
            logger.info("1.Create Ticket");
            
            logger.info("2. Get Ticket by ID");
            logger.info("3. List All Tickets");
            logger.info("4. Update Ticket");
            logger.info("5. Delete Ticket");
            logger.info("6. Exit");
            logger.info("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                	  logger.info("Enter Movie Name: ");
                    String movieName = scanner.nextLine();
                    logger.info("Enter Seat Number: ");
                    String seatNumber = scanner.nextLine();
                    logger.info("Enter Buyer Name: ");
                    String buyerName = scanner.nextLine();
                    ticketController.createTicket(movieName, seatNumber, buyerName);
                    logger.info("Ticket created successfully.");
                    break;

                case 2:
                	  logger.info("Enter Ticket ID: ");
                    long getId = scanner.nextLong();
                    scanner.nextLine();  

                    Ticket ticket = ticketController.getTicket(getId);
                    if (ticket != null) {
                    	  logger.info("Ticket: " + ticket.getId() + ", " + ticket.getMovieName() + ", " + ticket.getSeatNumber() + ", " + ticket.getBuyerName());
                    } else {
                    	  logger.info("Ticket not found.");
                    }
                    break;

                case 3:
                    List<Ticket> tickets = ticketController.listTickets();
                    for (Ticket t : tickets) {
                    	  logger.info("Ticket Details:\n" + 
                                           "ID: " + t.getId() + "\n" + 
                                           "Movie Name: " + t.getMovieName() + "\n" + 
                                           "Seat Number: " + t.getSeatNumber() + "\n" + 
                                           "Buyer Name: " + t.getBuyerName() + "\n\n");
                    }
                    break;


                case 4:
                	  logger.info("Enter Ticket ID to update: ");
                    long updateId = scanner.nextLong();
                    scanner.nextLine();  

                    logger.info("Enter New Movie Name: ");
                    String newMovieName = scanner.nextLine();
                    logger.info("Enter New Seat Number: ");
                    String newSeatNumber = scanner.nextLine();
                
                    
                    logger.info("Enter New Buyer Name: ");
                    String newBuyerName = scanner.nextLine();

                    ticketController.updateTicket(updateId, newMovieName, newSeatNumber, newBuyerName);
                    logger.info("Ticket updated successfully.");
                    break;

                case 5:
                	  logger.info("Enter Ticket ID to delete: ");
                    long deleteId = scanner.nextLong();
                    scanner.nextLine(); 

                    ticketController.deleteTicket(deleteId);
                    logger.info("Ticket deleted successfully.");
                    break;

                case 6:
                    running = false;
                    logger.info("Exiting...");
                    break;

                default:
                	  logger.info("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
