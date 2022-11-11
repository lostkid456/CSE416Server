package com.server.server.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NonNull
    private String state;

    private int numberOfSeats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ensemble_id",referencedColumnName = "id")
    private Ensemble ensemble;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stateDemographic_id",referencedColumnName = "id")
    private StateDemographic stateDemographic;
}
