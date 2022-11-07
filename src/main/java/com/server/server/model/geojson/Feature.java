package com.server.server.model.geojson;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Embeddable
public class Feature {
    private String type;
    @Embedded
    private Properties properties;
    @Embedded
    private Geometry geometry;
}
