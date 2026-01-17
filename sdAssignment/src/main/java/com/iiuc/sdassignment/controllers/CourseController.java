package com.iiuc.sdassignment.controllers;

import com.iiuc.sdassignment.entities.Course;
import com.iiuc.sdassignment.entities.Student;
import com.iiuc.sdassignment.services.CourseService;
import com.iiuc.sdassignment.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/{studentId}/courses")
public class CourseController {

    private final CourseService service;
    private final StudentService  studentService;

    public CourseController(CourseService service, StudentService studentService) {
        this.service = service;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> add(@PathVariable Long studentId,@RequestBody Course course) {
        return studentService.getById(studentId)
                .map(s -> {
                    Course created = service.add(course);
                    studentService.add(studentId,created.getId());
                    return ResponseEntity.ok(created);
                }).orElse(ResponseEntity.notFound().build());


//                return ResponseEntity.ok("add courses in student")
//        studentService.add(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        return service.update(id, course)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long studentId, @PathVariable Long id) {
        studentService.deleteCourse(studentId, id);
        boolean deleted = service.delete(id);

        if (deleted) {
            return ResponseEntity.ok("Course with ID " + id + " was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with ID " + id + " not found.");
        }
    }
}