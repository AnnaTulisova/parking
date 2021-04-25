package com.tulisova.parking.controller;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import lombok.*;
import org.springframework.beans.propertyeditors.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;
import javax.validation.*;
import java.text.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final LocationService locationService;
    private final PlaceService placeService;

    @GetMapping("/reservation")
    public String showReservationForm(WebRequest request, Model model) {
        ReservationDto reservationDto = new ReservationDto();
        model.addAttribute("reservation", reservationDto);
        Collection<Location> locations = locationService.findAll();
        Collection<Place> places = placeService.findAll();
        model.addAttribute("locations", locations);
        model.addAttribute("places", places);
        return "reservation";
    }

//    @GetMapping("/reservation/locations")
//    public String getPossibleLocations(WebRequest request, Model model) {
//        ReservationDto reservationDto = new ReservationDto();
//        Collection<Location> locations = reservationService.findFreeLocations(reservationDto);
//        model.addAttribute("locations", locations);
//        //model.addAttribute("places", places);
//        return "reservation";
//    }

    @PostMapping("/reservation/reservation")
    public ModelAndView addReservation(@ModelAttribute("reservation") @Valid  final ReservationDto reservationDto, final HttpServletRequest request, final Errors errors) {
        Reservation reservation = reservationService.createReservation(reservationDto);
        return new ModelAndView("reservationResult", "reservation", reservation);
    }

    /*@PostMapping("/reservation/reservation")
    public ModelAndView addReservation(@ModelAttribute("reservation") @Valid ReservationDto reservationDto, HttpServletRequest request, Errors errors) {
        try {
            User registered = reservationService.registerNewUserAccount(reser);
        } catch (UserAlreadyExistException uaeEx) {
            uaeEx(reser.getEmail());
            return uaeEx;
        }
    }*/

    @GetMapping("/reservation/reservationResult")
    public String getReservationResult(WebRequest request, Model model) {
        ReservationDto reservationDto = new ReservationDto();
        Object test = model;
        /*Reservation dbReservation = reservationService.findById(id);
        model.addAttribute("user", reservationDto);*/
        return "reservationResult";
    }
}
