package com.iiuc.sdassignment.services;

import com.iiuc.sdassignment.entities.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final List<Course> courses = new ArrayList<>();
    private Long nextId = 100L; // simulate ID generation

    public List<Course> getAll() {
        return new ArrayList<>(courses); // defensive copy
    }

    public Optional<Course> getById(Long id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Course add(Course course) {
        course.setId(nextId++);
        courses.add(cloneCourse(course));
        return cloneCourse(course);
    }

    public Optional<Course> update(Long id, Course updated) {
        return getById(id).map(existing -> {
            existing.setCode(updated.getCode());
            existing.setTitle(updated.getTitle());
            existing.setCredit(updated.getCredit());
            existing.setType(updated.getType());
            existing.setSemester(updated.getSemester());
            existing.setDepartmentId(updated.getDepartmentId());
            existing.setTeacher(updated.getTeacher());
            return cloneCourse(existing);
        });
    }

    public boolean delete(Long id) {
        return courses.removeIf(c -> c.getId().equals(id));
    }

    private Course cloneCourse(Course c) {
        return new Course(
                c.getId(), c.getCode(), c.getTitle(), c.getCredit(),
                c.getType(), c.getSemester(), c.getDepartmentId(), c.getTeacher()
        );
    }
}