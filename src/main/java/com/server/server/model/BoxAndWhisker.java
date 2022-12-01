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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bw_gen")
    @SequenceGenerator(name="bw_gen",sequenceName = "bw_seq")
    private int id;

    private int min;
    private int max;
    private int median;
    private int firstQ;
    private int thirdQ;

    @Enumerated(EnumType.ORDINAL)
    private InterestType type;
}
