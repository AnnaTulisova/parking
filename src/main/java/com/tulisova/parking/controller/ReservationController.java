package com.tulisova.parking.controller;

import com.tulisova.parking.dao.extra.*;
import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import com.tulisova.parking.service.pdf.*;
import lombok.*;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;
import javax.validation.*;
import java.io.*;
import java.util.*;

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
    public ModelAndView addReservation(@ModelAttribute("reservation") @Valid  final ReservationDto reservationDto,
                                       final BindingResult bindingResult,
                                       final HttpServletRequest request,
                                       final Errors errors) {
        if(bindingResult.hasErrors())
        {
            ModelAndView model = new ModelAndView("reservation", "errors", errors);
            model.addObject("reservation", reservationDto);
            Collection<Location> locations = locationService.findAll();
            Collection<Place> places = placeService.findAll();
            model.addObject("locations", locations);
            model.addObject("places", places);
            return model;
        }

        Reservation reservation = reservationService.createReservation(reservationDto);
        ReservationExtra reservationForView = new ReservationExtra(reservation);
        return new ModelAndView("reservationResult", "reservation", reservationForView);
    }

    @GetMapping("/pdf")
    public void getPdf(@RequestParam("reservationId") Long reservationId, HttpServletResponse response, Model model) throws IOException, JRException {
        jasperReportService.exportReport(reservationId, response);
    }
}
