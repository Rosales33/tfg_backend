package com.example.tfg.controller;

import com.example.tfg.aspects.CheckAdmin;
import com.example.tfg.entities.Precaution;
import com.example.tfg.jsonView.View.*;
import com.example.tfg.service.PrecautionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/precautions")
public class PrecautionController {

    private final PrecautionService precautionService;

    public PrecautionController(PrecautionService precautionService) {
        this.precautionService = precautionService;
    }

    @PostMapping
    @CheckAdmin
    public Precaution create(@RequestBody Precaution precaution) {
        return precautionService.save(precaution);
    }

    @GetMapping
    @JsonView(ListOfEntity.class)
    public List<Precaution> getAll() {
        return precautionService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ListOfEntity.class)
    public Precaution getById(@PathVariable Integer id) {
        return precautionService.findById(id);
    }

    @DeleteMapping("/{id}")
    @CheckAdmin
    public void delete(@PathVariable Integer id) {
        precautionService.delete(id);
    }

    @PutMapping("/{id}")
    @CheckAdmin
    public ResponseEntity<Precaution> updatePrecaution(@PathVariable Integer id, @RequestBody Precaution precaution) {
        try {
            // Verificar si la precaución existe
            Precaution existingPrecaution = precautionService.findById(id);
            if (existingPrecaution == null) {
                return ResponseEntity.notFound().build(); // Si no se encuentra, retornar 404
            }

            // Actualizar los campos del objeto existente
            existingPrecaution.setPrecautionText(precaution.getPrecautionText());

            // Guardar la precaución actualizada
            Precaution updatedPrecaution = precautionService.save(existingPrecaution);

            return ResponseEntity.ok(updatedPrecaution); // Devolver la precaución actualizada
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // En caso de error interno
        }
    }
}
