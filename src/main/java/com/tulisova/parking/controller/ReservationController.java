package com.tulisova.parking.controller;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.model.dto.*;
import com.tulisova.parking.dao.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;
import javax.validation.*;

@RestController
public class ReservationController {

    /*@Autowired
    ReservationRepository reservationRepository;
     */


    @GetMapping("/reservation/reservation")
    public String showReservationForm(WebRequest request, Model model) {
        ReservationDto reservationDto = new ReservationDto();
        model.addAttribute("reservation", reservationDto);
        return "reservation";
    }

    @PostMapping("/reservation/reservation")
    public ModelAndView addReservation(@ModelAttribute("reservation") @Valid ReservationDto reservationDto, HttpServletRequest request, Errors errors) {
        /*try {
            User registered = reservationRepository.registerNewUserAccount(reser);
        } catch (UserAlreadyExistException uaeEx) {
            uaeEx(reser.getEmail());
            return uaeEx;
        }
*/
        return new ModelAndView();
    }

    @GetMapping("/reservation/reservationResult")
    public String getReservationResult(WebRequest request, Model model) {
        ReservationDto reservationDto = new ReservationDto();
        //Reservation dbReservation = reservationRepository.findById();
        model.addAttribute("user", reservationDto);
        return "reservationResult";
    }
}
