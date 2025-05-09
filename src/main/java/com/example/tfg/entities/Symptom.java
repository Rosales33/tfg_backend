package com.example.tfg.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import com.example.tfg.jsonView.View.*;

import java.util.List;

@Entity
@Table(name = "symptom")
public class Symptom {
    @Id
    @Column(name = "symptom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ListOfEntity.class)
    private Integer symptomId;

    @Column(unique = true, nullable = false)
    @JsonView(ListOfEntity.class)
    private String name;

    @Column(nullable = false)
    @JsonView(ListOfEntity.class)
    private int severity;

    @ManyToMany(mappedBy = "symptoms")
    private List<Disease> diseases;


    public Integer getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Integer symptomId) {
        this.symptomId = symptomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
