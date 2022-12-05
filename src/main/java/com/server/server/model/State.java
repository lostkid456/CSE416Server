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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String state;

//    private String planGeoJsonPath;

    private int numberOfSeats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ensemble_id",referencedColumnName = "id")
    private Ensemble ensemble;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stateDemographic_id",referencedColumnName = "id")
    private StateDemographic stateDemographic;
}
