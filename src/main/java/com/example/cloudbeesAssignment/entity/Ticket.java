package com.example.cloudbeesAssignment.entity;



import lombok.Getter;

import lombok.Setter;



@Setter
@Getter

public class Ticket {
    private Passenger passenger;
    private String from;
    private String to;

    private Integer seatNumber;

    private String TrainSection;
    private  Integer cost;

    public Ticket() {
        cost=20;
    }

    public Ticket(Passenger passenger, String from, String to, Integer seatNumber, String trainSection) {
        this.passenger = passenger;
        this.from = from;
        this.to = to;
        this.seatNumber = seatNumber;
        TrainSection = trainSection;
        this.cost = 20;
    }
}