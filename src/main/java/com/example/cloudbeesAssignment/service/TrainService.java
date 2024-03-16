package com.example.cloudbeesAssignment.service;

import com.example.cloudbeesAssignment.entity.Passenger;
import com.example.cloudbeesAssignment.entity.Ticket;
import com.example.cloudbeesAssignment.entity.TrainSection;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class TrainService {

    private final TrainSection sections;
    private final Map<String, Ticket> ticketMap;


TrainService()
{
    sections=new TrainSection();
    ticketMap=new HashMap<>();
}
    public Ticket purchaseTicket(String from, String to, Passenger user, Integer seatNumber, String sectionName) {
        Ticket ticket = new Ticket(user, from, to, seatNumber, sectionName);
        ticketMap.put(user.getEmail(), ticket);
        sections.allocateSeat(seatNumber, user, sectionName);
        return ticket;
    }

    public Optional<Ticket> getTicket(String userEmail) {
        return Optional.ofNullable(ticketMap.get(userEmail));
    }

    public Map<Integer, Passenger> getAllSeatAllocationsBySection(String sectionName) {
        return sections.getSeatAllocationsBySection(sectionName);
    }

    public Optional<Passenger> removeUser(String userEmail) {
        Ticket ticket = ticketMap.remove(userEmail);
        if (ticket != null) {
            return Optional.ofNullable(sections.removeUser(ticket.getTrainSection(), ticket.getSeatNumber()));
        }
        return Optional.empty();
    }

    public boolean updateSeatNumber(String userEmail, Integer newSeatNumber) {
        Ticket ticket = ticketMap.get(userEmail);
        if (ticket != null) {
            ticketMap.remove(userEmail);
            ticket.setSeatNumber(newSeatNumber);
            ticketMap.put(ticket.getPassenger().getEmail(), ticket);
            return sections.updateUserSeat(ticket.getTrainSection(), ticket.getSeatNumber(), newSeatNumber);
        }
        return false;
    }



}

