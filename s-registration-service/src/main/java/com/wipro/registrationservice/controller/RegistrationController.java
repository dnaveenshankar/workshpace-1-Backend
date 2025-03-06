package com.wipro.registrationservice.controller;

import com.wipro.registrationservice.entity.Registration;
import com.wipro.registrationservice.model.CourseRegistrationDTO;
import com.wipro.registrationservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public Registration registerStudent(@RequestBody Registration registration) {
        return registrationService.registerStudent(registration);
    }

    @GetMapping("/view/{id}")
    public Registration getRegistrationById(@PathVariable int id) {
        return registrationService.getRegistrationById(id);
    }

    @GetMapping("/full-details/{id}")
    public CourseRegistrationDTO getFullRegistrationDetails(@PathVariable int id) {
        return registrationService.getFullRegistrationDetails(id);
    }
}
