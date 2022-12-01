package com.server.server.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "StateDemographics")
public class StateDemographic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "state_demo_gen")
    @SequenceGenerator(name = "state_demo_gen",sequenceName = "state_demo_sequence")
    private int id;

    @OneToMany
    private List<Demographic> demographics;
}
