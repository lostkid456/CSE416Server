package com.server.server.model;

import com.server.server.model.map.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Ensemble{
    @Id
    @NonNull
    private String state;

    @OneToOne(cascade = CascadeType.ALL)
    private Map currentDistrict;
}
