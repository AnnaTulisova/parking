package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.extra.*;
import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
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

    @Override
    public Collection<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.getById(id);
    }

    @Override
    public Collection<Reservation> findAllByUserId(Long userId) { return reservationRepository.findAllByUserId(userId); }

    @Override
    public void deleteByReservationId(Long id)
    {
        reservationRepository.deleteById(id);
    }

    @Override
    public Collection<Reservation> findAllByStartDateTime(String startDate)
    {
        LocalDate startDateDate = LocalDate.parse(startDate);
        LocalDateTime startDateTotal = startDateDate.atStartOfDay();
        LocalDateTime endDateTotal = startDateDate.atTime(23, 59, 59);
        return reservationRepository.findAllByStartDateTime(startDateTotal, endDateTotal);
    }

}
