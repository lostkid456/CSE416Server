package com.server.server.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class State {
    @Id
    @NonNull
    private String state;
    private int numberOfSeats;

    @OneToOne(cascade = CascadeType.ALL)
    private Ensemble stateEnsemble;

//    @OneToOne
//    private Demographic demographic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        State state1 = (State) o;
        return Objects.equals(state, state1.state);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
