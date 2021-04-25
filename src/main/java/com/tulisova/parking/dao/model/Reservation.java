package com.tulisova.parking.dao.model;

import lombok.*;
import org.springframework.format.annotation.*;

import javax.persistence.*;
import java.time.*;

@Getter @Setter @NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime startDateTime;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime endDateTime;
    private String carNumber;

    @OneToOne
    private User user;

    @OneToOne
    private Location location;

    public Reservation(Integer id, LocalDateTime startDateTime, LocalDateTime endDateTime, String carNumber, User user, Location location) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.user = user;
        this.carNumber = carNumber;
        this.location = location;
    }
}
