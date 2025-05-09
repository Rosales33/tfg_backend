package com.example.tfg.controller;

import com.example.tfg.aspects.CheckAdmin;
import com.example.tfg.dto.DiseaseCreateDTO;
import com.example.tfg.entities.Disease;
import com.example.tfg.entities.Precaution;
import com.example.tfg.entities.Symptom;
import com.example.tfg.jsonView.View.*;
import com.example.tfg.repository.PrecautionRepository;
import com.example.tfg.repository.SymptomRepository;
import com.example.tfg.service.DiseaseService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/diseases")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PostMapping
    @CheckAdmin
    public Disease create(@RequestBody DiseaseCreateDTO diseaseDTO) {
        return diseaseService.save(diseaseDTO); // Llamar al servicio con el DTO
    }

    @GetMapping
    @JsonView(ListOfEntity.class)
    public List<Disease> getAll() {
        return diseaseService.findAll();
    }

    @GetMapping("/extended")
    @JsonView(DiseasesSymptomsAndPrecautions.class)
    public List<Disease> getAllDiseasesWithSymptomsAndPrecautions() {
        return diseaseService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ListOfEntity.class)
    public Disease getById(@PathVariable Integer id) {
        return diseaseService.findById(id);
    }

    @GetMapping("/{id}/extended")
    @JsonView(DiseasesSymptomsAndPrecautions.class)
    public Disease getDiseaseByIdWithSymptomsAndPrecautions(@PathVariable Integer id) {
        return diseaseService.findById(id);
    }

    @DeleteMapping("/{id}")
    @CheckAdmin
    public void delete(@PathVariable Integer id) {
        diseaseService.delete(id);
    }

    @PutMapping("/{id}")
    @CheckAdmin
    public ResponseEntity<Disease> updateDisease(
            @PathVariable Integer id,
            @RequestBody Disease diseasePayload
    ) {
        Disease existing = diseaseService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setName(diseasePayload.getName());
        existing.setDescription(diseasePayload.getDescription());
        Disease updated = diseaseService.save2(existing);
        return ResponseEntity.ok(updated);
    }

}



