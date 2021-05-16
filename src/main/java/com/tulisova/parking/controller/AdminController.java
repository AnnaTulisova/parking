package com.tulisova.parking.controller;

import com.tulisova.parking.dao.extra.*;
import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import com.tulisova.parking.service.impl.*;
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
import java.time.*;
import java.util.*;
import java.util.stream.*;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ReservationService reservationService;
    private final JasperReportService jasperReportService;

    @GetMapping("/reservation-list-admin")
    public String showReservationList(WebRequest request, Model model) {
        Collection<ReservationExtra> reservations = reservationService.findAll().stream()
                .map(ReservationExtra::new)
                .collect(Collectors.toList());;
        model.addAttribute("reservations", reservations);
        return "reservation-list-admin";
    }

    @GetMapping("/reservation-remove/{reservationId}")
    public String prepareReservationToDelete(@PathVariable("reservationId") Long reservationId, WebRequest request, Model model) {
        Reservation reservation = reservationService.findById(reservationId);
        model.addAttribute("reservation", new ReservationExtra(reservation));
        return "reservation-remove";
    }

    @PostMapping("/reservation-remove")
    public ModelAndView removeReservation(@ModelAttribute("reservationId") @Valid Long reservationId,
                                          final HttpServletRequest request,
                                          final Errors errors) {
        reservationService.deleteByReservationId(reservationId);
        Collection<ReservationExtra> reservations = reservationService
                .findAll()
                .stream()
                .map(ReservationExtra::new)
                .collect(Collectors.toList());
        return new ModelAndView("reservation-list-admin", "reservations", reservations);
    }

    @GetMapping("/reservation-filter")
    public String filterReservationList(@RequestParam("startDate") String startDate,
                                        WebRequest request, Model model)
    {
        Collection<ReservationExtra> reservations = reservationService
                .findAllByStartDateTime(startDate)
                .stream()
                .map(ReservationExtra::new)
                .collect(Collectors.toList());
        model.addAttribute("reservations", reservations).addAttribute("startDate", startDate).addAttribute("filtered", true);
        return "reservation-list-admin";
    }

    @GetMapping("/pdf-list")
    public void getPdf(@RequestParam(value = "filtered", required = false) String filteredDate, HttpServletResponse response, Model model) throws IOException, JRException {
        jasperReportService.exportReport(filteredDate, response);
    }
}
