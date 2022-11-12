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
@ToString
@RequiredArgsConstructor
@Entity
public class RepDemSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String split;
    private int numberOfPlans;
}
