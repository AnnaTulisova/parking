package com.tulisova.parking.service.dto;

import com.tulisova.parking.service.validation.PasswordMatches;
import com.tulisova.parking.service.validation.ValidEmail;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
@PasswordMatches(message = "Пароли должны совпадать!")
public class UserDto {
    @NotNull @NotEmpty(message = "Заполните поле!")
    private String firstName;

    @NotNull @NotEmpty(message = "Заполните поле!")
    private String lastName;

    @NotNull @NotEmpty(message = "Заполните поле!")
    @Pattern(regexp = "^(?=(.*[а-я,a-z]){1,})(?=(.*[А-Я,A-Z]){1,})(?=(.*[0-9]){2,})(?=(.*[!@#$%^&*()\\-__+.]){1,}).{8,}$" ,
            message = "Пароль должен содержать миинимум одну заглавную букву, одну строчную букву, число, один спецсимвол. Сам пароль должен быть длиной от 8 символов.")
    private String password;

    @NotNull @NotEmpty(message = "Заполните поле!")
    private String matchingPassword;

    @NotNull @NotEmpty(message = "Заполните поле!")
    @Pattern(regexp = "\\d{11}", message = "Номер телефона должен содержать значение из 11 цифр.")
    private String phone;

    @ValidEmail(message = "Введите правильно email.")
    @NotNull @NotEmpty(message = "Заполните поле!")
    private String email;
}
