package com.example.tfg.entities;

import com.example.tfg.jsonView.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.ListOfEntity.class)
    private Integer id;

    @JsonView(View.ListOfEntity.class)
    private String username;  // Relación con la tabla 'user'

    @Column(nullable = false, unique = true)
    @JsonView(View.ListOfEntity.class)
    private String email;     // Email único

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Diagnosis> diagnoses; // Relación con la tabla 'diagnosis'

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }
}

