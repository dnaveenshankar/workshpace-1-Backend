package com.wipro.courseservice.service;

import com.wipro.courseservice.entity.Course;
import com.wipro.courseservice.exception.ResourceNotFoundException;
import com.wipro.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(int courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isEmpty()) {
            throw new ResourceNotFoundException("Course not found with ID: " + courseId);
        }
        return optionalCourse.get();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
