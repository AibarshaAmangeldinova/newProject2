package com.company.controllers;

import com.company.repositories.FlightRepository;
import com.company.services.BookingService;

import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    private final FlightRepository flightRepo = new FlightRepository();
    private final BookingService bookingService = new BookingService();

    public void start() {

        Scanner sc = new Scanner(System.in);

        List<Flight> flights = flightRepo.findAll();

        flights.forEach(f ->
                System.out.println(
                        f.getId() + " | price: " + f.getPrice() +
                                " | seats: " + f.getAvailableSeats()
                )
        );

        System.out.print("Choose flight ID: ");
        int flightId = sc.nextInt();

        System.out.print("Seats: ");
        int seats = sc.nextInt();

        Flight chosen = flights.stream()
                .filter(f -> f.getId() == flightId)
                .findFirst()
                .orElseThrow();

        bookingService.bookFlight(1, chosen, seats);
    }
}

