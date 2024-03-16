package com.example.cloudbeesAssignment.controller;

import com.example.cloudbeesAssignment.entity.Passenger;
import com.example.cloudbeesAssignment.entity.Ticket;
import com.example.cloudbeesAssignment.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/train")
public class TrainController {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("/purchaseTicket")
    public Ticket purchaseTicket(@RequestParam String from, @RequestParam String to, @RequestBody Passenger user, @RequestParam Integer seatNumber, @RequestParam String sectionName) {
        return trainService.purchaseTicket(from, to, user, seatNumber, sectionName);
    }

    @GetMapping("/getTicket")
    public Optional<Ticket> getTicket(@RequestParam String userEmail){
        return trainService.getTicket(userEmail);
    }

    @GetMapping("/getAllSeatAllocationsBySection")
    public Map<Integer, Passenger> getAllSeatAllocationsBySection(@RequestParam String sectionName) {
        return trainService.getAllSeatAllocationsBySection(sectionName);
    }

    @DeleteMapping("/removeUser")
    public Optional<Passenger> removeUser(@RequestParam String userEmail) {
        return trainService.removeUser(userEmail);
    }

    @PutMapping("/updateSeatNumber")
    public ResponseEntity<String> updateSeatNumber(@RequestParam String userEmail, @RequestParam Integer newSeatNumber) {
        Optional<Ticket> ticketOptional = trainService.getTicket(userEmail);

        if (ticketOptional.isPresent()) {
            boolean updateResult = trainService.updateSeatNumber(userEmail, newSeatNumber);

            if (updateResult) {
                return ResponseEntity.ok("Seat number updated successfully for user: " + userEmail);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Seat number updated successfully for user: " + userEmail);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ticket not found for user: " + userEmail);
        }
    }
}