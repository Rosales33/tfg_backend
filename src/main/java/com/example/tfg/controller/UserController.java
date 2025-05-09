package com.example.tfg.controller;

import com.example.tfg.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    // Endpoint GET para obtener los datos del usuario por ID
    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id) {
        // Obtener el objeto 'User' desde el contexto de seguridad
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Comparar el ID del usuario autenticado con el ID de la URL
        if (Objects.equals(currentUser.getId(), id)) {
            // Si el ID del usuario autenticado coincide con el ID de la URL, permitir el acceso
            return "Acceso permitido: Los datos del usuario " + currentUser.getUsername();
        } else {
            // Si los IDs no coinciden, denegar el acceso
            return "Acceso denegado: No tiene permisos para acceder a este recurso.";
        }
    }
}

