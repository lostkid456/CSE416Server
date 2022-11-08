package com.server.server.model.geojson;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.List;

@Data
public class Coordinate {
    private List<Double> coordinates;
}
