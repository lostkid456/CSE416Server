package com.server.server.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class StateDemographic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int totalPopulation;
    private int caucasian;
    private int africanAmerican;
    private int asian;
    private int latino;
}
