package com.tulisova.parking.controller;

import com.tulisova.parking.dao.model.dto.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

import javax.servlet.http.*;
import javax.validation.*;

@RestController
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    private UserService userService;
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("hello, %s", name);
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, HttpServletRequest request, Errors errors) {
        /*try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            uaeEx(userDto.getEmail());
            return uaeEx;
        }*/
        return "canceled";

    }

}