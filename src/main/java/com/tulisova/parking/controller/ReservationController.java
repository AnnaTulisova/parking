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
import java.util.stream.*;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final LocationService locationService;
    private final PlaceService placeService;
    private final JasperReportService jasperReportService;

    @GetMapping("/reservation")
    public String showReservationForm(WebRequest request, Model model) {
        ReservationDto reservationDto = new ReservationDto();
        model.addAttribute("reservation", reservationDto);
        Collection<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "reservation";
    }

    @RequestMapping(value="/findPlace", method=RequestMethod.GET,produces = "application/json")
    public @ResponseBody Map<String, Object> findPlaces(@RequestParam("carNumber") String carNumber,
                                                      @RequestParam("startDateTime") String startDateTime,
                                                      @RequestParam("endDateTime") String endDateTime,
                                                      @RequestParam("location") Long locationId,
                                                      @RequestParam("place") Long placeId,
                                                      @RequestParam(value = "forElectroCar", defaultValue = "false", required = false) Boolean forElectroCar) {
        Map<String, Object> result = new HashMap<>();
        result.put("places", placeService.findFreePlaces(startDateTime, locationId, forElectroCar));
        result.put("locationImg", "data:image/png;base64," +Base64.getEncoder().encodeToString(locationService.findById(locationId).getPicture()));
        return result;
    }


    @PostMapping("/reservation/reservation")
    public ModelAndView addReservation(@ModelAttribute("reservation") @Valid  final ReservationDto reservationDto,
                                       final BindingResult bindingResult,
                                       final HttpServletRequest request,
                                       final Errors errors) {
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("reservation", "errors", errors);
            model.addObject("reservation", reservationDto);
            Collection<Location> locations = locationService.findAll();
            model.addObject("locations", locations);
            return model;
        }

        Reservation reservation = reservationService.createReservation(reservationDto);
        ReservationExtra reservationForView = new ReservationExtra(reservation);
        return new ModelAndView("reservation-result", "reservation", reservationForView);
    }

    @GetMapping("/pdf/{reservationId}")
    public @ResponseBody void getPdf(@PathVariable("reservationId") Long reservationId, HttpServletResponse response, Model model) throws IOException, JRException {
        jasperReportService.exportReport(reservationId, response);
    }

    @GetMapping("/reservation-list")
    public String getUserReservations(WebRequest request, Model model) {
        User currentUser = userService.getCurrentUser();
        Collection<ReservationExtra> reservations = reservationService
                .findAllByUserId(currentUser.getId())
                .stream().map(ReservationExtra::new)
                .collect(Collectors.toList());
        model.addAttribute("reservations", reservations);
        return "reservation-list";
    }
}
