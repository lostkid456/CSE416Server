package com.server.server.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "States")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_gen")
    @SequenceGenerator(name="state_gen",sequenceName = "state_seq")
    private int id;

    @NonNull
    private String state;

    @NonNull
    private String planGeoJsonPath;

    private int numberOfSeats;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "ensemble_id",referencedColumnName = "id")
    private Ensemble ensemble;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="stateDemographic_id",referencedColumnName = "id")
    private StateDemographic stateDemographic;
}
