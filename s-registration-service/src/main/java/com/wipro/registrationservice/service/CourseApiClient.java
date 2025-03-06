package com.wipro.registrationservice.service;

import com.wipro.registrationservice.model.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "S-COURSE-SERVICE")
public interface CourseApiClient {
    @GetMapping("/courses/view/{id}")
    CourseDTO getCourseDetails(@PathVariable int id);
}
