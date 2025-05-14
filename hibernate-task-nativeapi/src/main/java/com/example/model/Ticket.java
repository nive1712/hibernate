package com.example.model;

//import javax.persistence.Table;

//@Entity
//@Table(name = "tickets")
public class Ticket {
    
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@Column(name = "movie_name")
    private String movieName;
    
    //@Column(name = "seat_number")
    private String seatNumber;
    
    //@Column(name = "buyer_name")
    private String buyerName;

    public Ticket() {}

    public Ticket(String movieName, String seatNumber, String buyerName) {
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.buyerName = buyerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
