package com.example.tfg.service;

import com.example.tfg.entities.Symptom;
import com.example.tfg.repository.SymptomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomService {

    private final SymptomRepository symptomRepository;

    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public Symptom save(Symptom symptom) {
        return symptomRepository.save(symptom);
    }

    public List<Symptom> findAll() {
        return symptomRepository.findAll();
    }

    public Symptom findById(Integer id) {
        return symptomRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        symptomRepository.deleteById(id);
    }
}
