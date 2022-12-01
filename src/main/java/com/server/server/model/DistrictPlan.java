package com.server.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "DistrictPlans")
public class DistrictPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "districtPlan_gen")
    @SequenceGenerator(name = "districtPlan_gen",sequenceName = "districtPlan_seq")
    private int id;

    private boolean isMMD;

    private int numberOfDemocrats;

    private int numberOfRepublicans;

    private int numberOfMajorityMinority;

    private String districtBoundaryPath;
}
