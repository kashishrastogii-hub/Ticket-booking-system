package ticket.booking.entities;

import java.util.Collections;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

    public User(){}

    public User (String name, String password, String hashPassword, List<Ticket> ticketsBooked, String userId) {
        this.username = name;
        this.password = password;
        this.hashPassword = hashPassword;
        this.userId = userId;
        this.ticketsBooked = ticketsBooked != null ? ticketsBooked: Collections.emptyList();
    }

    public String getUsername() {
        return username;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void printTickets() {
        if(ticketsBooked.isEmpty()) {
            System.out.println("No tickets booked yet");
        } else {
            for(int i=0; i<ticketsBooked.size(); i++) {
                System.out.println(ticketsBooked.get(i).getTicketInfo());
            }
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
    public String getHashPassword() {
        return hashPassword;
    }



}
