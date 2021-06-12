package com.tulisova.parking.service.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class PlaceDto {
    private long id;

    String name;

    private Boolean forElectroCars;

    private String locationId;

}
