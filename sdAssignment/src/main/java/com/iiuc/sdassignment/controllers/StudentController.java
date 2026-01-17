package com.iiuc.sdassignment.controllers;

import com.iiuc.sdassignment.entities.Student;
import com.iiuc.sdassignment.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    StudentController(StudentService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
         return ResponseEntity.ok(service.getAll());
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(service.createStudent(student));
    }

}
