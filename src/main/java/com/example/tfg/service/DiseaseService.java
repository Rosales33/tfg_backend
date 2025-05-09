package com.example.tfg.service;

import com.example.tfg.dto.DiseaseCreateDTO;
import com.example.tfg.entities.Disease;
import com.example.tfg.entities.Precaution;
import com.example.tfg.entities.Symptom;
import com.example.tfg.repository.DiseaseRepository;
import com.example.tfg.repository.PrecautionRepository;
import com.example.tfg.repository.SymptomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;
    private final SymptomRepository symptomRepository;
    private final PrecautionRepository precautionRepository;

    public DiseaseService(DiseaseRepository diseaseRepository, SymptomRepository symptomRepository, PrecautionRepository precautionRepository) {
        this.diseaseRepository = diseaseRepository;
        this.symptomRepository = symptomRepository;
        this.precautionRepository = precautionRepository;
    }

    public Disease save(DiseaseCreateDTO diseaseDTO) {
        // Convertir los IDs de síntomas y precauciones en objetos completos
        List<Symptom> symptoms = symptomRepository.findAllById(diseaseDTO.getSymptomIds());
        List<Precaution> precautions = precautionRepository.findAllById(diseaseDTO.getPrecautionIds());

        // Crear una nueva entidad Disease con los datos del DTO
        Disease disease = new Disease();
        disease.setName(diseaseDTO.getName());
        disease.setDescription(diseaseDTO.getDescription());
        disease.setSymptoms(symptoms); // Establecer los síntomas
        disease.setPrecautions(precautions); // Establecer las precauciones

        // Guardar la enfermedad en la base de datos
        return diseaseRepository.save(disease);
    }

    public Disease save2(Disease disease) {
        // Guardar la enfermedad en la base de datos
        return diseaseRepository.save(disease);
    }

    public List<Disease> findAll() {
        return diseaseRepository.findAll();
    }

    public Disease findById(Integer id) {
        return diseaseRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        diseaseRepository.deleteById(id);
    }
}
