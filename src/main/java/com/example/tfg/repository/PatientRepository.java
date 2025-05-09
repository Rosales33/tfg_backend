package com.example.tfg.repository;

import com.example.tfg.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByUsername(String username);  // Buscar paciente por nombre de usuario
    boolean existsByEmail(String email);
}

