package com.tulisova.parking.dao.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Data
@Accessors(chain = true)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime endDateTime;

    private String carNumber;

    private Double coast;

    @OneToOne
    private User user;

    @OneToOne
    private Location location;

    @OneToOne
    private Place place;
}
