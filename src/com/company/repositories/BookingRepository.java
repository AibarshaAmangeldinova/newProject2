package com.company.services;

import com.company.models.Flight;
import com.company.repositories.BookingRepository;
import com.company.repositories.FlightRepository;

public class BookingService {

    private final FlightRepository flightRepo = new FlightRepository();
    private final BookingRepository bookingRepo = new BookingRepository();

    public void bookFlight(int userId, Flight flight, int seats) {

        if (flight.getAvailableSeats() < seats) {
            throw new RuntimeException("Not enough seats");
        }

        flightRepo.updateSeats(flight.getId(), seats);
        bookingRepo.save(userId, flight.getId(), seats);

        System.out.println("✅ Booking successful!");
        System.out.println("Total price: " + flight.getPrice() * seats);
    }
}

