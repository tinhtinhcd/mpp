package com.housing.app.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class UserUpdateRequest {

    @NotEmpty(message = "firstName should not be empty")
    private String firstName;

    @NotEmpty(message = "lastName should not be empty")
    private String lastName;

    private String password;

    private String phone;

    private String gender;

    private String dob;
}
