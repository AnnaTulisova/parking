package com.tulisova.parking.dao.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
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
    private String carNumber;

    @NotNull @NotEmpty
    private String phone;

    @NotNull @NotEmpty
    private String email;
}
