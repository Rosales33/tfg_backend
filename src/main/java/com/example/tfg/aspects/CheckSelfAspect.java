package com.example.tfg.aspects;

import com.example.tfg.entities.Patient;
import com.example.tfg.repository.PatientRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Aspect
@Component
public class CheckSelfAspect {

    private final PatientRepository patientRepository;

    public CheckSelfAspect(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Before("@annotation(checkSelf)")
    public void validateAccess(JoinPoint joinPoint, CheckSelf checkSelf) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
        }

        // Obtener el username del usuario autenticado
        String currentUsername;
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails) {
            currentUsername = ((UserDetails) principal).getUsername();
        } else {
            currentUsername = principal.toString();
        }

        // Si el usuario tiene ROLE_ADMIN, permite el acceso sin comprobar el recurso
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            return;
        }

        // Extraer el parámetro que contiene el patientId usando el índice de la anotación
        Object[] args = joinPoint.getArgs();
        int index = checkSelf.patientIdParamIndex();
        if (args.length <= index) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient ID not provided");
        }
        Object param = args[index];
        Integer patientId;
        if (param instanceof Integer) {
            patientId = (Integer) param;
        } else if (param instanceof String) {
            try {
                patientId = Integer.valueOf((String) param);
            } catch (NumberFormatException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid patient ID format");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid patient ID type");
        }

        // Recuperar el paciente asociado al usuario autenticado
        Patient patient = patientRepository.findByUsername(currentUsername);
        if (patient == null || !Objects.equals(patient.getId(), patientId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: You do not own this resource");
        }
    }
}



