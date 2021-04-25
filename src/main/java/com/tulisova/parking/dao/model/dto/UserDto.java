package com.tulisova.parking.dao.model.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter @Setter @NoArgsConstructor
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    private String email;
}
