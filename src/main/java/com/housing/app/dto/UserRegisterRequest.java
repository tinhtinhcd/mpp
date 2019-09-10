package com.housing.app.dto;

import javax.validation.constraints.NotEmpty;

public class UserRegisterRequest {

    @NotEmpty(message = "User name should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
