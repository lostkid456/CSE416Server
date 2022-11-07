package com.server.server.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="State")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String state;
//    private Demographic demographic;
    private int number_of_seats;
//    private Ensemble ensemble;
}
