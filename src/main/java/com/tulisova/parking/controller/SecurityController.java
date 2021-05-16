package com.tulisova.parking.controller;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.UserService;
import com.tulisova.parking.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tulisova.parking.service.exception.UserAlreadyExistException;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.*;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/contacts")
    public String getContacts(WebRequest request, Model model) {
        User user = userService.getCurrentUser();
        String mainUrl = "/";
        if(user != null)
        {
            Boolean userIsAdmin = user.getRoles().contains(Role.ADMIN);
            if(userIsAdmin)
            {
                mainUrl = "index-admin";
            }
        }
        model.addAttribute("link", mainUrl);
        return "contacts";
    }

    @GetMapping("/about")
    public String about(WebRequest request, Model model) {
        User user = userService.getCurrentUser();
        String mainUrl = "/";
        if(user != null)
        {
            Boolean userIsAdmin = user.getRoles().contains(Role.ADMIN);
            if(userIsAdmin)
            {
                mainUrl = "index-admin";
            }
        }
        model.addAttribute("link", mainUrl);
        return "about";
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
            final BindingResult bindingResult,
            final HttpServletRequest request,
            final Errors errors
    ) {
        if (bindingResult.hasErrors()) {
            Optional<ObjectError> passwordMatchesError = errors.getAllErrors().stream().filter(it ->
                    it.getDefaultMessage().equals("Пароли должны совпадать!")).findFirst();
            if(!passwordMatchesError.isEmpty())
            {
                List<ObjectError> totalErrors = errors.getAllErrors().stream()
                        .filter(fer -> !fer.getDefaultMessage().equals("Пароли должны совпадать!"))
                        .collect(Collectors.toList());

                BeanPropertyBindingResult totalResult = new BeanPropertyBindingResult(userDto, "user");
                for(ObjectError error: totalErrors)
                {
                    totalResult.addError(error);
                }
                ModelAndView errorModelView = new ModelAndView("security/registration", "errors", totalResult);
                errorModelView.addObject("passwordsNotMatchedMessage", "Пароль и его подтверждение не совпадают.");
                return errorModelView;
            }
            return new ModelAndView("security/registration", "errors", errors);
        }
        try {
            User registered = userService.registerNewUser(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("security/registration", "user", userDto);
            mav.addObject("message", "Аккаунт для такого email " + userDto.getEmail()+ " уже существует.");
            return mav;
        }

        return new ModelAndView("security/success-register", "user", userDto);
    }
}
