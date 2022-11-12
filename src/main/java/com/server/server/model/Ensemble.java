package com.server.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Ensemble{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NonNull
    private String planGeoJsonPath;

    @Transient
    private Map<String,Object> currentDistrictPlan;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ensemble_id")
    @ToString.Exclude
    private List<DistrictPlan> districtPlans;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ensemble_id")
    @ToString.Exclude
    private List<BoxAndWhisker> boxAndWhiskers;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ensemble_id")
    @ToString.Exclude
    private List<RepDemSplit> repDemSplits;
}
