package com.example.tfg.entities;

import com.example.tfg.jsonView.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.ListOfEntity.class)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "disease_id", nullable = false)
    @JsonView(View.ListOfEntity.class)
    private Disease disease;

    @Temporal(TemporalType.DATE)
    @JsonView(View.ListOfEntity.class)
    private Date date;  // Fecha del diagnóstico

    @Column(name = "score", nullable = false)
    @JsonView(View.ListOfEntity.class)
    private double score;

    @Column(name = "confidence", nullable = false)
    @JsonView(View.ListOfEntity.class)
    private double confidence;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}

