package com.tulisova.parking.service.dto;

import com.tulisova.parking.service.validation.PasswordMatches;
import com.tulisova.parking.service.validation.ValidEmail;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
@PasswordMatches
public class UserDto {
    @NotNull @NotEmpty
    private String firstName;

    @NotNull @NotEmpty
    private String lastName;

    @NotNull @NotEmpty
    private String password;

    @NotNull @NotEmpty
    private String matchingPassword;

    @NotNull @NotEmpty
    private String phone;

    @ValidEmail
    @NotNull @NotEmpty
    private String email;
}
