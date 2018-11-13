package com.example.sample.studentProject.dao;

import java.util.List;
import com.example.sample.studentProject.bean.Student;

public interface StudentDao {
    public Student createStudent(Student student);
    public List<Student> getAllStudents();
    public Student findByRollNo(int rollNo);
    public Student updateStudent(Student student);
}