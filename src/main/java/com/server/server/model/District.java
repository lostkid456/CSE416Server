package com.server.server.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "Districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="district_id")
    private List<DistrictDemographic> districtDemographics;
}
