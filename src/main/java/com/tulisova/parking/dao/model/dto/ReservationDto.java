package com.tulisova.parking.dao.model.dto;

import javax.validation.constraints.*;
import java.time.*;

import lombok.*;

public class ReservationDto {
    @NotNull
    @NotEmpty
    private Long UserId;

    @NotNull
    @NotEmpty
    private String carNumber;

    @NotNull
    @NotEmpty
    private LocalDateTime startDateTime;

    @NotNull
    @NotEmpty
    private LocalDateTime endDateTime;

    @NotNull
    @NotEmpty
    private Long locationId;

    @NotNull
    @NotEmpty
    private Long placeId;
}
