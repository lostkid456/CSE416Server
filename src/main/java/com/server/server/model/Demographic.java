package com.server.server.model;

import com.server.server.model.enums.InterestType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="Demographics")
public class Demographic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int population;

    @Enumerated(EnumType.STRING)
    private InterestType type;
}
