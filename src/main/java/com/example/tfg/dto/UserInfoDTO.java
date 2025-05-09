package com.example.tfg.dto;

public class UserInfoDTO {
    private String username;
    private String role;
    private Integer patientId;

    public UserInfoDTO(String username, String role, Integer patientId) {
        this.username  = username;
        this.role = role;
        this.patientId = patientId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}


