package com.server.server.model;

import com.server.server.model.map.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Ensemble {
    @Id
    @NonNull
    @JoinColumn(name="state_id",referencedColumnName = "id")
    private String id;
    @OneToOne
    @JoinColumn(name="map_id",referencedColumnName = "id")
    Map currentDistrictPlan;
}
