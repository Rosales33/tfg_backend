package com.example.tfg.entities;

import com.example.tfg.jsonView.View.*;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "disease")
public class Disease {
    @Id
    @Column(name = "disease_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ListOfEntity.class)
    private Integer diseaseId;

    @Column(unique = true, nullable = false)
    @JsonView(ListOfEntity.class)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonView(ListOfEntity.class)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "disease_precautions",
            joinColumns = @JoinColumn(name = "disease_id"),
            inverseJoinColumns = @JoinColumn(name = "precaution_id")
    )
    @JsonView(DiseasesSymptomsAndPrecautions.class)
    private List<Precaution> precautions;

    @ManyToMany
    @JoinTable(
            name = "disease_symptom",
            joinColumns = @JoinColumn(name = "disease_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
    @JsonView(DiseasesSymptomsAndPrecautions.class)
    private List<Symptom> symptoms;

    public Integer getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }

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

    public List<Precaution> getPrecautions() {
        return precautions;
    }

    public void setPrecautions(List<Precaution> precautions) {
        this.precautions = precautions;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public List<Integer> getSymptomIds() {
        if (symptoms == null) {
            symptoms = new ArrayList<>();  // Asegúrate de que symptoms nunca sea null
        }
        return symptoms.stream()
                .map(Symptom::getSymptomId) // Obtén los IDs de los síntomas
                .collect(Collectors.toList());
    }

    public List<Integer> getPrecautionIds() {
        if (precautions == null) {
            precautions = new ArrayList<>();  // Asegúrate de que precautions nunca sea null
        }
        return precautions.stream()
                .map(Precaution::getPrecautionId) // Obtén los IDs de las precauciones
                .collect(Collectors.toList());
    }
}
