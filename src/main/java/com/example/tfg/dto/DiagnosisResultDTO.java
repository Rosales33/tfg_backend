package com.example.tfg.dto;

public class DiagnosisResultDTO {
    private Integer diseaseId;
    private String name;
    private String description;
    private double score;       // F1‐score en [0.0 – 1.0]
    private double confidence;  // score*100 redondeado a dos decimales

    public DiagnosisResultDTO(Integer diseaseId, String name, String description,
                              double score, double confidence) {
        this.diseaseId   = diseaseId;
        this.name        = name;
        this.description = description;
        this.score       = score;
        this.confidence  = confidence;
    }

    // Getters y setters

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

