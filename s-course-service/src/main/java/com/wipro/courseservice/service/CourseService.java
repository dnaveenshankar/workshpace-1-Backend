package com.wipro.courseservice.service;

import com.wipro.courseservice.entity.Course;
import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    Course getCourseById(int courseId);
    List<Course> getAllCourses();
}
