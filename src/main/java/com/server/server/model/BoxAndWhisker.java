package com.server.server.model;

import com.server.server.model.enums.InterestType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
//@ToString
@Entity
@Table(name = "BoxAndWhiskers")
public class BoxAndWhisker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double min;
    private double max;
    private double median;
    private double firstQ;
    private double thirdQ;

    @Enumerated(EnumType.STRING)
    private InterestType type;
}
