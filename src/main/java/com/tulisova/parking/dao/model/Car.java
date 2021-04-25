package com.tulisova.parking.dao.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter @Setter @NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String carNumber;

    @ManyToOne
    List<User> users;

    public Car(String id)
    {
        this.id = id;
    }

    public Car(String id, String carNumber)
    {
        this.id = id;
        this.carNumber = carNumber;
    }

    public Car(String id, String carNumber, List<User> users)
    {
        this.id = id;
        this.carNumber = carNumber;
        this.users = users;
    }
}
