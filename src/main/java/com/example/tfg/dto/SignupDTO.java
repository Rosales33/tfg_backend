package com.example.tfg.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class SignupDTO {

    @NotNull(message = "The username is mandatory")
    private String username;

    @NotNull(message = "The email is mandatory")
    @Email(message = "The email should be valid")
    private String email;

    @NotNull(message = "The password is mandatory")
    private String password;

    // Getters y Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

