package com.tulisova.parking.dao.model;

import lombok.*;
import lombok.experimental.*;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String name;

    @OneToOne
    private Location location;
}
