package com.server.server.model.geojson;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

@Data
@Embeddable
public class Geometry {
    private String type;
    @Embedded
    private List<Coordinate> coordinates;
}
