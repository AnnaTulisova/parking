package com.tulisova.parking.service.dto;

import com.tulisova.parking.dao.model.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import java.time.*;
@Data
@Accessors(chain = true)
public class ReservationDto {

    private String carNumber;

    @DateTimeFormat(pattern = "yy-MM-dd HH:mm")
    private String startDateTime;

    @DateTimeFormat(pattern = "yy-MM-dd HH:mm")
    private String endDateTime;

    private Location location;

    private Place place;

}
