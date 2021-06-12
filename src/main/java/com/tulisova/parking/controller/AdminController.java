package com.tulisova.parking.controller;

import com.tulisova.parking.dao.extra.*;
import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import com.tulisova.parking.service.impl.*;
import com.tulisova.parking.service.pdf.*;
import lombok.*;
import net.sf.jasperreports.engine.*;
import org.springframework.scheduling.support.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;
import javax.swing.*;
import javax.validation.*;
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ReservationService reservationService;
    private final LocationService locationService;
    private final PlaceService placeService;
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

    @PostMapping("/reservation-filter")
    public ModelAndView filterReservationList(@ModelAttribute("startDate") String startDate,
                                        WebRequest request)
    {
        Collection<ReservationExtra> reservations = reservationService
                .findAllByStartDateTime(startDate)
                .stream()
                .map(ReservationExtra::new)
                .collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView("reservation-list-admin");
        modelAndView.addObject("reservations", reservations).addObject("startDate", startDate).addObject("filtered", true);
        return modelAndView;
    }

    @GetMapping("/pdf-list")
    public void getPdf(@RequestParam(value = "filtered", required = false) String filteredDate, HttpServletResponse response, Model model) throws IOException, JRException {
        jasperReportService.exportReport(filteredDate, response);
    }

    @GetMapping("/location")
    public String getLocationAdd(WebRequest request, Model model) {
        LocationDto locationDto = new LocationDto();
        model.addAttribute("location", locationDto);
        return "location";
    }

    @PostMapping("/location-add")
    public ModelAndView addLocation(@ModelAttribute("location") @Valid  final LocationDto locationDto,
                                    final BindingResult bindingResult,
                                    final HttpServletRequest request,
                                    final Errors errors) throws IOException {
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("location", "errors", errors);
            model.addObject("location", locationDto);
            return model;
        }
        Location addedLocation = locationService.createLocation(locationDto);
        locationDto.setId(addedLocation.getId());
        locationDto.setPictureBase64(Base64.getEncoder().encodeToString(locationDto.getPicture().getBytes()));
        return new ModelAndView("location-result", "location", locationDto);
    }

    @GetMapping("/location-list-admin")
    public String showLocationList(WebRequest request, Model model) {
        Collection<LocationDto> locations = locationService.findAllLocations();
        model.addAttribute("locations", locations);
        return "location-list-admin";
    }

    @PostMapping("/location-filter")
    public ModelAndView filterLocationList(@ModelAttribute("address") String address,
                                              WebRequest request) {
        Collection<LocationDto> locations = locationService.findAllByAddress(address);
        ModelAndView modelAndView = new ModelAndView("location-list-admin");
        modelAndView.addObject("locations", locations)
                .addObject("address", address)
                .addObject("filtered", true);
        return modelAndView;
    }

    @GetMapping("/location-remove/{locationId}")
    public String prepareLocationToDelete(@PathVariable("locationId") Long locationId, WebRequest request, Model model) {
        Location location = locationService.findById(locationId);
        model.addAttribute("location",
                new LocationDto(location, placeService.findPlacesNameByLocation(location),
                        placeService.findElectroPlacesNameByLocation(location)));
        return "location-remove";
    }

    @PostMapping("/location-remove")
    public ModelAndView removeLocation(@ModelAttribute("locationId") @Valid Long locationId,
                                          final HttpServletRequest request,
                                          final Errors errors) {
        locationService.deleteByLocationId(locationId);
        Collection<LocationDto> locations = locationService.findAllLocations();
        return new ModelAndView("location-list-admin", "locations", locations);
    }

    @GetMapping("/location-edit/{locationId}")
    public String prepareLocationToEdit(@PathVariable("locationId") Long locationId, WebRequest request, Model model) throws IOException {
        Location location = locationService.findById(locationId);
        LocationDto locationDto = new LocationDto(location,
                placeService.findPlacesNameByLocation(location),
                placeService.findElectroPlacesNameByLocation(location));
        model.addAttribute("location", locationDto);
        return "location-edit";
    }

    @PostMapping("/location-edit")
    public ModelAndView editLocation(@ModelAttribute("location") @Valid  final LocationDto locationDto,
                                       BindingResult bindingResult,
                                       final HttpServletRequest request,
                                       final Errors errors) throws IOException {
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("location-edit", "errors", errors);
            model.addObject("location", locationDto);
            return model;
        }
        locationService.editLocation(locationDto);
        Collection<LocationDto> locations = locationService.findAllLocations();
        return new ModelAndView("location-list-admin", "locations", locations);
    }

    @GetMapping("/location-about/{locationId}")
    public String prepareLocationPlaces(@PathVariable("locationId") Long locationId, WebRequest request, Model model) {
        model.addAttribute("places", placeService.findAllByLocationId(locationId));
        model.addAttribute("locationId", locationId);
        return "location-about";
    }

    @GetMapping("/place-remove/{placeId}")
    public String preparePlaceToDelete(@PathVariable("placeId") Long placeId, WebRequest request, Model model) {
        Place place = placeService.findById(placeId);
        model.addAttribute("place", place);
        return "place-remove";
    }

    @PostMapping("/place-remove")
    public ModelAndView deletePlace(@ModelAttribute("placeId") @Valid Long placeId, WebRequest request, Model model) {
        Long locationId = placeService.deleteById(placeId);
        return  new ModelAndView("location-about")
                .addObject("places", placeService.findAllByLocationId(locationId));
    }
    @GetMapping("/place-edit/{placeId}")
    public String preparePlaceToEdit(@PathVariable("placeId") Long placeId, WebRequest request, Model model) {
        Place place = placeService.findById(placeId);
        model.addAttribute("place", place);
        return "place-edit";
    }

    @PostMapping("/place-edit")
    public String editPlace(@ModelAttribute("place") @Valid final Place place, WebRequest request, Model model) {
        Place dbPlace = placeService.editPlace(place);
        model.addAttribute("places", placeService.findAllByLocationId(dbPlace.getLocation().getId()));
        model.addAttribute("locationId", dbPlace.getLocation().getId());
        return "location-about";
    }

    @GetMapping("/place-add/{locationId}")
    public String preparePlaceToAdd(@PathVariable("locationId") Long locationId, WebRequest request, Model model) {
        model.addAttribute("place", new PlaceDto().setLocationId(locationId.toString()));
        model.addAttribute("locationId", locationId);
        return "place";
    }

    @PostMapping("/place-add")
    public String addPlace(@ModelAttribute("place") @Valid final PlaceDto place,
                           WebRequest request, Model model) {
        Location location = locationService.findById(Long.parseLong(place.getLocationId()));
        Place dbPlace = placeService.addPlace(place, location);
        model.addAttribute("places", placeService.findAllByLocationId(dbPlace.getLocation().getId()));
        model.addAttribute("locationId", dbPlace.getLocation().getId());
        return "location-about";
    }

}
