package com.tulisova.parking.dao.extra;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.dto.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.web.multipart.*;

import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.util.*;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LocationResult {

    private long id;

    private String address;

    private Double tenMinuteCoast;

    private String places;

    private String electroPlaces;

    private String picture;

    public LocationResult(LocationDto locationDto) throws IOException {

        this.id = locationDto.getId();
        this.address = locationDto.getAddress();
        this.tenMinuteCoast = locationDto.getTenMinuteCoast();
        this.places = locationDto.getPlaces();
        this.electroPlaces = locationDto.getElectroPlaces();
        if(locationDto.getPicture() != null)
        {
            this.picture = Base64.getEncoder().encodeToString(locationDto.getPicture().getBytes());
        }
    }
}
