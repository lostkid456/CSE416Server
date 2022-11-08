package com.server.server.model.geojson;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

@Data
@TypeDef(name="json",typeClass = JsonType.class)
public class Geometry {
    private String type;
    @Type(type = "json")
    private JsonNode coordinates;
}
