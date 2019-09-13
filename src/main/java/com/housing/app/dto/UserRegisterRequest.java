package com.housing.app.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRegisterRequest {

    @NotEmpty(message = "firstName should not be empty")
    private String firstName;

    @NotEmpty(message = "lastName should not be empty")
    private String lastName;

    @NotEmpty(message = "User name should not be empty")
    @Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 6,  message = "Password should have at least 6 characters")
    private String password;

}
