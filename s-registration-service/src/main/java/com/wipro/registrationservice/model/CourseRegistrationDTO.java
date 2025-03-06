package com.wipro.registrationservice.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CourseRegistrationDTO {
    private int registrationId;
    private LocalDate dateOfRegistration;
    private double feesPaid;
    private double feesPending;
    private String status;

    private StudentDTO student; 
    private CourseDTO course; 
}
