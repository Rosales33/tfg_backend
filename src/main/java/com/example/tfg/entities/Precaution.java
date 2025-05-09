package com.example.tfg.entities;

import com.example.tfg.jsonView.View.*;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "precaution")
public class Precaution {
    @Id
    @Column(name = "precaution_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ListOfEntity.class)
    private Integer precautionId;

    @Column(nullable = false)
    @JsonView(ListOfEntity.class)
    private String precautionText;

    @ManyToMany(mappedBy = "precautions")
    private List<Disease> diseases; // Lista de enfermedades asociadas a esta precaución

    public Integer getPrecautionId() {
        return precautionId;
    }

    public void setPrecautionId(Integer precautionId) {
        this.precautionId = precautionId;
    }

    public String getPrecautionText() {
        return precautionText;
    }

    public void setPrecautionText(String precautionText) {
        this.precautionText = precautionText;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
