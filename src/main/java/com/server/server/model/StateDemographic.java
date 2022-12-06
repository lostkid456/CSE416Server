package com.server.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.server.model.enums.InterestType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private InterestType type;

    private int population;
}
