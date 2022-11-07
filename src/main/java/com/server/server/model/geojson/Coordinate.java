package com.server.server.model.geojson;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Coordinate {
    private double latitude;
    private double longitude;
}
