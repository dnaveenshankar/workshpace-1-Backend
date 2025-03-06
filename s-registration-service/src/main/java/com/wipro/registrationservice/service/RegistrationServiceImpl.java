package com.wipro.registrationservice.service;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.registrationservice.entity.Registration;
import com.wipro.registrationservice.exception.ResourceNotFoundException;
import com.wipro.registrationservice.model.CourseDTO;
import com.wipro.registrationservice.model.CourseRegistrationDTO;
import com.wipro.registrationservice.model.StudentDTO;
import com.wipro.registrationservice.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private StudentApiClient studentApiClient;

    @Autowired
    private CourseApiClient courseApiClient;

    @Override
    public Registration registerStudent(Registration registration) {
        CourseDTO course = courseApiClient.getCourseDetails(registration.getCourseId());

        double feesPaid = registration.getFeesPaid();
        double courseFees = course.getCourseFees();
        double feesPending = courseFees - feesPaid;

        registration.setDateOfRegistration(LocalDate.now());
        registration.setFeesPending(feesPending);
        registration.setStatus("Enrolled");

        return registrationRepository.save(registration);
    }

    @Override
    public Registration getRegistrationById(int registrationId) {
        Optional<Registration> optionalRegistration = registrationRepository.findById(registrationId);

        if (optionalRegistration.isEmpty()) {
            throw new ResourceNotFoundException("Registration not found with ID: " + registrationId);
        }

        return optionalRegistration.get();
    }


    @Override
    public CourseRegistrationDTO getFullRegistrationDetails(int registrationId) {
        Registration registration = getRegistrationById(registrationId);

        CourseRegistrationDTO courseRegistrationDTO = new CourseRegistrationDTO();
        
        // Setting Registration details
        courseRegistrationDTO.setRegistrationId(registration.getRegistrationId());
        courseRegistrationDTO.setDateOfRegistration(registration.getDateOfRegistration());
        courseRegistrationDTO.setFeesPaid(registration.getFeesPaid());
        courseRegistrationDTO.setFeesPending(registration.getFeesPending());
        courseRegistrationDTO.setStatus(registration.getStatus());

        // Fetch and set Student details
        StudentDTO student = studentApiClient.getStudentDetails(registration.getStudentId());
        courseRegistrationDTO.setStudent(student);  // Directly setting Student object

        // Fetch and set Course details
        CourseDTO course = courseApiClient.getCourseDetails(registration.getCourseId());
        courseRegistrationDTO.setCourse(course);  // Directly setting Course object

        return courseRegistrationDTO;
    }

}
