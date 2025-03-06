package com.wipro.registrationservice.service;

import com.wipro.registrationservice.entity.Registration;
import com.wipro.registrationservice.model.CourseRegistrationDTO;

public interface RegistrationService {
    
    Registration registerStudent(Registration registration);

    Registration getRegistrationById(int registrationId);

    CourseRegistrationDTO getFullRegistrationDetails(int registrationId);
}
