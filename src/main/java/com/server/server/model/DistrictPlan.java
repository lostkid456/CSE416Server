package com.server.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.server.server.model.relationships.IsMMD;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class DistrictPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int numberOfDemocrats;

    private int numberOfRepublicans;

    private int numberOfMajorityMinority;

    private String districtBoundary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="isMMD_id",referencedColumnName = "id")
    @JsonManagedReference
    private IsMMD isMMD;
}
