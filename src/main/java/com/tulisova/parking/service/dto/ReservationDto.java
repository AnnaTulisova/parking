package com.tulisova.parking.service.dto;

import com.tulisova.parking.dao.model.*;
import lombok.*;
import lombok.experimental.*;

import javax.validation.constraints.*;
@Data
@Accessors(chain = true)
public class ReservationDto {

    @NotNull @NotEmpty(message = "Заполните поле!")
    @Pattern(regexp = "^[А-Я]{1}[0-9]{3}[А-Я]{2}[0-9]{2,}$",
            message = "Номер автомобиля должен иметь следующий формат XYYYXXZZZ, где X - буквы номера, Y - цифры, а Z - код региона")
    private String carNumber;

    @NotNull @NotEmpty(message = "Заполните поле!")
    private String startDateTime;

    @NotNull @NotEmpty(message = "Заполните поле!")
    private String endDateTime;

    @NotNull(message = "Необходимо выбрать расположение для парковки")
    private Location location;

    @NotNull(message = "Необходимо выбрать место для парковки!")
    private Place place;

}
