package com.server.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="state_id")
    private List<Ensemble> ensembles;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="state_id")
    private List<StateDemographic> stateDemographics;
}
