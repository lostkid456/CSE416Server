package com.server.server.model;

import com.server.server.model.relationships.BWType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Getter
@Setter
@ToString
@Entity
public class BoxAndWhisker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int min;
    private int max;
    private int median;
    private int firstQ;
    private int thirdQ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bwType_id",referencedColumnName = "id")
    private BWType bwType;
}
