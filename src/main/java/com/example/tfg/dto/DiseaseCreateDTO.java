package com.example.tfg.dto;

import java.util.List;

public class DiseaseCreateDTO {
    private String name;
    private String description;
    private List<Integer> symptomIds; // Recibe los IDs de los síntomas
    private List<Integer> precautionIds; // Recibe los IDs de las precauciones

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getSymptomIds() {
        return symptomIds;
    }

    public void setSymptomIds(List<Integer> symptomIds) {
        this.symptomIds = symptomIds;
    }

    public List<Integer> getPrecautionIds() {
        return precautionIds;
    }

    public void setPrecautionIds(List<Integer> precautionIds) {
        this.precautionIds = precautionIds;
    }
}

