package com.example.tfg.service;

import com.example.tfg.entities.Precaution;
import com.example.tfg.repository.PrecautionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecautionService {
    private final PrecautionRepository precautionRepository;

    public PrecautionService(PrecautionRepository precautionRepository) {
        this.precautionRepository = precautionRepository;
    }

    public Precaution save(Precaution precaution) {
        return precautionRepository.save(precaution);
    }

    public List<Precaution> findAll() {
        return precautionRepository.findAll();
    }

    public Precaution findById(Integer id) {
        return precautionRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        precautionRepository.deleteById(id);
    }
}
