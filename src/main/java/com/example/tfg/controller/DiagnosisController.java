package com.example.tfg.controller;

import com.example.tfg.aspects.CheckAdmin;
import com.example.tfg.aspects.CheckSelf;
import com.example.tfg.dto.DiagnosisResultDTO;
import com.example.tfg.entities.Diagnosis;
import com.example.tfg.jsonView.View;
import com.example.tfg.service.DiagnosisService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/diagnoses")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping
    public ResponseEntity<?> diagnose(@RequestBody Map<String, List<Integer>> body) {
        Set<Integer> userSymptomIds = Set.copyOf(
                Optional.ofNullable(body.get("symptomIds")).orElse(List.of())
        );

        Optional<DiagnosisResultDTO> results = diagnosisService.diagnoseByF1WithTieBreaker(userSymptomIds);
        if (results.isEmpty()) {
            return ResponseEntity.badRequest().body("No matching disease found");
        }
        return ResponseEntity.ok(results);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveDiagnosis(
            @RequestParam Integer patientId,
            @RequestBody DiagnosisResultDTO dto) {
        try {
            diagnosisService.saveDiagnosis(patientId, dto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            // Puedes loguear el error aquí si lo deseas
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(false);
        }
    }
}
