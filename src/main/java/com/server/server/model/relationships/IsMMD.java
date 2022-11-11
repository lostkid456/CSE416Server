package com.server.server.model.relationships;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.server.server.model.DistrictPlan;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class IsMMD {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int isMMD;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "isMMD")
    @JsonBackReference
    private DistrictPlan districtPlan;
}
