package com.server.server.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@Entity
public class OpportunityRange {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int opportunityDistrictCount;

    private int planCount;
}
