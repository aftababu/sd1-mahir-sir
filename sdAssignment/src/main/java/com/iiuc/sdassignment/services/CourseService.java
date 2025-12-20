package com.iiuc.sdassignment.services;

import com.iiuc.sdassignment.entities.Course;
import com.iiuc.sdassignment.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setCode(updatedCourse.getCode());
            course.setTitle(updatedCourse.getTitle());
            course.setCredit(updatedCourse.getCredit());
            course.setType(updatedCourse.getType());
            course.setSemester(updatedCourse.getSemester());
            course.setDepartmentId(updatedCourse.getDepartmentId());
            course.setTeacher(updatedCourse.getTeacher());
            return courseRepository.save(course);
        });
    }

    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}