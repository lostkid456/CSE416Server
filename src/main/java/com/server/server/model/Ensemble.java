package com.server.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "Ensembles")
public class Ensemble{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;



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
