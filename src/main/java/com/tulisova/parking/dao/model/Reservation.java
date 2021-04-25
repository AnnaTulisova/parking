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
    private LocalDateTime startDatetime;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime endDatetime;

    @OneToOne
    private User user;

    @OneToOne
    private Car car;

    public Reservation(Integer id, LocalDateTime startDatetime, LocalDateTime endDatetime, User user, Car car) {
        this.id = id;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.user = user;
        this.car = car;
    }
}
