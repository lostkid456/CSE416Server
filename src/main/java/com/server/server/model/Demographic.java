package com.server.server.model;

import com.server.server.model.enums.InterestType;

import javax.persistence.*;

@Entity
@Table(name="Demographics")
public class Demographic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demo_gen")
    @SequenceGenerator(name = "demo_gen",sequenceName = "demo_seq")
    private int id;

    private int population;

    @Enumerated(EnumType.ORDINAL)
    private InterestType type;
}
