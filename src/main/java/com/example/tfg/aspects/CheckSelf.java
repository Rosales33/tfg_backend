package com.example.tfg.aspects;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckSelf {
    /**
     * Index of the parameter that contains the patient ID.
     * Default is 0.
     */
    int patientIdParamIndex() default 0;
}
