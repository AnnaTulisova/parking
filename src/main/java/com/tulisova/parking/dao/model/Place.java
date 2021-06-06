package com.tulisova.parking.dao.model;

import com.fasterxml.jackson.annotation.*;
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

    @JsonIgnore
    private Boolean deleted;

    @JsonIgnore
    @ManyToOne (optional=false)
    @JoinColumn (name="location_id")
    private Location location;
}
