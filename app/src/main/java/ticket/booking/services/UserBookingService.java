package ticket.booking.services;
import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;
    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "app/src/main/java/ticket/booking/LocalDB/users.json";

    public UserBookingService() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadUsers();
    }
    private void loadUsers() throws IOException {
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
    }
    public boolean signUp(User user) throws IOException{
        try {
            Optional<User> foundUser = userList.stream().filter(user1 -> {return user1.getUserId().equalsIgnoreCase(user.getUserId());}).findFirst();
            if (foundUser.isPresent()) {
                System.out.println("User already exists!");
                return false;
            }
            userList.add(user);
            saveUserListToFile();
        } catch (Exception ex) {
            System.out.println("Saving user list to file failed" + ex.getMessage());
            return false;
        }
        return true;
    }
    private void saveUserListToFile() throws IOException {
        File userFile = new File(USERS_PATH);
        objectMapper.writeValue(userFile,userList);
    }
    public void fetchBookings() {
        System.out.println("Fetch your bookings");
        user.printTickets();
    }
    public Optional getUserByUsername(String username){
        return userList.stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst();
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean cancelBooking(String ticketId) throws IOException{
        if (ticketId.isEmpty() || ticketId == null) {
            System.out.println("Ticket ID cannot be null or empty");
            return Boolean.FALSE;
        }
        Boolean isRemoved = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equalsIgnoreCase(ticketId));
        if(isRemoved) {
            saveUserListToFile();
            System.out.println("Ticket with ID" + ticketId + "has been cancelled");
            return true;
        } else {
            System.out.println("No tickets found with ID" + ticketId);
            return false;
        }
    }
    public List<Train> getTrains (String source, String destination) throws IOException{
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (Exception ex) {
            System.out.println("There is something wrong!");
            return Collections.emptyList();
        }
    }
    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }
    public Boolean bookTrainSeat(Train train, int row, int seat) throws IOException {
        try
        {
        TrainService trainService = new TrainService();
        List<List<Integer>> seats = train.getSeats();
        if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
            if (seats.get(row).get(seat) == 0) {
                seats.get(row).set(seat, 1);

                train.setSeats(seats);
                trainService.addTrain(train);

                Ticket ticket = new Ticket();

                ticket.setSource(train.getStation().getFirst());
                ticket.setDestination(train.getStation().getLast());
                ticket.setTrain(train);
                ticket.setUserId(user.getUserId());
                ticket.setDateOfTravel("2021-09-01");
                ticket.setTicketId(UserServiceUtil.generateTicketId());

                user.getTicketsBooked().add(ticket);

                System.out.println("Seat booked successfully  !  ");

                System.out.println(ticket.getTicketInfo());

                saveUserListToFile();
                return true; // Booking successful
            } else {
                return false; // Execute when Seat is already booked
            }
        } else {
            return false; // Execute when Invalid row or seat index
        }
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }
}


