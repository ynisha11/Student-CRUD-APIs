package com.example.sample.studentProject.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.sample.studentProject.bean.Student;
import com.example.sample.studentProject.service.StudentService;

@RestController
@RequestMapping(value = { "/api" })
public class StudentController {
	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/student", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createStudent(@RequestBody Student student) {
		System.out.println("Creating Student " + student.getName());
		studentService.createStudent(student);
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Message", "Succesfully created student!");
		responseMap.put("Student", student);
		return new ResponseEntity(responseMap, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Message", "Succesfully fetched all active students!");
		responseMap.put("Students", students);
		return new ResponseEntity(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/student/{rollNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getUserById(@PathVariable int rollNo) {
		System.out.println("Succesfully fetched student by rollNo: " + rollNo + "!");
		Student student = studentService.findByRollNo(rollNo);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}

		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Message", "Succesfully fetched student by rollNo: " + rollNo + "!");
		responseMap.put("Student", student);
		return new ResponseEntity(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/student", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody Student student) {
		student = studentService.updateStudent(student);
		if (student == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Message", "Succesfully updated student by rollNo: " + student.getRollNo() + "!");
		responseMap.put("Student", student);
		return new ResponseEntity(responseMap, HttpStatus.OK);
	}

	@RequestMapping(value = "student/{rollNo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> deleteUser(@PathVariable int rollNo) {
		Student student = studentService.deleteStudentByRollNo(rollNo);

		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Message", "Succesfully deleted student by rollNo: " + student.getRollNo() + "!");
		responseMap.put("Student", student);
		return new ResponseEntity(responseMap, HttpStatus.OK);
	}
}