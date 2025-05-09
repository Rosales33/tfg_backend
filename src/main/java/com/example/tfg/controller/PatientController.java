package com.example.tfg.controller;

import com.example.tfg.entities.Diagnosis;
import com.example.tfg.aspects.CheckSelf;
import com.example.tfg.service.DiagnosisService;
import com.example.tfg.jsonView.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final DiagnosisService diagnosisService;

    @Autowired
    public PatientController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    /**
     * Endpoint GET para obtener los diagnósticos del paciente por ID.
     * Solo el mismo paciente (o un ADMIN) podrá acceder a sus propios diagnósticos.
     */
    @GetMapping("/{id}/diagnoses")
    @JsonView(View.ListOfEntity.class)
    @CheckSelf(patientIdParamIndex = 0)
    public List<Diagnosis> getPatientDiagnoses(@PathVariable Integer id) {
        return diagnosisService.getPatientDiagnoses(id);
    }
}

