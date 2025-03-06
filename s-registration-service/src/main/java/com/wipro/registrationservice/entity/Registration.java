package com.wipro.registrationservice.entity;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registrationId;
    private int courseId;
    private int studentId;
    private LocalDate dateOfRegistration;
    private double feesPaid;
    private double feesPending;
    private String status;
}
