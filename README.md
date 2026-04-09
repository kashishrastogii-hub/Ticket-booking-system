## 🎟️Ticket Booking System (CLI-Based)

A command-line based train ticket booking application built with Java. It is designed to strengthen Object-Oriented Programming (OOP) skills while implementing practical functionalities like user authentication, train search, ticket booking, and cancellation.

# 🚀 Features
- ✔️ User Authentication – Secure sign-up and login system
- ✔️ Train Search – Find trains between selected stations
- ✔️ Seat Availability – View available seats before booking
- ✔️ Booking Management – Reserve and cancel train seats
- ✔️ Data Persistence – Store user and booking information

# 🛠️ Technologies Used
- 🔹 Java – Core logic and implementation
- 🔹 Jackson – JSON parsing
- 🔹 BCrypt – Password hashing 
- 🔹 Gradle – Build automation and dependency management
- 🔹 Collections & Streams API – Efficient data handling
- 🔹 OOP Principles – Encapsulation, Abstraction, and Polymorphism

# Project Structure
app/src/main/java/ticket/booking/
|--- entities/      # Train, User, Ticket models
├── services/       # TrainService, UserBookingService
├── util/           # UserServiceUtil
└── LocalDB/        # trains.json, users.json

# Usage
1. SignUp
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
