package com.example.tfg.controller;

import com.example.tfg.aspects.CheckAdmin;
import com.example.tfg.entities.Symptom;
import com.example.tfg.jsonView.View.*;
import com.example.tfg.service.SymptomService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symptoms")
public class SymptomController {

    private final SymptomService symptomService;

    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @PostMapping
    @CheckAdmin
    public Symptom create(@RequestBody Symptom symptom) {
        return symptomService.save(symptom);
    }

    @GetMapping
    @JsonView(ListOfEntity.class)
    public List<Symptom> getAll() {
        return symptomService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ListOfEntity.class)
    public Symptom getById(@PathVariable Integer id) {
        return symptomService.findById(id);
    }

    @DeleteMapping("/{id}")
    @CheckAdmin
    public void delete(@PathVariable Integer id) {
        symptomService.delete(id);
    }

    @PutMapping("/{id}")
    @CheckAdmin
    public ResponseEntity<Symptom> updateSymptom(@PathVariable Integer id, @RequestBody Symptom symptom) {
        try {
            // Verificar si el síntoma existe
            Symptom existingSymptom = symptomService.findById(id);
            if (existingSymptom == null) {
                return ResponseEntity.notFound().build(); // Si no se encuentra, retornar 404
            }

            // Actualizar los campos del objeto existente
            existingSymptom.setName(symptom.getName());
            existingSymptom.setSeverity(symptom.getSeverity());

            // Guardar el síntoma actualizado
            Symptom updatedSymptom = symptomService.save(existingSymptom);

            return ResponseEntity.ok(updatedSymptom); // Devolver el síntoma actualizado
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // En caso de error interno
        }
    }

}
