package com.example.tfg.repository;

import com.example.tfg.entities.Precaution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecautionRepository extends JpaRepository<Precaution, Integer> {
}
