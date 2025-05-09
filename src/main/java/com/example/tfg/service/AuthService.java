package com.example.tfg.service;

import com.example.tfg.entities.Patient;
import com.example.tfg.entities.Role;
import com.example.tfg.entities.User;
import com.example.tfg.repository.PatientRepository;
import com.example.tfg.repository.RoleRepository;
import com.example.tfg.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PatientRepository patientRepository,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerPatient(String username, String email, String password) {
        // Verificar si el username ya existe
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken");
        }

        // Verificar si el email ya existe
        if (patientRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already registered");
        }

        // Crear un nuevo rol USER
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        if (!roleOptional.isPresent()) {
            throw new RuntimeException("Role USER not found");
        }

        Role userRole = roleOptional.get();

        // Crear el objeto User
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(userRole);

        // Guardar el usuario
        userRepository.save(user);

        // Crear el objeto Patient
        Patient patient = new Patient();
        patient.setUsername(username);
        patient.setEmail(email);

        // Guardar el paciente
        patientRepository.save(patient);
    }
}

