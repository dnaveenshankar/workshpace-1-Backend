package com.wipro.registrationservice.service;

import com.wipro.registrationservice.model.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "S-STUDENT-SERVICE")
public interface StudentApiClient {
    @GetMapping("/students/view/{id}")
    StudentDTO getStudentDetails(@PathVariable int id);
}
