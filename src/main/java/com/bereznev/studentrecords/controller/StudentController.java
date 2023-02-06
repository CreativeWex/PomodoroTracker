package com.bereznev.studentrecords.controller;
/*
    =====================================
    @project StudentRecords
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.studentrecords.model.Student;
import com.bereznev.studentrecords.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.updateStudent(id, student), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<String>("Student deleted successfully!", HttpStatus.OK);
    }
}
