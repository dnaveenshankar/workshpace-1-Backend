package com.wipro.registrationservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private int studentId;
    private String firstname;
    private String lastname;
    private String email;
    private String mobile;
    private int age;
}
