package com.server.server.model;

import com.server.server.model.map.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Ensemble {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Id
    @NonNull
    private String state;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="map_id",referencedColumnName = "state")
    Map currentDistrictPlan;
}
