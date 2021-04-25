package com.tulisova.parking.controller;

import com.tulisova.parking.service.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class ReservationController {

//    @Autowired
//    ReservationRepository reservationRepository;


    @GetMapping("/reservation/reservation")
    public String showReservationForm(WebRequest request, Model model) {
        ReservationDto reservationDto = new ReservationDto();
        model.addAttribute("reservation", reservationDto);
        return "reservation";
    }

//    @PostMapping("/reservation/reservation")
//    public ModelAndView addReservation(@ModelAttribute("reservation") @Valid ReservationDto reservationDto, HttpServletRequest request, Errors errors) {
//        try {
//            User registered = reservationRepository.registerNewUserAccount(reser);
//        } catch (UserAlreadyExistException uaeEx) {
//            uaeEx(reser.getEmail());
//            return uaeEx;
//        }
//
//    }

//    @GetMapping("/reservation/reservationResult")
//    public String getReservationResult(WebRequest request, Model model) {
//        ReservationDto reservationDto = new ReservationDto();
//        Reservation dbReservation = reservationRepository.findById();
//        model.addAttribute("user", reservationDto);
//        return "reservationResult";
//    }
}
