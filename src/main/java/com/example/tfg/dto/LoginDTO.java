package com.example.tfg.dto;

import jakarta.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull(message = "The username is mandatory")
    private String username;

    @NotNull(message = "The password is mandatory")
    private String password;

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

