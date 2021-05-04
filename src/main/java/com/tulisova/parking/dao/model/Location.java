package com.tulisova.parking.dao.model;

import lombok.*;
import lombok.experimental.*;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;
}
