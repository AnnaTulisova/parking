package com.tulisova.parking.dao.extra;

import com.tulisova.parking.dao.model.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.format.*;

@Data
@Accessors(chain = true)
public class ReservationExtra {
    private Long id;
    private String startDateTime;
    private String endDateTime;
    private String carNumber;
    private User user;
    private Location location;
    private Place place;

    public ReservationExtra(Reservation reservation)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        this.id = reservation.getId();
        this.carNumber = reservation.getCarNumber();
        this.location = reservation.getLocation();
        this.place = reservation.getPlace();
        this.user = reservation.getUser();
        this.startDateTime = formatter.format(reservation.getStartDateTime());
        this.endDateTime = formatter.format(reservation.getEndDateTime());
    }
}

