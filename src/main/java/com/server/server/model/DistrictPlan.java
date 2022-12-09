package com.server.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "DistrictPlans")
public class DistrictPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isMMD;

    private String pattern;

    private String planType;

    private int numberOfDemocrat;

    private int numberOfRepublican;

    private int numberOfMajorityMinority;

    private String split;

    private String districtBoundaryPath;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "district_plan_id")
    private List<District> districts;
}
