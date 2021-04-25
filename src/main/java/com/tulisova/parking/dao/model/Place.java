package com.tulisova.parking.dao.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter @NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String name;

    @OneToOne
    private Location location;
}
