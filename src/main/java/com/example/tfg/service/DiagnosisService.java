package com.example.tfg.service;

import com.example.tfg.dto.DiagnosisResultDTO;
import com.example.tfg.entities.Diagnosis;
import com.example.tfg.entities.Disease;
import com.example.tfg.entities.Patient;
import com.example.tfg.entities.Symptom;
import com.example.tfg.repository.DiagnosisRepository;
import com.example.tfg.repository.DiseaseRepository;
import com.example.tfg.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository, DiseaseRepository diseaseRepository, PatientRepository patientRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.diseaseRepository = diseaseRepository;
        this.patientRepository = patientRepository;
    }

    // Metodo para obtener los diagnósticos del paciente
    public List<Diagnosis> getPatientDiagnoses(Integer id) {
        return diagnosisRepository.findByPatientId(id);
    }

    public Optional<DiagnosisResultDTO> diagnoseByF1WithTieBreaker(Set<Integer> userSymptomIds) {
        double bestScore = 0.0;
        long bestCommon = 0;
        int bestDiseaseSize = Integer.MAX_VALUE;
        Disease bestDisease = null;

        for (Disease d : diseaseRepository.findAll()) {
            Set<Integer> D = d.getSymptoms().stream().map(Symptom::getSymptomId).collect(Collectors.toSet());
            long common = userSymptomIds.stream().filter(D::contains).count();
            if (common == 0) continue;

            double precision = (double) common / userSymptomIds.size();
            double recall = (double) common / D.size();
            double f1 = 2 * precision * recall / (precision + recall);

            if (f1 > bestScore || (Double.compare(f1, bestScore) == 0 &&
                    (common > bestCommon || (common == bestCommon && D.size() < bestDiseaseSize)))) {
                bestScore = f1;
                bestCommon = common;
                bestDiseaseSize = D.size();
                bestDisease = d;
            }
        }

        if (bestDisease == null) return Optional.empty();

        double confPct = Math.round(bestScore * 10000.0) / 100.0;
        DiagnosisResultDTO dto = new DiagnosisResultDTO(bestDisease.getDiseaseId(), bestDisease.getName(),
                bestDisease.getDescription(), bestScore, confPct);
        return Optional.of(dto);
    }

    public Diagnosis saveDiagnosis(Integer patientId,
                                   DiagnosisResultDTO dto) {
        Patient p = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente no existe"));
        Disease d = diseaseRepository.findById(dto.getDiseaseId())
                .orElseThrow(() -> new RuntimeException("Enfermedad no existe"));

        Diagnosis diag = new Diagnosis();
        diag.setPatient(p);
        diag.setDisease(d);
        diag.setDate(new Date());
        diag.setScore(dto.getScore());
        diag.setConfidence(dto.getConfidence());

        return diagnosisRepository.save(diag);
    }

}

