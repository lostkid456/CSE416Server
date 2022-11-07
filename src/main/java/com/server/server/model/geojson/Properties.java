package com.server.server.model.geojson;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Properties {
    private String type;
}
