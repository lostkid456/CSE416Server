package com.server.server.model.geojson;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@TypeDef(name="json",typeClass = JsonType.class)
public class Feature {
    private String type;
    @Type(type="json")
    private Properties properties;
    @Type(type = "json")
    private Geometry geometry;
}
