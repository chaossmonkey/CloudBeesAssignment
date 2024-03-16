package com.example.cloudbeesAssignment.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TrainSection {
    private Map<String, Map<Integer,Passenger>> seatAllocations;

    public TrainSection() {
        this.seatAllocations = new HashMap<>();
    }


    public void allocateSeat(int seatNumber, Passenger passenger,String sectionName) {
        Map<Integer, Passenger> sectionSeats = seatAllocations.computeIfAbsent(sectionName, k -> new HashMap<>());
        sectionSeats.put(seatNumber, passenger);
    }

    public Map<Integer,Passenger> getSeatAllocationsBySection(String sectionName) {
        Map<Integer, Passenger> sectionSeats = seatAllocations.get(sectionName);
        return sectionSeats != null ? Collections.unmodifiableMap(sectionSeats) : null;

    }

    public Passenger removeUser(String section, Integer seatNumber) {
        Map<Integer, Passenger> sectionSeats = seatAllocations.get(section);
        if (sectionSeats != null) { // Check if section exists
            return sectionSeats.remove(seatNumber); // Remove and return the user from the seat
        }
        return null; // Return null if section doesn't exist
    }

    public boolean updateUserSeat(String section, Integer oldSeatNumber, Integer newSeatNumber) {
        Map<Integer, Passenger> sectionSeats = seatAllocations.get(section);

        if (sectionSeats != null) { // Check if section exists
            Passenger user = sectionSeats.remove(oldSeatNumber); // Remove the user from the old seat
            if (user != null) { // Check if the user exists in the old seat

                sectionSeats.put(newSeatNumber, user); // Put the user in the new seat

                return true; // Return true indicating successful seat modification
            }
        }

        return false; // Return false if section or old seat doesn't exist, or user is not found in the old seat
    }


}
