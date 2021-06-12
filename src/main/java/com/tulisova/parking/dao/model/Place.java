package com.tulisova.parking.dao.model;

import com.fasterxml.jackson.annotation.*;
import com.tulisova.parking.service.dto.*;
import lombok.*;
import lombok.experimental.*;

import javax.persistence.*;
import java.util.*;

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

    private Boolean forElectroCars;

    public Place setForElectroCars(Collection<String> electroPlaces) {
        if(electroPlaces.contains(this.getName())){
            this.forElectroCars = true;
        } else {
            this.forElectroCars = false;
        }
        return this;
    }
    public Place setForElectroCars(Boolean value) {
        this.forElectroCars = value;
        return this;
    }


    @JsonIgnore
    @ManyToOne (optional=false)
    @JoinColumn (name="location_id")
    private Location location;
}
