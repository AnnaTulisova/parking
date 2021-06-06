package com.tulisova.parking.dao.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.boot.context.properties.bind.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Accessors(chain = true)
//@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;

    private Double tenMinuteCoast;

    @Lob
    private byte[] picture;

    @JsonIgnore
    private Boolean deleted;

    @JsonIgnore
    @OneToMany (mappedBy="location", fetch=FetchType.LAZY)
    private Collection<Place> places;
}
