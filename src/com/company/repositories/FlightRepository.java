package com.company.repositories;

import com.company.data.PostgresDB;
import com.company.models.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();

        String sql = "SELECT * FROM flights";

        try (Connection c = PostgresDB.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                flights.add(new Flight(
                        rs.getInt("id"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getDouble("price"),
                        rs.getInt("available_seats")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void updateSeats(int flightId, int seats) {
        String sql = "UPDATE flights SET available_seats = available_seats - ? WHERE id = ?";

        try (Connection c = PostgresDB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, seats);
            ps.setInt(2, flightId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
