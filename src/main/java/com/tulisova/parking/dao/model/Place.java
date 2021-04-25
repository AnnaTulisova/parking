package com.tulisova.parking.dao.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter @NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String name;

    @OneToOne
    private Location location;
}
