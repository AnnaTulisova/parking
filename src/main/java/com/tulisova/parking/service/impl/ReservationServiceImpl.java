package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final LocationService locationService;
    private final PlaceService placeService;
    private final UserService userService;

    @Override
    public Reservation createReservation(ReservationDto reservationDto) {

        User currentUser = userService.getCurrentUser();

        Reservation toDBReservation = new Reservation().setCarNumber(reservationDto.getCarNumber())
                .setLocation(reservationDto.getLocation())
                .setPlace(reservationDto.getPlace())
                .setStartDateTime(LocalDateTime.parse(reservationDto.getStartDateTime()))
                .setEndDateTime(LocalDateTime.parse(reservationDto.getEndDateTime()))
                .setUser(currentUser);

        reservationRepository.save(toDBReservation);
        return toDBReservation;
    }

//    @Override
//    public Collection<Place> findFreePlaces(ReservationDto reservationDto) {
//        Collection<Location> locations;
//        Collection<Place> places;
//        if(reservationDto.getStartDateTime().isEmpty() && reservationDto.getEndDateTime().isEmpty())
//        {
//            locations = locationService.findAll();
//            places = placeService.findAll();
//        }
//        else
//        {
//
//            Collection<Reservation> reservations = findAllByStartDateTime(LocalDateTime.parse(reservationDto.getStartDateTime()));
//            for(Reservation reservation: reservations)
//            {
//                Location reservedLocation = reservation.getLocation();
//                Collection<Place> n
//            }
//
//            List<Location> reservedLocations = reservations.stream().map(it -> it.getLocation()).collect(Collectors.toList());
//            List<Place> reservedPlaces = reservations.stream().map(it -> it.getPlace()).collect(Collectors.toList());
//            locations = locationService.findAllWhereLocationIdNotIn(reservedLocations);
//
//        }
//    }

//    @Override
//    public Collection<Location> findFreeLocations(ReservationDto reservationDto) {
//        return null;
//    }

    @Override
    public Collection<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    /*@Override
    public Collection<Reservation> findAllByStartDateTime(LocalDateTime startDateTime) {
        return reservationRepository.findAllByStartDateTime(startDateTime);
    }*/

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.getById(id);
    }
}
