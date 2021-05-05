package com.tulisova.parking.controller;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import com.tulisova.parking.service.pdf.*;
import lombok.*;
import net.sf.jasperreports.engine.*;
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
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final LocationService locationService;
    private final PlaceService placeService;
    private final JasperReportService jasperReportService;

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

    @PostMapping("/reservation/reservation")
    public ModelAndView addReservation(@ModelAttribute("reservation") @Valid  final ReservationDto reservationDto, final HttpServletRequest request, final Errors errors) {
        Reservation reservation = reservationService.createReservation(reservationDto);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
        reservationDto.setStartDateTime(formatter.format(reservation.getStartDateTime()));
        reservationDto.setEndDateTime(formatter.format(reservation.getEndDateTime()));

        ModelAndView reservationResultView = new ModelAndView("reservationResult", "reservation", reservationDto);
        reservationResultView.addObject("user", reservation.getUser());
        reservationResultView.addObject("reservationId", reservation.getId());

        return reservationResultView;
    }

    @GetMapping("/pdf")
    public void getPdf(@RequestParam("reservationId") Long reservationId, HttpServletResponse response, Model model) throws IOException, JRException {
        //return jasperReportService.exportReport(Long.parseLong(reservationId));
        jasperReportService.exportReport(reservationId, response);
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
}
