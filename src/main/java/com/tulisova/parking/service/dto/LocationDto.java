package com.tulisova.parking.service.dto;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.impl.*;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LocationDto {

    private long id;

    private String address;

    private Double tenMinuteCoast;

    private String places;

    public LocationDto(Location dbLocation, String places) {

        this.id = dbLocation.getId();
        this.address = dbLocation.getAddress();
        this.tenMinuteCoast = dbLocation.getTenMinuteCoast();
        this.places = places;
    }
}
