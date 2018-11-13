package com.example.sample.studentProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.studentProject.bean.Student;
import com.example.sample.studentProject.dao.StudentDao;

@Service
@Transactional
public class StudentServiceImp implements StudentService {

	@Autowired
	StudentDao studentDao;

	public Student createStudent(Student student) {
		return studentDao.createStudent(student);
	}

	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	public Student findByRollNo(int rollNo) {
		return studentDao.findByRollNo(rollNo);
	}

	public Student updateStudent(Student student) {
		Student dbStudent = studentDao.findByRollNo(student.getRollNo());
		if (student.getName() != null) {
			dbStudent.setName(student.getName());
		}
		if (student.getAge() > 0) {
			dbStudent.setAge(student.getAge());
		}
		if (student.getDateOfBirth() != null) {
			dbStudent.setDateOfBirth(student.getDateOfBirth());
		}
		return studentDao.updateStudent(dbStudent);
	}

	public Student deleteStudentByRollNo(int rollNo) {
		Student student = studentDao.findByRollNo(rollNo);
		student.setActive(false);
		return studentDao.updateStudent(student);
	}

}