package com.server.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ensembles")
public class Ensemble{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ensemble_gen")
    @SequenceGenerator(name="ensemble_gen",sequenceName = "ensemble_seq")
    private int id;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ensemble_id")
    private List<DistrictPlan> districtPlans;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ensemble_id")
    private List<BoxAndWhisker> boxAndWhiskers;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ensemble_id")
    private List<RepDemSplit> repDemSplits;
}
