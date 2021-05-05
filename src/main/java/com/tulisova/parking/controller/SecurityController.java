package com.tulisova.parking.controller;

import com.tulisova.parking.dao.model.User;
import com.tulisova.parking.service.UserService;
import com.tulisova.parking.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tulisova.parking.service.exception.UserAlreadyExistException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "security/registration";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid final UserDto userDto,
            final HttpServletRequest request,
            final Errors errors
    ) {

        try {
            User registered = userService.registerNewUser(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("security/registration", "user", userDto);
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("security/successRegister", "user", userDto);
    }
}
