package com.wipro.studentservice.service;

import com.wipro.studentservice.entity.Student;
import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
}
