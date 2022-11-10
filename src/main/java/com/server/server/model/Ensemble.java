package com.server.server.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
public class Ensemble{
    @Id
    @NonNull
    private String state;

    @Transient
    private Map<String,Object> currentDistrictPlan;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ensemble ensemble = (Ensemble) o;
        return Objects.equals(state, ensemble.state);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
