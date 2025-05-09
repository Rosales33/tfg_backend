package com.example.tfg.controller;

import com.example.tfg.dto.SignupDTO;
import com.example.tfg.dto.UserInfoDTO;
import com.example.tfg.entities.Patient;
import com.example.tfg.entities.User;
import com.example.tfg.jwt.JwtUtil;
import com.example.tfg.repository.PatientRepository;
import com.example.tfg.repository.UserRepository;
import com.example.tfg.dto.LoginDTO;
import com.example.tfg.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final PatientRepository patientRepository;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager, AuthService authService, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.patientRepository = patientRepository;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request) {
        User user = userRepository.findByUsername(loginDTO.getUsername());

        if (user != null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

            authenticationManager.authenticate(authenticationToken);
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole().getName());

            request.getSession().setAttribute("token", token);

            return jwtUtil.generateToken(user.getUsername(), user.getRole().getName());
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "incorrect user or password";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupDTO signupDTO) {
        // Registrar al paciente a través del servicio
        try {
            authService.registerPatient(signupDTO.getUsername(), signupDTO.getEmail(), signupDTO.getPassword());
            return ResponseEntity.ok("Patient successfully signed up");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoDTO> userInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        String role = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("ROLE_USER");

        Patient patient = patientRepository.findByUsername(username);

        UserInfoDTO dto = new UserInfoDTO(username, role, patient.getId());
        return ResponseEntity.ok(dto);
    }

}

