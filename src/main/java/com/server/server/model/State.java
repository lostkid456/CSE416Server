package com.server.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name="State")
@NoArgsConstructor
@RequiredArgsConstructor
public class State {
    @Id
    @NonNull
    private String state;
    private int number_of_seats;

    @OneToOne(cascade = CascadeType.ALL)
    private Ensemble stateEnsemble;
}
