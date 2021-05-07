package com.tulisova.parking.controller;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import com.tulisova.parking.service.pdf.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ReservationService reservationService;
    private final LocationService locationService;
    private final PlaceService placeService;
    private final JasperReportService jasperReportService;

    @GetMapping("/reservation-list-admin")
    public String showReservationList(WebRequest request, Model model) {
        Collection<Reservation> reservations = reservationService.findAll();
        model.addAttribute("reservations", reservations);
        return "reservation-list-admin";
    }
}
