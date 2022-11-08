package com.server.server.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="State")
public class State {
    @Id
    private String state;
    private int number_of_seats;
    @OneToOne
    private Ensemble ensemble;
}
