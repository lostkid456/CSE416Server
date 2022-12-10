package com.server.server.model;

import com.server.server.model.enums.InterestType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Representatives")
public class Representative {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;
    @Enumerated(EnumType.STRING)
    private InterestType party;
}
