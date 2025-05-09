package com.example.tfg.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRolesController {

    // Endpoint solo accesible para ADMIN
    //@Secured("ROLE_ADMIN") //para restringir el acceso a métodos específicos sin importar la URL
    @GetMapping("/admin")
    public String testAdminAccess() {
        return "Acceso permitido: Solo para administradores.";
    }

    // Endpoint accesible para cualquier usuario autenticado
    //@Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user")
    public String testUserAccess() {
        return "Acceso permitido: Cualquier usuario autenticado.";
    }
}
