package com.server.server.model.map;

import com.server.server.model.geojson.Feature;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@TypeDef(name = "json",typeClass = JsonType.class)
@NoArgsConstructor
@RequiredArgsConstructor
public class Map {
    @Id
    @NonNull
    private String id;
    private String type;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Feature> features;
}
