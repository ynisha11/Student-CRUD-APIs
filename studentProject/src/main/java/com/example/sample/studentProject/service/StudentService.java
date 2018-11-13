package com.example.sample.studentProject.service;

import java.util.List;
import com.example.sample.studentProject.bean.Student;

public interface StudentService {
    public Student createStudent(Student student);
    public List<Student> getAllStudents();
    public Student findByRollNo(int rollNo);
    public Student updateStudent(Student student);
    public Student deleteStudentByRollNo(int rollNo);
}