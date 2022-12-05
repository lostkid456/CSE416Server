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
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="districtDemographic_id",referencedColumnName = "id")
    private DistrictDemographic districtDemographic;
}
