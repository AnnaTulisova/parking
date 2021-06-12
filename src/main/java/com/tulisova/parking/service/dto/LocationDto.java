package com.tulisova.parking.service.dto;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.impl.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.web.multipart.*;
import org.springframework.web.multipart.commons.*;

import javax.validation.constraints.*;
import java.util.Base64;
import java.util.prefs.*;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LocationDto {

    private long id;
    @NotNull
    @NotEmpty(message = "Заполните поле!")
    private String address;
    @NotNull(message = "Заполните поле!")
    private Double tenMinuteCoast;
    private String places;

    private String electroPlaces;

    private MultipartFile picture;

    private String pictureBase64;

    public LocationDto(Location dbLocation, String places, String electroPlaces) {
        this.id = dbLocation.getId();
        this.address = dbLocation.getAddress();
        this.tenMinuteCoast = dbLocation.getTenMinuteCoast();
        this.places = places;
        this.electroPlaces = electroPlaces;
        if(dbLocation.getPicture() != null) {
            this.pictureBase64 = Base64.getEncoder().encodeToString(dbLocation.getPicture());
        }
    }
}
