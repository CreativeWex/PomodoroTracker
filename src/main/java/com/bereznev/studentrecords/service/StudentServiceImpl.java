package com.bereznev.studentrecords.service;
/*
    =====================================
    @project StudentRecords
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.studentrecords.exception.ResourceNotFoundException;
import com.bereznev.studentrecords.model.Student;
import com.bereznev.studentrecords.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existedStudent = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "Id", id));

        existedStudent.setFirstName(updatedStudent.getFirstName());
        existedStudent.setLastName(updatedStudent.getLastName());
        existedStudent.setEmail(updatedStudent.getEmail());

        studentRepository.save(existedStudent);
        return existedStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
        studentRepository.deleteById(id);
    }

}
